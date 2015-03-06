import java.util.ArrayList;


public class Folder implements Fileable{

	private String name;
	private ArrayList<Fileable> contents;
	
	public Folder (String name){
		this.name=name;
		contents = new ArrayList<Fileable>();
	}
	
	@Override
	public int getSizeInBytes() {
		int sizeInBytes = 0;
		for (Fileable file: contents){
			sizeInBytes+= file.getSizeInBytes();
		}
		return sizeInBytes;
	}
	

	public void add(Fileable f){
		contents.add(f);
	}
	
	public void remove (Fileable f){
		contents.remove(f);
		
	}
	
	public int getNumDocuments (){
		int numContents = 0;
		for (Fileable file: contents){
			numContents+= file.getNumDocuments();	
		}
		return numContents;
	}
	
	public String toString(){
		StringBuilder output = new StringBuilder("Folder, "+name+" containing,\n");
		for (Fileable file: contents){
			String filenames = (file.toString()+"\n");
			String[] files = filenames.split("[\n]");
			for (String fileName: files){
				output.append("\t"+fileName+"\n");
			}
		}
		return output.toString();
	}

	@Override
	public boolean isDocument() {
		return false;
	}
}
