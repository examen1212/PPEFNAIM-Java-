package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.main;
import modele.modele;

public class vueconnection extends JFrame implements ActionListener, KeyListener{
	
	private JPanel unPanel = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se connecter");
	private JTextField txtlogin = new JTextField();
	private JPasswordField txtpwd = new JPasswordField();
	
	// Constructeur
	public vueconnection() {
		this.setTitle("Connexion au logiciel de gestion FNAIM");
		this.setBounds(200,200,500,350);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(239,216,7));
		
		// Construction du panneau
		this.unPanel.setBounds(50, 150, 400, 180);
		this.unPanel.setBackground(Color.yellow);
		this.unPanel.setLayout(new GridLayout(3,2)); // Definir une matrice (une grille)
		this.unPanel.add(new JLabel ("Login : ")); // De base le premier élément ajouter est à la position 1,1 du GridLayout
		this.unPanel.add(this.txtlogin);
		this.unPanel.add(new JLabel("Mdp : "));
		this.unPanel.add(this.txtpwd);
		this.unPanel.add(this.btAnnuler);
		this.unPanel.add(this.btSeConnecter);
		
		this.unPanel.setVisible(true);
		this.add(this.unPanel);
		
		// ajout de l'image à  la fenêtre
		ImageIcon logo = new ImageIcon ("src/image/logo-fnaim.png");
		JLabel lbLogo = new JLabel (logo);
		lbLogo.setBounds(50, 20, 400, 120);
		this.add(lbLogo);
		
		// ajout de l'icone en haut a gauche
		ImageIcon logoPetit = new ImageIcon ("src/image/logo-fnaim.png");
		this.setIconImage(logoPetit.getImage());

		//rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		
		//rendre la touche entré écoutable
		this.txtlogin.addKeyListener(this);
		this.txtpwd.addKeyListener(this);
		
		
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Annuler":
			this.txtlogin.setText("");
			this.txtpwd.setText("");
			break;
		case "Se connecter":
			traitement();
			break;
		}
		
	}
	private void traitement() {
		String login = this.txtlogin.getText();
		String mdp = new String (this.txtpwd.getPassword());
		if(login.equals("") || mdp.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les identifiants");
		}else {
			String droits = modele.verifConnection(login,mdp);
			if (droits.equals("")) {
				JOptionPane.showMessageDialog(this, "Erreur d'identifiants","Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtlogin.setText("");
				this.txtpwd.setText("");
			}else {
				JOptionPane.showMessageDialog(this, "Bienvenue ! ","Connection réussie",JOptionPane.INFORMATION_MESSAGE);
				//appel de la JFrame general
				main.rendreVisible(false);
				new vuegenerale(droits);
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			traitement();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}





