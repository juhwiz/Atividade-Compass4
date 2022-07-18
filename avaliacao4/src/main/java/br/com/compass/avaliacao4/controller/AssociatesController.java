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

import br.com.compass.avaliacao4.dto.request.RequestAssociacionDto;
import br.com.compass.avaliacao4.dto.request.RequestAssociatesDto;
import br.com.compass.avaliacao4.dto.response.ResponseAssociatesDto;
import br.com.compass.avaliacao4.models.PoliticalOffice;
import br.com.compass.avaliacao4.service.AssociatesService;

@RestController
@RequestMapping("/associados")
public class AssociatesController {
    
    @Autowired
    private AssociatesService service;

    @PostMapping
    public ResponseEntity<ResponseAssociatesDto> post(@RequestBody RequestAssociatesDto associates){
        ResponseAssociatesDto response = service.postAssociates(associates);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/partidos")
    public ResponseEntity<Void> postAssociation(@RequestBody RequestAssociacionDto association){
        service.updatePoliticalParty(association);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idAssociado}/partidos/{idPartido}")
    public ResponseEntity<Void> deleteAssociacion(@PathVariable Long idAssociado, @PathVariable Long idPartido){
        service.deleteAssociacion(idAssociado, idPartido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody RequestAssociatesDto associates, @PathVariable Long id){
        service.update(associates, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseAssociatesDto>> get(@RequestParam(required = false)PoliticalOffice cargoPolitico,@RequestParam(required = false)String sortNomeBy){
        if(cargoPolitico == null && sortNomeBy == null){
            List<ResponseAssociatesDto> responseDto = service.getAssociates();
            return ResponseEntity.ok(responseDto);
        } else if(!sortNomeBy.equals(null)){
            List<ResponseAssociatesDto> responseDto = service.getAssociatesBySort();
            return ResponseEntity.ok(responseDto);
        } 
        else {
            List<ResponseAssociatesDto> responseDto = service.getAssociatesByOffice(cargoPolitico);
            if(responseDto == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(responseDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAssociatesDto> getById(@PathVariable Long id){
        ResponseAssociatesDto responseDto = service.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    

}
