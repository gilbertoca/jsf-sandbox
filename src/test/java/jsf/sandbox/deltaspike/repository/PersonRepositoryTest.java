package jsf.sandbox.deltaspike.repository;

import java.util.List;
import javax.inject.Inject;
import jsf.sandbox.deltaspike.model.Gender;
import jsf.sandbox.deltaspike.model.Person;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class PersonRepositoryTest {
    @Inject
    private PersonRepository repo;

    @Test
    public void notNull(){
        Assert.assertNotNull(repo);
    }

    @Test
    public void saveEntity() {
        Person p = new Person();
        p.setName("name");
        p.setAge(15);
        p.setGender(Gender.FEMALE);
        repo.save(p);
        Assert.assertNotNull(p.getId());
    }
    @Test
    public void findAll() {
        Person p = new Person();
        p.setName("name");
        p.setAge(15);
        p.setGender(Gender.FEMALE);
        repo.save(p);
        Assert.assertNotNull(p.getId());
        
        List<Person> result = repo.findAll();
        org.junit.Assert.assertTrue(result.size() > 0);
    }

}
