package jsf.sandbox.deltaspike.repository;

import java.util.List;
import javax.inject.Inject;
import jsf.sandbox.deltaspike.model.Gender;
import jsf.sandbox.deltaspike.model.Person;
import org.jglue.cdiunit.AdditionalPackages;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeData;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author gilbertoca
 */
@RunWith(CdiRunner.class)
@SupportDeltaspikeData
@AdditionalPackages(jsf.sandbox.config.EntityManagerProducer.class)
public class PersonRepositoryTest {
    @Inject
    private PersonRepository repo;

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
