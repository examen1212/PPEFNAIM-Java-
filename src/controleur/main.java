package controleur;

import vue.vueconnection;

public class main {

	private static vueconnection uneConnection;
	public static void rendreVisible(boolean action) {
		main.uneConnection.setVisible(action);
	}
	
	public static void main(String[] args) {
		main.uneConnection = new vueconnection();
	}

}
