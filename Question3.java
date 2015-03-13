/**
 * Class to demonstrate the function of the hierarchical filing system
 * which implements the composite pattern.
 *
 */
public class Question3 {
	public static void main(String[] args){
		// Create document and folder components
		Document myDocument = new Document (2, "MyDocument");
		Document testDocument = new Document (2, "TestDocument");
		
		Folder myFolder = new Folder ("MyFolder");
		Document anotherDocument = new Document (2, "AnotherDocument");
		Document againAnotherDocument = new Document (2, "AgainAnotherDocument");
		Folder anotherFolder = new Folder ("AnotherFolder");
		Document moreWork = new Document (2, "MoreWork");
		Folder additionalFolder = new Folder ("AdditionalFolder");
		Document bigDocument = new Document (2, "BigDocument");
		Document littleDocument = new Document (2, "LittleDocument");
		Document lastDocument = new Document (2, "LastDocument");
		Folder resources = new Folder ("Resources");
		
		// Add the components to an a top most resource folder
		resources.add(myDocument);
		resources.add(testDocument);
		resources.add(myFolder);
		resources.add(littleDocument);
		resources.add(lastDocument);
		resources.add(additionalFolder);
		
		// Add documents and folders to an existing folder to nest
		// folders and documents.
		myFolder.add(anotherDocument);
		myFolder.add(againAnotherDocument);
		myFolder.add(anotherFolder);
		anotherFolder.add(moreWork);
		additionalFolder.add(bigDocument);
		
		// Display the contents of the highest folder in the hierarchy, the folder "resource"
		System.out.println("Contents:");
		System.out.println (resources.toString());
		// Display the total number of "bytes" contained within the folder "resource"
		System.out.println("Size in bytes: "+ resources.getSizeInBytes()+" bytes");
		// Display the number of documents contained within the folder "resource"
		System.out.println ("Total Number of Docs: "+resources.getNumDocuments());
		
	}
}