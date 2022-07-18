package br.com.compass.avaliacao4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.avaliacao4.models.Ideology;
import br.com.compass.avaliacao4.models.PoliticalPartyModel;

import java.util.List;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalPartyModel, Long>{
    public List<PoliticalPartyModel> findByIdeologia(Ideology ideologia);
}
