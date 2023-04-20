package document;

public class DVD extends AbstrDocument {
	private boolean SeizeAnsPlus;
	
	public DVD(String titre, boolean SeizeAnsPlus) {
		super(titre);
		this.SeizeAnsPlus = SeizeAnsPlus;
	}
}
