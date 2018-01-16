package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import modele.modele;

public class vueclients extends JPanel implements ActionListener{
	
	private JTable tableclients;
	private JPanel paneledition = new JPanel();
	private JButton btajouter = new JButton("Ajouter");
	private JButton btsupprimer = new JButton("Supprimer");
	private JButton btupdate = new JButton("Editer");
	
	private JTextField txtidclient = new JTextField();
	private JTextField txtnom = new JTextField();
	private JTextField txtprenom = new JTextField();
	private JTextField txtemail = new JTextField();
	
	public vueclients() {
		this.setBounds(20,70,660,400);
		this.setLayout(null);
		this.setBackground(Color.gray);
		//construction de la Jtable
		String entete [] = {"ID utilisateur","Nom utilisateur","Prenom utilisateur","email"};
		this.tableclients = new JTable(this.recupererlesclients(),entete) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tableclients.setEnabled(true);
		
		this.tableclients.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent m) {
				// TODO Auto-generated method stub
					int ligne = tableclients.getSelectedRow();
					txtidclient.setText(tableclients.getValueAt(ligne, 0).toString());
					txtnom.setText(tableclients.getValueAt(ligne, 1).toString());
					txtprenom.setText(tableclients.getValueAt(ligne, 2).toString());
					txtemail.setText(tableclients.getValueAt(ligne, 3).toString());
			}
		});
		
		//affichage de la Jtable dans une scroll (liste déroulante)
		JScrollPane uneScroll = new JScrollPane(this.tableclients);
		uneScroll.setBounds(20,20,600,250);
		uneScroll.setBackground(Color.black);
		this.add(uneScroll);
		
		//construction du panel edition
		this.paneledition.setBounds(20, 290, 600, 60);
		this.paneledition.setLayout(new GridLayout(2,4));
		this.paneledition.add(new JLabel("ID utilisateur"));
		this.paneledition.add(txtidclient);
		this.paneledition.add(new JLabel("Nom"));
		this.paneledition.add(txtnom);
		this.paneledition.add(new JLabel("Prenom"));
		this.paneledition.add(txtprenom);
		this.paneledition.add(new JLabel("email"));
		this.paneledition.add(txtemail);
		this.add(this.paneledition);
		
		this.btajouter.setBounds(200, 370, 100, 20);
		this.add(btajouter);
		this.btsupprimer.setBounds(350, 370, 100, 20);
		this.add(btsupprimer);
		this.btupdate.setBounds(470, 370, 100, 20);
		this.add(btupdate);
		this.txtidclient.setEditable(false);
		
		// rendre les boutons cliquables
		this.btajouter.addActionListener(this);
		this.btsupprimer.addActionListener(this);
		this.btupdate.addActionListener(this);

		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btajouter) {
			Client unclient = new Client (txtnom.getText(),txtprenom.getText(),txtemail.getText());
			modele.insertClient(unclient);
			JOptionPane.showMessageDialog(this, "Insertion réussie");
			txtnom.setText("");
			txtprenom.setText("");
			txtemail.setText("");
		}else if (e.getSource() == this.btsupprimer) {
			int idclient = Integer.parseInt(txtidclient.getText());
			Client unclient = new Client (idclient,txtnom.getText(),txtprenom.getText(),txtemail.getText());
			modele.deleteclient(unclient);
			JOptionPane.showMessageDialog(this, "Suppression réussie");
			txtidclient.setText("");
			txtnom.setText("");
			txtprenom.setText("");
			txtemail.setText("");
		}else if(e.getSource() == this.btupdate) {
			int idclient = Integer.parseInt(txtidclient.getText());
			Client unclient = new Client (idclient,txtnom.getText(),txtprenom.getText(),txtemail.getText());
			modele.updateclient(unclient);
			JOptionPane.showMessageDialog(this, "Mise à jour réussie");
			txtidclient.setText("");
			txtnom.setText("");
			txtprenom.setText("");
			txtemail.setText("");
		}
	}
	// récuperer les données sous forme de matrice (tableau)
	private Object [][] recupererlesclients(){
		ArrayList<Client> lesclients = modele.selectAllClients();
		Object [][] donnees = new Object[lesclients.size()][Client.getNbchampsclients()];
		int i = 0;
		for(Client unclient : lesclients) {
			donnees[i][0] = unclient.getIdclient()+"";
			donnees[i][1] = unclient.getNom();
			donnees[i][2] = unclient.getPrenom();
			donnees[i][3] = unclient.getEmail();
			i++;
		}
		return donnees;
	}
}
