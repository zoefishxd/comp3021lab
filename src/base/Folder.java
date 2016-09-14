package base;

import java.util.ArrayList;

public class Folder {

	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note){
		notes.add(note);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public String toString(){
		int nText = 0;
		int nImage = 0;
		for(Note note : notes){
			if (note instanceof TextNote){
				nText += 1 ;
			}
			else{
				nImage += 1;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
