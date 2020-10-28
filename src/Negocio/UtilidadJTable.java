package Negocio;

import java.util.Enumeration;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class UtilidadJTable {

    public static void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new CellRenderer());
        }
    }
}
