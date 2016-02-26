package jsf.sandbox.deltaspike.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.sandbox.deltaspike.model.Gender;
import jsf.sandbox.deltaspike.model.Person;
import jsf.sandbox.deltaspike.repository.PersonRepository;

/**
 *
 * @author gilberto.andrade
 */
@ViewScoped
@Named
public class PersonController implements Serializable{
    @Inject
    private PersonRepository repo;
    
    @PostConstruct
    private void init(){
        Person p = new Person();
        p.setName("name");
        p.setAge(15);
        p.setGender(Gender.FEMALE);
        repo.save(p);        
    }
    public List<Person> getPeople(){
        List<Person> result = repo.findAll();
        return result;
    }
}
