package jsf.sandbox.deltaspike.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.sandbox.deltaspike.model.Gender;
import jsf.sandbox.deltaspike.model.Person;
import jsf.sandbox.deltaspike.service.PersonService;

@ViewScoped
@Named
public class PersonController implements Serializable{
    @Inject
    private PersonService service;
    
    @PostConstruct
    private void init(){
        Person p = new Person();
        p.setName("DeltaSpike Guy!");
        p.setAge(15);
        p.setGender(Gender.FEMALE);
        service.save(p);        
    }
    public List<Person> getPeople(){
        List<Person> result = service.findAll();
        return result;
    }
}
