import java.util.ArrayList;

/**
 * Class to represent the folders in the filing hierarchy. Folders represent
 * the composites in the composite pattern.
 */
public class Folder implements Fileable{
	
	// Store the name and contents of the folder
	private String name;
	//The folder contains a reference to the 'Fileable' objects
	// contained within the folder
	private ArrayList<Fileable> contents;
	
	/**
	 * Constructor for the Folder objects
	 * @param name String containing the name of the folder
	 */
	public Folder (String name){
		this.name=name;
		// Instantiate a new ArrayList of Fileable objects
		contents = new ArrayList<Fileable>();
	}
	
	/**
	 * Method to determine the size of the folder in bytes
	 * @return 	int the size of the contents of the folder in bytes
	 */
	public int getSizeInBytes() {
		int sizeInBytes = 0;
		for (Fileable file: contents){
			sizeInBytes+= file.getSizeInBytes();
		}
		return sizeInBytes;
	}
	
	/**
	 * Method to add a Fileable object to the Folder
	 * @param file Fileable object to be added to the folder
	 */
	public void add(Fileable file){
		contents.add(file);
	}
	
	/**
	 * Method to remove a Fileable object from the Folder
	 * @param file Fileable object to be removed from the folder
	 */
	public void remove (Fileable file){
		contents.remove(file);
		
	}
	
	/**
	 * Method to calculate the number of documents contained with the Folder
	 */
	public int getNumDocuments (){
		int numContents = 0;
		// for all Fileable object within the folder
		for (Fileable file: contents){
			// Determine the number of documents contained within and add to an accumulated total
			numContents+= file.getNumDocuments();	
		}
		return numContents;
	}
	
	/**
	 * Method to return the contents of the Folder with correct indentation
	 */
	public String toString(){
		// Create a StringBuilder object to create the description of the contents of the folder
		// The folder name, and associated text is added
		StringBuilder output = new StringBuilder("Folder, "+name+" containing,\n");
		// For each Fileable object referenced in the folder
		for (Fileable file: contents){
			// call the description of this fileable component
			String filenames = (file.toString()+"\n");
			// to indent, split the returned string by the carrage returns
			String[] files = filenames.split("[\n]");
			// for each line that has been returned, add a new tab to the beginning of the String
			// and add the String to the new StringBuilder object
			for (String fileName: files){
				output.append("\t"+fileName+"\n");
			}
		}
		// Create a string from the StringBuilder object
		return output.toString();
	}
}