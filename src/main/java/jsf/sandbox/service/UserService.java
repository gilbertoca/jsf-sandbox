package jsf.sandbox.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsf.sandbox.model.User;


@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public User find(String username, String password) {
        List<User> found = em.createNamedQuery("User.find", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        return found.isEmpty() ? null : found.get(0);
    }

    @Produces
    @Named("users")
    @RequestScoped
    public List<User> list() {
        return em.createNamedQuery("User.list", User.class).getResultList();
    }

    public Long create(User user) {
        em.persist(user);
        return user.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

}
