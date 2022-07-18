package br.com.compass.avaliacao4.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import br.com.compass.avaliacao4.models.AssociatesModel;
import br.com.compass.avaliacao4.models.PoliticalOffice;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AssociatesRepositoryTest {

    @Autowired
    private AssociatesRepository repository;

    @Test
    public void shouldBringAnAssociateByPoliticalOffice(){
        PoliticalOffice politicalOffice = PoliticalOffice.PRESIDENTE;
        List<AssociatesModel> associate = repository.findByCargoPolitico(politicalOffice);
        Assert.assertNotNull(associate);
        Assert.assertEquals(politicalOffice, associate.get(0).getCargoPolitico());
    }
}
