package jsf.sandbox.repository;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import jsf.sandbox.model.Gender;
import jsf.sandbox.model.Person;
import org.jglue.cdiunit.AdditionalPackages;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@SupportDeltaspikeData
@AdditionalPackages(jsf.sandbox.config.EntityManagerProducer.class)
public class TestPersonRepository {

    @Inject
    private PersonRepository repo;
//    @Inject
//    private EntityManager em;

    @Test
    public void notNull(){
        //org.junit.Assert.assertNotNull(em);
        org.junit.Assert.assertNotNull(repo);
    }
    
    @Test
    public void saveEntity() {
        Person p = new Person();
        p.setName("name");
        p.setAge(15);
        p.setGender(Gender.FEMALE);
        repo.save(p);
        org.junit.Assert.assertNotNull(p.getId());
    }
    @Test
    public void findAll() {
        List<Person> result = repo.findAll();
        org.junit.Assert.assertTrue(result.size() > 0);
    }

}
