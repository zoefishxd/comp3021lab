package base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class Folder implements Comparable<Folder>,Serializable{

	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
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
	
	public int compareTo(Folder f){
		String name1 = this.name;
		String name2 = f.name;
		int i = name1.compareTo(name2);
		if(i<0)/*name2>name1*/{
			return -1;
		}
		else if(i==0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public void sortNotes(){
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>();
		List<Integer> or = new ArrayList<Integer>();
		String[] splited = keywords.split(" ");
		/*int compare1;
		int compare2;
		int compare3;
		int compare4;
		//find out all the or
		for (int i = 0; i <= splited.length-1; i++){
			//splited[i]
			compare1 = splited[i].compareTo("or");
			compare2 = splited[i].compareTo("oR");
			compare3 = splited[i].compareTo("Or");
			compare4 = splited[i].compareTo("OR");
			if((compare1 == 0)||(compare2 == 0)||(compare3 == 0)||(compare4 == 0)){
				or.add(i);
			}
		}*/
		//scan through each notes
		for(Note note : notes){
			if (note instanceof TextNote){
				String title = note.getTitle();
				String content = note.getContent();
				Boolean tf = true;
				int i = 0;
				while (i <= splited.length-1){
					//if next word == or 
					if(splited[i+1].toLowerCase().compareTo("or")==0){
						//if contain already, jump 3
						if(title.toLowerCase().contains(splited[i].toLowerCase()) || title.toLowerCase().contains(splited[i+2].toLowerCase()) || content.toLowerCase().contains(splited[i].toLowerCase()) || content.toLowerCase().contains(splited[i+2].toLowerCase())){
							i+=3;
						}else{
							tf = false;
							break;
						}
					}else{
						if(title.toLowerCase().contains(splited[i].toLowerCase()) || content.toLowerCase().contains(splited[i].toLowerCase())){
							i+=1;
						}else{
							tf = false;
							break;
						}
					}
				}
				if(tf==true){
					result.add(note);
				}
			}
			else{
				String title = note.getTitle();
				Boolean tf = true;
				int i = 0;
				while (i <= splited.length-1){
					//if next word == or 
					if(splited[i+1].toLowerCase().compareTo("or")==0){
						//if contain already, jump 3
						if(title.toLowerCase().contains(splited[i].toLowerCase()) || title.toLowerCase().contains(splited[i+2].toLowerCase())){
							i+=3;
						}else{
							tf = false;
							break;
						}
					}else{
						if(title.toLowerCase().contains(splited[i].toLowerCase())){
							i+=1;
						}else{
							tf = false;
							break;
						}
					}
				}
				if(tf==true){
					result.add(note);
				}
			}
		}
		return result;
	}
	
}
