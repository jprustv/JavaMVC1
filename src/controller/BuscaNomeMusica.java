package controller;

import view.*;
import model.*;


public class BuscaNomeMusica implements Busca{
	
	private Subject model;
	private TelaPrincipal tp;
	
	public BuscaNomeMusica(TelaPrincipal tela, Subject s){
		tp = tela;
		model = s;
	}
	
	public void buscarMusica(){
		
		tp.displayBuscaNome();	
	}

}
