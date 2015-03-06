
public class Document implements Fileable{
	
	private int sizeInBytes;
	private String name;
	
	public Document(int bytes, String name){
		this.sizeInBytes=bytes;
		this.name = name;
	}
	
	public int getSizeInBytes() {
		return sizeInBytes;
	}
	
	public String toString(){
		return name;
	}

	@Override
	public int getNumDocuments() {
		return 1;
	}

	@Override
	public boolean isDocument() {
		return true;
	}
}
