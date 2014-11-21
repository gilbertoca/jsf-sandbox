package sandbox.primefaces.datatable.fix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.util.ComponentUtils;

/**
 * ref.: http://stackoverflow.com/a/24360708/269514
 *
 * @author Ceyda
 */
public class LazySelectionFeature extends org.primefaces.component.datatable.feature.SelectionFeature {

    @Override
    public void decode(FacesContext context, DataTable table) {
        String clientId = table.getClientId(context);
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        String selection = params.get(clientId + "_selection");

        if (table.isSingleSelectionMode()) {
            decodeSingleSelection(table, selection);
        } else {
            decodeMultipleSelection(context, table, selection);
        }
    }

    void decodeSingleSelection(DataTable table, String selection) {
        if (ComponentUtils.isValueBlank(selection)) {
            table.setSelection(null);
        } else {
            table.setSelection(table.getRowData(selection));
        }
    }

    void decodeMultipleSelection(FacesContext context, DataTable table, String selection) {
        Class<?> clazz = table.getValueExpression("selection").getType(context.getELContext());
        boolean isArray = clazz.isArray();

        if (!isArray && !List.class.isAssignableFrom(clazz)) {
            throw new FacesException("Multiple selection reference must be an Array or a List for datatable " + table.getClientId());
        }

        if (!isArray && !List.class.isAssignableFrom(clazz)) {
            throw new FacesException("Multiple selection reference must be an Array or a List for datatable " + table.getClientId());
        }

        if (ComponentUtils.isValueBlank(selection)) {
            if (isArray) {
                table.setSelection(Array.newInstance(clazz.getComponentType(), 0));
            } else {
                table.setSelection(new ArrayList<Object>());
            }
        } else {
            String[] rowKeys = selection.split(",");
            List<Object> selectionList = new ArrayList<Object>();

            boolean lazy = table.isLazy();
            if (lazy) {
                if (table.getSelection() != null) {
                    selectionList.addAll((List<Object>) table.getSelection());
                }
                for (int i = 0; i < rowKeys.length; i++) {
                    Object rowData = table.getRowData(rowKeys[i]);

                    if (rowData != null) {
                        if (!selectionList.contains(rowData)) {
                            selectionList.add(rowData);
                        } else {
                            selectionList.remove(rowData);
                        }
                    }
                }

            } else {

                for (int i = 0; i < rowKeys.length; i++) {
                    Object rowData = table.getRowData(rowKeys[i]);

                    if (rowData != null) {
                        selectionList.add(rowData);
                    }
                }

            }

            if (isArray) {
                Object selectionArray = Array.newInstance(clazz.getComponentType(), selectionList.size());
                table.setSelection(selectionList.toArray((Object[]) selectionArray));
            } else {
                table.setSelection(selectionList);
            }
        }
    }
}
