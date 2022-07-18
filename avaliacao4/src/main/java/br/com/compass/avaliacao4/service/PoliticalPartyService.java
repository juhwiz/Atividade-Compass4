package br.com.compass.avaliacao4.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.avaliacao4.dto.request.RequestPoliticalPartyDto;
import br.com.compass.avaliacao4.dto.response.ResponsePoliticalPartyDto;
import br.com.compass.avaliacao4.exceptions.PoliticalPartyNotFoundException;
import br.com.compass.avaliacao4.models.Ideology;
import br.com.compass.avaliacao4.models.PoliticalPartyModel;
import br.com.compass.avaliacao4.repository.PoliticalPartyRepository;

@Service
public class PoliticalPartyService{

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository; 
    

    @Autowired
    private ModelMapper modelMapper;


    public ResponsePoliticalPartyDto postPoliticalParty(@Valid RequestPoliticalPartyDto politicalParty ){
        PoliticalPartyModel model = modelMapper.map(politicalParty, PoliticalPartyModel.class);
        PoliticalPartyModel saveModel = politicalPartyRepository.save(model);
        return modelMapper.map(saveModel, ResponsePoliticalPartyDto.class);
    }

    public List<ResponsePoliticalPartyDto> getPoliticalParty(){
        
        List<PoliticalPartyModel> allPartys = politicalPartyRepository.findAll();
        List<ResponsePoliticalPartyDto> partyDtos = allPartys.stream().map(politicalPartyModel -> 
            modelMapper.map(politicalPartyModel, ResponsePoliticalPartyDto.class))
            .collect(Collectors.toList());
        return partyDtos;
    
            
    }
    public List<ResponsePoliticalPartyDto> getPartyByIdeology(Ideology ideologia){    
        List<PoliticalPartyModel> result = politicalPartyRepository.findByIdeologia(ideologia);
        List<ResponsePoliticalPartyDto> partyDtos = result.stream().map(politicalPartyModel -> 
            modelMapper.map(politicalPartyModel, ResponsePoliticalPartyDto.class))
            .collect(Collectors.toList());
            return partyDtos;
            
    }

    public ResponsePoliticalPartyDto getById(Long id){
        PoliticalPartyModel partyModel = politicalPartyRepository.findById(id).orElseThrow(PoliticalPartyNotFoundException::new);
        return modelMapper.map(partyModel, ResponsePoliticalPartyDto.class);
    }

    public void delete(Long id){
        politicalPartyRepository.findById(id).orElseThrow(PoliticalPartyNotFoundException::new);
        politicalPartyRepository.deleteById(id);
    }

    public void update(RequestPoliticalPartyDto request, Long id){
        PoliticalPartyModel partyModel = politicalPartyRepository.findById(id).orElseThrow(PoliticalPartyNotFoundException::new);
        modelMapper.map(request, partyModel);
        politicalPartyRepository.save(partyModel);
    }


}