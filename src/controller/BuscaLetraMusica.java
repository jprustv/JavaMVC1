package controller;

import view.*;
import model.*;


public class BuscaLetraMusica implements Busca{
	
	private Subject model;
	private TelaPrincipal tp;
	
	public BuscaLetraMusica(TelaPrincipal tela, Subject s){
		tp = tela;
		model = s;
	}
	
	public void buscarMusica(){
		
		tp.displayBuscaLetra();	
	}

}