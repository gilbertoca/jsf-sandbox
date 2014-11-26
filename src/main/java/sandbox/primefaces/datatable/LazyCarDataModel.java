package sandbox.primefaces.datatable;

import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a real database.
 */
public class LazyCarDataModel extends LazyDataModel<Car> {

    private CarService service;
    private Map<String, String> filters;

    public LazyCarDataModel(CarService service) {
        this.service = service;
    }

    @Override
    public int getRowCount() {
        return service.count(filters).intValue();
    }

    @Override
    public List<Car> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
        //store data filters
        this.filters = filters;
        return service.load(first, 
                pageSize, 
                sortField, 
                sortOrder == SortOrder.ASCENDING ? "asc" : sortOrder == SortOrder.DESCENDING ? "desc" : null, 
                filters);
    }

    @Override
    public Object getRowKey(Car m) {
        return m.getId();
    }

    @Override
    public Car getRowData(String rowKey) {
        return service.findById(rowKey);
    }
}
