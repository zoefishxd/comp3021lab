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
		String[] karray = keywords.split(" ");
		List<Note> snote = new ArrayList<Note>();
		for (Note note : this.notes){
			boolean Flag=true;
			int i=0;
			while((i<karray.length)&&(Flag)){
				Flag=false;
				if(note instanceof TextNote)
				{
					do{
						if (karray[i].equalsIgnoreCase("or"))
						i++;
						if ((note.getTitle().toLowerCase().indexOf(karray[i].toLowerCase())>=0)||(((TextNote) note).content.toLowerCase().indexOf(karray[i].toLowerCase())>=0))
						{Flag=true;}
						i++;
					} while ((i<karray.length)&&(karray[i].equalsIgnoreCase("or")));
				}else {
					do {
						if (karray[i].equalsIgnoreCase("or"))
						i++;
						if ((note.getTitle().toLowerCase().indexOf(karray[i].toLowerCase())>=0))
						{Flag=true;}
						i++;
					} while ((i<karray.length)&&(karray[i].equalsIgnoreCase("or")));
				}
			}
			if (Flag)
				snote.add(note);
		}
		return snote;

	}
	
//	public List<Note> searchNotes(String keywords){
//		List<Note> result = new ArrayList<Note>();
//		String[] splited = keywords.split(" ");
//		List<String> orArray = new ArrayList<String>();
//		List<String> andArray = new ArrayList<String>();
//		int i = 0;
//		//int j = 1;
//		while(i<=splited.length-1){
//			if(splited[i+1].toLowerCase().compareTo("or")==0){
//				String arr = "";
//				while(splited[i+3].toLowerCase().compareTo("or")==0){
//					arr = arr + splited[i];
//					i += 2;
//				}
//				arr = arr + splited[i] + splited[i+2];
//				i += 2;
//				orArray.add(arr);
//			}else{
//				
//			}
//		}
//		
//		//scan through each notes
//		for(Note note : notes){
//			if (note instanceof TextNote){
//				String title = note.getTitle();
//				String content = note.getContent();
//				Boolean tf = true;
//			}
//			else{
//				String title = note.getTitle();
//				String content = note.getContent();
//				Boolean tf = true;
//			}
//		}
//		return result;
//	}
	
//	public List<Note> searchNotes(String keywords){
//		List<Note> result = new ArrayList<Note>();
//		List<Integer> or = new ArrayList<Integer>();
//		List<Integer> and = new ArrayList<Integer>();
//		String[] splited = keywords.split(" ");
//		List<String> orArray = new ArrayList<String>();
//		List<String> andArray = new ArrayList<String>();
//		int i = 0;
//		//int j = 1;
//		while(i<=splited.length-1){
//			String arr = "";
//			/*while (splited[i+1].toLowerCase().compareTo("or")==0){
//				arr = arr + splited[i] + " ";
//				i += 2;
//			}
//			arr = arr + splited [i];
//			orArray.add(arr);*/
//			if (splited[i+1].toLowerCase().compareTo("or")==0){
//				arr = " " + splited[i];
//				while (splited[i+3].toLowerCase().compareTo("or")==0){
//					arr = arr + splited[i] + " ";
//					i += 2;
//				}
//			}else{
//				//andArray.add(splited[i]);
//				while (splited[i+3].toLowerCase().compareTo("and")==0){
//					
//				}
//			}
//			System.out.println(arr);
//		}
//		
//		//scan through each notes
//		for(Note note : notes){
//			if (note instanceof TextNote){
//				String title = note.getTitle();
//				String content = note.getContent();
//				Boolean tf = true;
//				/*while (i <= splited.length-1){
//					//if next word == or 
//					if(splited[i+1].toLowerCase().compareTo("or")==0){
//						//if contain already, jump 3
//						if(title.toLowerCase().contains(splited[i].toLowerCase()) || title.toLowerCase().contains(splited[i+2].toLowerCase()) || content.toLowerCase().contains(splited[i].toLowerCase()) || content.toLowerCase().contains(splited[i+2].toLowerCase())){
//							i+=3;
//						}else{
//							tf = false;
//							break;
//						}
//					}else{
//						if(title.toLowerCase().contains(splited[i].toLowerCase()) || content.toLowerCase().contains(splited[i].toLowerCase())){
//							i+=1;
//						}else{
//							tf = false;
//							break;
//						}
//					}
//				}*/
//				if(tf==true){
//					result.add(note);
//				}
//			}
//			else{
//				String title = note.getTitle();
//				Boolean tf = true;
//				int i = 0;
//				while (i <= splited.length-1){
//					//if next word == or 
//					if(splited[i+1].toLowerCase().compareTo("or")==0){
//						//if contain already, jump 3
//						if(title.toLowerCase().contains(splited[i].toLowerCase()) || title.toLowerCase().contains(splited[i+2].toLowerCase())){
//							i+=3;
//						}else{
//							tf = false;
//							break;
//						}
//					}else{
//						if(title.toLowerCase().contains(splited[i].toLowerCase())){
//							i+=1;
//						}else{
//							tf = false;
//							break;
//						}
//					}
//				}
//				if(tf==true){
//					result.add(note);
//				}
//			}
//		}
//		return result;
//	}
	
	public boolean removeNotes(String title){
//		int n1=notes.indexOf(new TextNote(title));
//		if (n1>=0){
//			notes.remove(n1);
//			return true;
//		}
//		return false;
		for(Note n : notes){
			if(n.getTitle().equals(title)){
				notes.remove(n);
				return true;
			}
		}
		return false;
	}
	
}
