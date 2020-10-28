package Negocio;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class HeaderRenderer extends JLabel implements TableCellRenderer {

    public HeaderRenderer() {
        configurarColumnasTabla();
    }

    private void configurarColumnasTabla() {
        setFont(new Font("Bodoni MT Condensed", Font.BOLD, 20));
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.darkGray));
        setHorizontalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(getWidth(), 40));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
}
