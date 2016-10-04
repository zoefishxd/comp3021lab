package base;

import java.util.Date;
import java.io.Serializable;

public class Note implements Comparable<Note>,Serializable{
	private Date date;
	private String title;
	
	public Note(String title){
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getContent(){
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	public int compareTo(Note o){
		// TODO Auto-generated method stub
		// compare base in creation date
		// more recently is considered as smaller
		// 0=smaller, 1=larger
		int i = 0;
		Date date1 = this.date;
		Date date2 = o.date;
		if (date1.after(date2)){
			i = -1;
		}else if(date1.before(date2)){
			i = 1;
		}else{
			i = 0;
		}
		return i;
	}
	
	public String toString(){
		return date.toString() + "\t" + title;
	}
	
}



