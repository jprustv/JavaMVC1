package model;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import view.Observer;
import controller.*;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;


public class Acervo implements Subject {
	private List<Observer> observers;
	
	ObjectContainer artistas = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), 
			"artistas.db4o");
	
	private List<Artista> artistasList;
	
	private VagalumeConnection vc = new VagalumeConnection();
	
	public Acervo(){
		observers = new LinkedList();
		artistasList = new LinkedList();
	}
	
	public void fechaDB(){
		artistas.close();
	}
	
	public List<Artista> getListaArtistas(){
		//List <Artista> artists = artistas.query(Artista.class);
		return artistasList;
	}
	
	public Artista getArtista(String nomeArtista){
		for (Artista a:getListaArtistas()){
			if (a.matches(nomeArtista)) return a;
		}
		return null;
	}
	
	public void notifyObserversAdd(){
		for(Object i:observers){
			  Observer ob = (Observer)i;
			  ob.update("Artista adicionado com sucesso!");
		}  
	}
	
	public void notifyObserversSearch(List result){
		for(Object i:observers){
			  Observer ob = (Observer)i;
			  ob.update(result);
		  }
	}
	
	public void notifyObserversAddMusica(){
		for (Object i:observers){
			Observer ob = (Observer)i;
			ob.update("Música adicionada com sucesso!");
		}
	}
	
	public void buscarMusica(String texto){
		List<Musica> musicasEncontradas = new LinkedList();
		Query q = artistas.query();
		q.constrain(Artista.class);
		ObjectSet<Artista> r = q.execute();
		for (Artista artist: r){
	      for (Musica m:artist.getMusicas()){
	    	  MatchesNome mn = new MatchesNome();
	    	  MatchesTrecho mt = new MatchesTrecho();
	    	  // Busca no nome e depois no trecho
	    	  m.setMatches(mn);
	    	  if (m.matches(texto)){
	    		  for (Musica mus : musicasEncontradas){
	    			  if (mus.getNome().equals(m.getNome()));
	    		  }
	    		  musicasEncontradas.add(m);
	    	  }
	    	  else{
	    		  m.setMatches(mt);
	    		  if (m.matches(texto)){
	    			  for (Musica mus: musicasEncontradas){
	    				  if (mus.getNome().equals(m.getNome()));
	    			  }
	    			  musicasEncontradas.add(m);
	    		  }
	    	  }
	      }
	    }
	    notifyObserversSearch(musicasEncontradas);
	}
	
	public void addArtista(String nome){
	//	artistasList.add(new Artista(nome));
		
		Query q = artistas.query();
		q.constrain(Artista.class);
		ObjectSet<Artista> r = q.execute();
		for (Artista a:r){
			if (a.getNomeArtista().equals(nome)) return;
		}
		
		Artista art = new Artista(nome);
		artistas.store(art);
		artistas.commit();
		notifyObserversAdd();
	}
	
	public void addMusica(String nomeMusica, String nomeArtista){
		Query q = artistas.query();
		q.constrain(Artista.class);
		ObjectSet<Artista> r = q.execute();
		
		for(Artista a:r){
			
			if(a.getNomeArtista().equals(nomeArtista)){
				for (Musica m: a.getMusicas()){
					if (m.getNome().equals(nomeMusica)) return;
				}
				a.addMusica(nomeMusica, vc.getLyrics(a.getNomeArtista(), nomeMusica));
				artistas.store(a.getMusicas());
				artistas.commit();				
				
				notifyObserversAddMusica();
				
			}
		}
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
		
	}


	public void removeObserver(Observer o) {
		observers.remove(o);
		
	}
	
}
