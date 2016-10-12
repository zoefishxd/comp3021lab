package base;

import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileInputStream; 
//import java.io.FileOutputStream; 
//import java.io.ObjectOutputStream; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.io.BufferedWriter; 
import java.io.FileWriter;

public class TextNote extends Note{
	public String content;
	
	public TextNote(String title){
		super(title);
	}
	
	public TextNote(String title, String content){
	 	super(title);
		this.content = content;
	}
	
	@Override
	public String getContent(){
		return content;
	}
	
	
	
	/** * load a TextNote from File f
	 *  * * the tile of the TextNote is the name of the file
	 *   * the content of the TextNote is the content of the file
	 *    * * @param File f */ 
	public TextNote(File f) {
		super(f.getName()); 
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	/** * get the content of a file
	 *  * * @param absolutePath of the file
	 *   * @return the content of the file */ 
	private String getTextFromFile(String absolutePath) {
		String result = "";
		// TODO return result;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try { 
			fis = new FileInputStream(absolutePath);
			in = new ObjectInputStream(fis); 
			TextNote n = (TextNote) in.readObject(); 
			result = n.getContent();
			in.close(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return result;
	}
	
	/** * export text note to file
	 *  
	 *  * @param pathFolder path of the folder where to export the note
	 *  * the file has to be named as the title of the note with extension ".txt" 
	 *  * * if the title contains white spaces " " they has to be replaced with underscores "_" * * */ 
	public void exportTextToFile(String pathFolder) {
		//TODO           
		//File file = new File( pathFolder + File. separator + + ".txt" ); 
		// TODO
		try{
			//FileOutputStream fos = new FileOutputStream(pathFolder);
		    //ObjectOutputStream oos = new ObjectOutputStream(fos); 
			String title = this.getTitle();
			String content = this.getContent();
			title = title.replaceAll(" ", "_");
			//File file = new File( pathFolder + File.separator + title + ".txt" );
			File file = new File( pathFolder + title + ".txt" );
			//File test = new File("C:/Users/Zoe/git/comp3021lab/" + title + ".txt");
			//oos.writeObject(file);
			//oos.close();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
					if (count.get(c) > b) {
						b = count.get(c);
						r = c;
					}
				}
			}
		}
		return r;
	}


}
