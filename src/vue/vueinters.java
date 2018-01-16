package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class vueinters extends JPanel implements ActionListener{

	public vueinters() {
		this.setBounds(20,70,660,400);
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
