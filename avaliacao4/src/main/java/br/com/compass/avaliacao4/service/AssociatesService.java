package br.com.compass.avaliacao4.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.compass.avaliacao4.dto.request.RequestAssociacionDto;
import br.com.compass.avaliacao4.dto.request.RequestAssociatesDto;
import br.com.compass.avaliacao4.dto.response.ResponseAssociatesDto;
import br.com.compass.avaliacao4.exceptions.AssociateNotFoundException;
import br.com.compass.avaliacao4.exceptions.PoliticalPartyNotFoundException;
import br.com.compass.avaliacao4.models.AssociatesModel;
import br.com.compass.avaliacao4.models.PoliticalOffice;
import br.com.compass.avaliacao4.models.PoliticalPartyModel;
import br.com.compass.avaliacao4.repository.AssociatesRepository;
import br.com.compass.avaliacao4.repository.PoliticalPartyRepository;


@Service
public class AssociatesService {
    
    @Autowired
    private AssociatesRepository associatesRepository;

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseAssociatesDto postAssociates(@Valid RequestAssociatesDto associates){
        AssociatesModel model = modelMapper.map(associates, AssociatesModel.class);
        AssociatesModel saveModel = associatesRepository.save(model);
        return modelMapper.map(saveModel, ResponseAssociatesDto.class);
    }

    public List<ResponseAssociatesDto> getAssociates(){
        List<AssociatesModel> allAssociates = associatesRepository.findAll();
        List<ResponseAssociatesDto> associatesDtos = allAssociates.stream().map(associatesModel -> 
            modelMapper.map(associatesModel, ResponseAssociatesDto.class))
            .collect(Collectors.toList());
        
        return associatesDtos;
    }
    
    public List<ResponseAssociatesDto> getAssociatesBySort(){
        List<AssociatesModel> allAssociates = associatesRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        List<ResponseAssociatesDto> associatesDtos = allAssociates.stream().map(associatesModel -> 
            modelMapper.map(associatesModel, ResponseAssociatesDto.class))
            .collect(Collectors.toList());
        return associatesDtos;
    }

    public List<ResponseAssociatesDto> getAssociatesByOffice(PoliticalOffice cargoPolitico){
        List<AssociatesModel> result = associatesRepository.findByCargoPolitico(cargoPolitico);
        List<ResponseAssociatesDto> associatesDtos = result.stream().map(associatesModel -> 
            modelMapper.map(associatesModel, ResponseAssociatesDto.class))
            .collect(Collectors.toList());
        return associatesDtos;
    }

    public ResponseAssociatesDto getById(Long id){
        AssociatesModel associatesModel = associatesRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        return modelMapper.map(associatesModel, ResponseAssociatesDto.class);
    }

    public void delete(Long id){
        associatesRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        associatesRepository.deleteById(id);
    }

    public void update(RequestAssociatesDto request, Long id){
        AssociatesModel associatesModel = associatesRepository.findById(id).orElseThrow(AssociateNotFoundException::new);
        modelMapper.map(request, associatesModel);
        associatesRepository.save(associatesModel);
    }

    public void updatePoliticalParty(@Valid RequestAssociacionDto association){
        AssociatesModel model = associatesRepository.findById(association.getIdAssociado()).orElseThrow(AssociateNotFoundException::new);
        PoliticalPartyModel partyModel = politicalPartyRepository.findById(association.getIdPartido()).orElseThrow(PoliticalPartyNotFoundException::new);
        
        model.setPartido(partyModel);
        partyModel.setAssociados(model);
        associatesRepository.save(model);
        politicalPartyRepository.save(partyModel);
    }   

    public void deleteAssociacion(Long idAssociado, Long idPartido){
        AssociatesModel model = associatesRepository.findById(idAssociado).orElseThrow(AssociateNotFoundException::new);
        PoliticalPartyModel partyModel = politicalPartyRepository.findById(idPartido).orElseThrow(PoliticalPartyNotFoundException::new);
        model.setPartido(null);
        partyModel.setAssociados(null);
        associatesRepository.save(model);
        politicalPartyRepository.save(partyModel);
    }



}
