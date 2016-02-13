package model;

import java.util.List;

import view.Observer;

public interface Subject {
	
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObserversAdd();
	public void notifyObserversSearch(List result);
	public void notifyObserversAddMusica();

}
