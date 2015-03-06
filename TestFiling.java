
public class TestFiling {
	public static void main(String[] args){
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
		
		resources.add(myDocument);
		resources.add(testDocument);
		resources.add(myFolder);
		resources.add(littleDocument);
		resources.add(lastDocument);
		resources.add(additionalFolder);
		
		
		myFolder.add(anotherDocument);
		myFolder.add(againAnotherDocument);
		myFolder.add(anotherFolder);
		anotherFolder.add(moreWork);
		additionalFolder.add(bigDocument);
		
		System.out.println("Contents:");
		System.out.println (resources.toString());
		System.out.println("Size in bytes: "+ resources.getSizeInBytes()+" bytes");
		System.out.println ("Total Number of Docs: "+resources.getNumDocuments());
		
	}
}
