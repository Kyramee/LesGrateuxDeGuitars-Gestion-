package modele;


import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class modeleColonneMembre extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (value.equals(true)) {
			setIcon(new ImageIcon(getClass().getResource("../images/membre.png")));
		} else {
			setIcon(null);
		}
		
		setText("");
		setHorizontalAlignment(JLabel.CENTER);
		return this;
	}
}
