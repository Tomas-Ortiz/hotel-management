package Negocio;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class UtilidadJTable {

    public UtilidadJTable() {
    }

    public void centrarElementosTable(JTable table) {

        DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
        dtc.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(dtc);
            table.getColumnModel().getColumn(i).setCellRenderer(dtc);
        }
    }
}
