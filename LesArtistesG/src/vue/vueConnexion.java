package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.controlerConnexion;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JPasswordField;

public class vueConnexion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPasswordField textPassword;
	private JTextField textNom = new JTextField();
	private JButton btnValide;
	private JButton btnQuitter;
	private JLabel lblLesGratteuxDe;

	/**
	 * Create the frame.
	 */
	public vueConnexion( vueJFrame parent ) {
		setBackground( Color.LIGHT_GRAY );
		setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setLayout( null );
		ActionListener listeneur = new controlerConnexion( parent, this );

		settingJLabel();

		settingJTextField();

		settingJButton(listeneur);

		JLabel fontEcran = new JLabel("");
		fontEcran.setIcon(new ImageIcon(getClass().getResource("../images/ecranConnexion.png")));
		fontEcran.setBounds(10, -29, 634, 375);
		add(fontEcran);
	}

	private void settingJLabel() {
		JLabel utilisateur = new JLabel( "Nom d'utilisateur : " );
		utilisateur.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
		utilisateur.setBounds( 190, 123, 133, 14 );
		add( utilisateur );

		JLabel password = new JLabel( "Password : " );
		password.setFont( new Font( "Tahoma", Font.BOLD, 11 ) );
		password.setBounds( 228, 154, 83, 14 );
		add( password );

		lblLesGratteuxDe = new JLabel( "Les gratteux de guitar Gestion" );
		lblLesGratteuxDe.setForeground( Color.BLACK );
		lblLesGratteuxDe.setFont( new Font( "Tahoma", Font.BOLD | Font.ITALIC, 21 ) );
		lblLesGratteuxDe.setBackground( Color.RED );
		lblLesGratteuxDe.setBounds( 117, 57, 368, 38 );
		add( lblLesGratteuxDe );
	}

	private void settingJTextField() {
		textPassword = new JPasswordField();
		textPassword.setBounds(308, 151, 145, 20);
		add(textPassword);
		textPassword.setColumns(10);

		textNom = new JTextField();
		textNom.setBounds(308, 120, 145, 20);
		textNom.setColumns(10);
		add(textNom);
	}

	private void settingJButton(ActionListener listeneur) {
		btnValide = new JButton("Valider");
		btnValide.addActionListener(listeneur);
		btnValide.setBounds(216, 182, 107, 23);
		btnValide.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnValide.setToolTipText("<html><img src=\"" + getClass().getResource("../images/Valider.jpeg") + "\">");
		add(btnValide);
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(listeneur);
		btnQuitter.setBounds(333, 182, 107, 23);
		btnQuitter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnQuitter.setToolTipText("<html><img src=\"" + getClass().getResource("../images/Quitter.jpeg") + "\">");
		add(btnQuitter);
		textNom.setBounds( 308, 120, 145, 20 );
		textNom.setColumns( 10 );
		add( textNom );
	}

	/**
	 * @return the textPassword
	 */
	public String getTextPassword() {
		return new String( this.textPassword.getPassword() );
	}

	/**
	 * @return the textNom
	 */
	public String getTextNom() {
		return this.textNom.getText();
	}

}
