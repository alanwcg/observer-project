package interfaces;

import java.io.PrintWriter;

public interface Subject {
	
	void addObserver(Observer ob);
	void removeObserver(Observer ob);
	void notifyObservers(PrintWriter writer, String text);

}
