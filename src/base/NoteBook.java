package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.io.Serializable;

public class NoteBook implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title){
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title){
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	private boolean insertNote(String folderName, Note note){
		Folder f = null;
		for (Folder f1 : folders){
			if(f1.getName().equals(folderName)){
				f = f1;
			}
		}
		if (f == null){
			f = new Folder(folderName);
			folders.add(f);
		}
		for (Note n : f.getNotes()){
			if (note.equals(n)){
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + "failed");
				return false;
			}
		}
		f.addNote(note);
		return true;
	}
	
	public void sortFolders(){
		for (Folder f1 : folders){
			f1.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>();
		for(Folder f1 : folders){
			result.addAll(f1.searchNotes(keywords));
		}
		return result;
	}
	
	/** * method to save the NoteBook instance to file
	 *  * * @param file, the path of the file where to save the object serialization
	 *   * @return true if save on file is successful, false otherwise */
	/*public boolean save(String file)
	{ //TODO
		FileOutputStream fos = null; 
		ObjectOutputStream out = null;
		try {
			//TODO
			fos = new FileOutputStream(file); 
			out = new ObjectOutputStream(fos); 
			out.writeObject(this); 
			out.close(); 
		} catch (Exception e) {
			e.printStackTrace();
			return false;  
		}
		return true; 
	}*/
	public boolean save(String file){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	 
	
	/** * * Constructor of an object NoteBook from an object serialization on disk
	 *  * * @param file, the path of the file for loading the object serialization */ 
	public NoteBook(String file){
		// TODO 
		//NoteBook n = (NoteBook) in.readObject();
		// TODO
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try { 
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis); 
			NoteBook n = (NoteBook) in.readObject(); 
			this.folders = n.folders;
			in.close(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void addFolder(String folderName){
		folders.add(new Folder(folderName));
	}
	
}
