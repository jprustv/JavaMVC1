package model;

import controller.*;

public class Musica extends Artista{
	private String nome;
	private String letra;
	private Matches match;

	public Musica(String nome, String letra, String artista){
		super(artista);
		this.nome = nome;
		this.letra = letra;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	public void setMatches(Matches mat){
		match = mat;
	}
	
	public boolean matches(String textoRecebido){
		if (match.matches(textoRecebido, this)) return true;
		else return false;
	}

}
