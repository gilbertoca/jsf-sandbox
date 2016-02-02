package jsf.sandbox.repository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import jsf.sandbox.model.Person;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeData;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeJpa;
import org.junit.Test;
import org.junit.runner.RunWith;

@SupportDeltaspikeJpa
@SupportDeltaspikeData
@RunWith(CdiRunner.class)
public class TestDeltaspikeTransactions {
 
  @Inject
  PersonRepository er;
  EntityManagerFactory emf;
 
  @PostConstruct
  void init() {
    emf = Persistence.createEntityManagerFactory("deltaspikePU");
  }
 
  @Produces
  @RequestScoped
  EntityManager createEntityManager() {
    return emf.createEntityManager();
  }
 
  @InRequestScope
  @Transactional
  @Test
  public void test() {
    Person t = new Person();
    er.save(t);
  }
}