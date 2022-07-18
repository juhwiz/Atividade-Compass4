package br.com.compass.avaliacao4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.avaliacao4.dto.request.RequestPoliticalPartyDto;
import br.com.compass.avaliacao4.dto.response.ResponsePoliticalPartyDto;
import br.com.compass.avaliacao4.models.Ideology;
import br.com.compass.avaliacao4.service.PoliticalPartyService;

@RestController
@RequestMapping("/partidos")
public class PoliticalPartyController{

    @Autowired
    private PoliticalPartyService service;

    @PostMapping
    public ResponseEntity<ResponsePoliticalPartyDto> post(@RequestBody RequestPoliticalPartyDto politicalParty){
        ResponsePoliticalPartyDto response = service.postPoliticalParty(politicalParty); 
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody RequestPoliticalPartyDto politicalParty, @PathVariable Long id){
        service.update(politicalParty, id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponsePoliticalPartyDto>> get(@RequestParam(required = false)Ideology ideologia){
        if(ideologia == null){
            List<ResponsePoliticalPartyDto> responsePartyDto = service.getPoliticalParty();
            return ResponseEntity.ok(responsePartyDto);
        } else{
            List<ResponsePoliticalPartyDto> responsePartyDto = service.getPartyByIdeology(ideologia);
            if(responsePartyDto == null){
                return ResponseEntity.badRequest().build();
            }
            
            return ResponseEntity.ok(responsePartyDto);
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePoliticalPartyDto> getById(@PathVariable Long id){
        ResponsePoliticalPartyDto responsePartyDto = service.getById(id);
        return ResponseEntity.ok(responsePartyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}