package controller;
import model.*;

public class MatchesTrecho implements Matches {
	public boolean matches(String texto, Musica m){
		if (m.getLetra().toLowerCase().contains(texto.toLowerCase())) return true;
		else return false;
	}
}
