import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class vueAjouterArtiste extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public vueAjouterArtiste() {
		super(new BorderLayout());
		add(centerPanel(), BorderLayout.CENTER);
		add(southPanel(), BorderLayout.SOUTH);
	}
	
	public JPanel centerPanel() {
		JPanel jp = new JPanel(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		
		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = 1;
		constraint.weighty = 1;
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.insets = new Insets(5, 5, 5, 5);
		constraint.gridx = 0;
		constraint.gridy = 0;
		
		for(int i = 0; i < 4; i++) {
			jp.add(new JLabel("" + i), constraint);
			constraint.gridy++;
		}
		
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridx = 1;
		constraint.gridy = 0;
		
		for (int i = 0; i < 4; i++) {
			jp.add(new JTextField(), constraint);
			constraint.gridy++;
		}
		
		return jp;
	}
	
	public JPanel southPanel() {
		JPanel jp = new JPanel(new FlowLayout());
		JButton b = new JButton("0");
		b.setSize(50, 20);
		jp.add(b);
		jp.add(new JButton("n"));
		
		return jp;
	}
}
