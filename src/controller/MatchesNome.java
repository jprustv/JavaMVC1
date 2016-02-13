package controller;
import model.*;

public class MatchesNome implements Matches{
	public boolean matches(String texto, Musica m){
		if (m.getNome().toLowerCase().contains(texto.toLowerCase())) return true;
		else if (m.getNomeArtista().toLowerCase().contains(texto.toLowerCase())) return true;
		else return false;
	}
}
