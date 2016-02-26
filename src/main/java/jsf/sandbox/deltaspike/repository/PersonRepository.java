package jsf.sandbox.deltaspike.repository;

import jsf.sandbox.deltaspike.model.Person;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;


@Repository
public interface PersonRepository extends EntityRepository<Person, Long>
{
    @Query("select p from Person p where p.name = ?1")
    Person findByName(String name);

}