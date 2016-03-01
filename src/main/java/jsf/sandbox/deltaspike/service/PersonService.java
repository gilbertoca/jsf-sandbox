package jsf.sandbox.deltaspike.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import jsf.sandbox.deltaspike.model.Person;
import jsf.sandbox.deltaspike.repository.PersonRepository;

@Stateless
public class PersonService {

    @Inject
    PersonRepository personRepository;
//    @Inject
//    <Entity>Repository <entity>Repository;

    public void save( Person value) {
        personRepository.save(value);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
