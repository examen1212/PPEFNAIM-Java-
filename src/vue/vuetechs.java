package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controleur.Technicien;
import modele.modele;

public class vuetechs extends JPanel implements ActionListener{

	private JComboBox cbxTech = new JComboBox();
	private JButton btOk = new JButton("Ok");
	
	private JTextArea areaTech = new JTextArea();
	
	public vuetechs() {
		this.setBounds(20,70,660,400);
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		this.cbxTech.setBounds(50, 50, 200, 20);
		this.add(cbxTech);
		this.btOk.setBounds(280, 50, 80, 20);
		this.add(btOk);
		this.areaTech.setBounds(50, 80, 360, 150);
		this.add(this.areaTech);
		
		this.remplircbx();
		this.btOk.addActionListener(this);
		this.areaTech.setEditable(false);
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btOk) {
			String chaine = this.cbxTech.getSelectedItem().toString();
			String tab[] = chaine.split(" - ");
			int idtech = Integer.parseInt(tab[0]);
			Technicien untech = new Technicien (idtech, tab[1],tab[2], "");
			untech = modele.SelectWhereTech(untech);
			this.areaTech.setText("Technicien sélectionné : \n ID tech : "+untech.getIdtech()+"\n Nom Tech : "+untech.getNom()+"\n Prenom Tech : "+ untech.getPrenom()+ "\n Compétences : "+ untech.getCompetence());
		}
	}
	
	public void remplircbx() {
		ArrayList<Technicien> lestechniciens = modele.SelectAllTech();
		for (Technicien untech : lestechniciens) {
			this.cbxTech.addItem(untech.getIdtech()+ " - " + untech.getNom() + " - " + untech.getPrenom());
		}
	}

}
