package document;

public class DVD extends AbstrDocument {
	private boolean autoriseMoins16Ans;
	
	public DVD(String titre, boolean autoriseMoins16Ans) {
		super(titre);
		this.autoriseMoins16Ans = autoriseMoins16Ans;
	}
}
