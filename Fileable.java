/**
 * Interface for Fileable objects. Any object which implements this interface
 * can be added to the filing hierarchy and is therefore a component in the
 * composite pattern.
 */
public interface Fileable {
	
	/**
	 * Method to return the size of the fileable object in bytes
	 * @return int the number of bytes contained with the fileable object.
	 */
	public int getSizeInBytes();
	
	/**
	 * Method to describe the object in the form of a String
	 * @return String the description of the fileable object
	 */
	public String toString();
		
	/**
	 * Method to return the number of documents contained within the fileable object
	 * @return int containing the number of documents contained within the fileable object
	 */
	public int getNumDocuments();
	
}