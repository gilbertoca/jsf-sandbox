package jsf.sandbox.repository;

import java.util.List;
import javax.inject.Inject;
import jsf.sandbox.model.Person;
import org.jglue.cdiunit.AdditionalPackages;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeData;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeJpa;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@SupportDeltaspikeData 
@SupportDeltaspikeJpa 
@AdditionalPackages(jsf.sandbox.config.EntityManagerProducer.class)
public class PersonRepositoryTest {

    @Inject
    private PersonRepository repo;

    @Test
    public void findAll() {
        List<Person> result = repo.findAll();

    }

}
