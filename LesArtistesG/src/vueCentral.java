import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class vueCentral extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JPanel northPanel;
	private JPanel centerPanel;
	
	public vueCentral() {
		super(new BorderLayout());
		this.northPanel = new vueAjouterArtiste();
		this.centerPanel = centerPanel();
		
		add(this.northPanel, BorderLayout.NORTH);
		add(this.centerPanel(), BorderLayout.CENTER);
	}
	
	public void setNorthPanel(JPanel jp) {
		this.northPanel = jp;
	}
	
	public JPanel centerPanel() {
		JPanel jp = new JPanel();
		
		
		
		JTable table = new JTable();
		JScrollPane scroListe = new JScrollPane(table);
		
		jp.add(scroListe);
		return new JPanel();
	}
	
	public String[] chargerDonnes() {
		gestionArtistes ga = new gestionArtistes();
		ArrayList<Artiste> result = ga.getTabArtiste();
		
		for
	}
}
