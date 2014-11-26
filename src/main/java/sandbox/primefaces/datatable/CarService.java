package sandbox.primefaces.datatable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


//@DataSourceDefinition(name = "java:app/jdbc/myDatasource", 
// className = "org.h2.jdbcx.JdbcDataSource", 
// url = "jdbc:h2:file:data/demoData;create=true;DB_CLOSE_DELAY=-1", 
// user = "sa",
// password=""
//)
@Stateless
public class CarService {

    @PersistenceContext(unitName = "demoData")
    private EntityManager em;
    
    private final static String[] colors;
    private final static String[] brands;

    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";

        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }

    public void createCars(int size) {
        for (int i = 0; i < size; i++) {
            em.persist(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
        }
    }

//    public List<Car> createCars(int size) {
//        List<Car> list = new ArrayList<Car>();
//        for (int i = 0; i < size; i++) {
//            list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
//        }
//
//        return list;
//    }
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }

    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }

    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }

    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true : false;
    }

    public List<String> getColors() {
        return Arrays.asList(colors);
    }

    public List<String> getBrands() {
        return Arrays.asList(brands);
    }

    public Car findById(String id) {
        return em.find(Car.class, id);
    }

    public List<Car> load(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters) {

        String where = "";
        for (Map.Entry<String, String> e : filters.entrySet()) {
            where += (where.length() > 0 ? " AND " : "") + "c."+e.getKey() + " like '" + e.getValue() + "%'";
        }

        Query lazy = em.createQuery("SELECT c FROM Car c" + (where.length() > 0 ? " where " + where : "") + (sortField != null && sortOrder != null ? " order by " + "c."+sortField + " " + sortOrder : ""));
        lazy.setFirstResult(first);
        lazy.setMaxResults(pageSize);

        return lazy.getResultList();
    }

    public Long count(Map<String, String> filters) {
        String where = "";
        if (filters != null) {
            for (Map.Entry<String, String> e : filters.entrySet()) {
                where += (where.length() > 0 ? " AND " : "") + "c."+e.getKey() + " like '" + e.getValue() + "%'";
            }
        }

        return (Long) em.createQuery("SELECT COUNT(c) FROM Car c" + (where.length() > 0 ? " where " + where : "")).getSingleResult();
    }
}
