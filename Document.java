/**
 *	Class to represent documents in the filing system. The documents
 *	represent the leaves within the composite pattern.
 */
public class Document implements Fileable{
	
	// Store the name and size of the document in bytes
	private int sizeInBytes;
	private String name;
	
	/**
	 * Constructor for the document
	 * @param bytes	int denoting the size of the document in bytes
	 * @param name	String the name of the document
	 */
	public Document(int bytes, String name){
		this.sizeInBytes=bytes;
		this.name = name;
	}
	
	/**
	 * 	Get method for the size of the document
	 * 	@return sizeInBytes int denoting the size of the document in bytes.
	 */
	public int getSizeInBytes() {
		return sizeInBytes;
	}
	
	/**
	 * Method to override the toString() method which returns the name of the document
	 * @return name	String containing the name of the document
	 */
	public String toString(){
		return name;
	}

	/**
	 * Method to return the number of documents contained within
	 * @return 1 int each document only stores itself.
	 */
	public int getNumDocuments() {
		return 1;
	}
}