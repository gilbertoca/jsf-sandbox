package sandbox;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class Bean {
    private List<Item> items = new ArrayList<Item>();

    public void add() {
        items.add(new Item());
    }

    public void submit() {
        System.out.println("items: " + items);
    }

    public List<Item> getItems() {
        return items;
    }
}