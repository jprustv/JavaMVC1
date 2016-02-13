package model;

import java.util.*;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Artista implements Comparable {
	private String nome;
	private List musicas = new LinkedList();
	
	public Artista(String nome){
		this.nome = nome;
	}
		
	public void addMusica(String nome, String letra){
		Musica m = new Musica(nome, letra, this.nome);
		musicas.add(m);
	}
	
	public List<Musica> getMusicas(){
		return musicas;
	}
	
	public String getNomeArtista(){
		return nome;
	}
	
	public void setNomeArtista(String nomeArtista){
		this.nome = nomeArtista;
	}
	
	public boolean matches(String nomeArtista){
		if (nomeArtista.equals(this.getNomeArtista())) return true;
		else return false;
	}

	@Override
	public int compareTo(Object o) {
		Artista a = (Artista) o;
		if (this.getNomeArtista().charAt(0) > a.getNomeArtista().charAt(0)) return 1;
		else if (this.getNomeArtista().charAt(0) < a.getNomeArtista().charAt(0)) return -1;
		else return 0;
	}
	
}
