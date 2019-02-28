package br.udesc.ppr55.zanac;

import java.awt.EventQueue;

import br.udesc.ppr55.zanac.core.GameLoop;

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
