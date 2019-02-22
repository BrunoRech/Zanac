package br.udesc.ppr55.zanac;

import java.awt.EventQueue;

public class Zanac {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				GameLoop tela = new GameLoop();
				tela.setVisible(true);
			}});

	}

}
