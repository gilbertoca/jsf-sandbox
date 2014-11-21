package sandbox.primefaces.datatable.fix;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.DataTableRenderer;
import org.primefaces.component.datatable.feature.CellEditFeature;
import org.primefaces.component.datatable.feature.DataTableFeature;
import org.primefaces.component.datatable.feature.DataTableFeatureKey;
import org.primefaces.component.datatable.feature.DraggableColumnsFeature;
import org.primefaces.component.datatable.feature.FilterFeature;
import org.primefaces.component.datatable.feature.PageFeature;
import org.primefaces.component.datatable.feature.ResizableColumnsFeature;
import org.primefaces.component.datatable.feature.RowEditFeature;
import org.primefaces.component.datatable.feature.RowExpandFeature;
import org.primefaces.component.datatable.feature.ScrollFeature;
import org.primefaces.component.datatable.feature.SortFeature;

/**
 * ref.: http://stackoverflow.com/a/24360708/269514
 * @author Ceyda
 */
public class LazyDataTableRenderer extends DataTableRenderer {
static Map<DataTableFeatureKey,DataTableFeature> FEATURES;

    static {
        FEATURES = new HashMap<DataTableFeatureKey,DataTableFeature>();
        FEATURES.put(DataTableFeatureKey.DRAGGABLE_COLUMNS, new DraggableColumnsFeature());
        FEATURES.put(DataTableFeatureKey.FILTER, new FilterFeature());
        FEATURES.put(DataTableFeatureKey.PAGE, new PageFeature());
        FEATURES.put(DataTableFeatureKey.SORT, new SortFeature());
        FEATURES.put(DataTableFeatureKey.RESIZABLE_COLUMNS, new ResizableColumnsFeature());
        FEATURES.put(DataTableFeatureKey.SELECT, new LazySelectionFeature());
        FEATURES.put(DataTableFeatureKey.ROW_EDIT, new RowEditFeature());
        FEATURES.put(DataTableFeatureKey.CELL_EDIT, new CellEditFeature());
        FEATURES.put(DataTableFeatureKey.ROW_EXPAND, new RowExpandFeature());
        FEATURES.put(DataTableFeatureKey.SCROLL, new ScrollFeature());
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        DataTable table = (DataTable) component;

        for(Iterator<DataTableFeature> it = FEATURES.values().iterator(); it.hasNext();) {
            DataTableFeature feature = it.next();

            if(feature.shouldDecode(context, table)) {
                feature.decode(context, table);
            }
        }

        decodeBehaviors(context, component);        
    }
}
