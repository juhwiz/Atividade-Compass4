package br.com.compass.avaliacao4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.avaliacao4.models.AssociatesModel;
import br.com.compass.avaliacao4.models.PoliticalOffice;

public interface AssociatesRepository extends JpaRepository<AssociatesModel, Long>{
    List<AssociatesModel> findByCargoPolitico(PoliticalOffice cargoPolitico);

}
