package view;
import java.util.*;
import model.*;
import controller.Busca;

public class View implements Observer {
	private Busca busca;
	private List encontradas = new LinkedList();
	
	public void update(List l){
		encontradas = l;
		busca.buscarMusica();
	}
	
	public void update(String msg){
		System.out.println(msg);
	}
	
	public void setBusca(Busca b){
		busca = b;
	}
	
	public void displayBuscaPorNome(){
		if (encontradas.isEmpty()){
			System.out.println("Nenhuma música encontrada!");
			return;
		}
		for (Iterator i = encontradas.iterator(); i.hasNext(); ) {
		      Musica m = (Musica)i.next();
		      System.out.println(m.getNome());
		}
		
	}
	
	public void displayBuscaPorTrecho(){
		if (encontradas.isEmpty()){
			System.out.println("Nenhuma música encontrada!");
			return;
		}
		for (Iterator i = encontradas.iterator(); i.hasNext(); ) {
		      Musica m = (Musica)i.next();
		      System.out.println(m.getNome() + "\n\n"+m.getLetra());
		      
		}
	}
	
}
