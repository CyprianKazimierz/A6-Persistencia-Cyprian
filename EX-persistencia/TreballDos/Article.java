public class Article{
	//attr
	private String codi;
	private String article;
	private String quantitat;
	private String preu;
	private String preuQuantitat;
	
	//constructor
	public Article(String codi, String article, String quantitat, String preu, String preuQuantitat){
		this.codi=codi;
		this.article=article;
		this.quantitat=quantitat;
		this.preu=preu;
		this.preuQuantitat=preuQuantitat;
	}
	
	//gettr
	public String getCodi(){
		return codi;
	}
	public String getArticle(){
		return article;
	}
	public String getQuantitat(){
		return quantitat;
	}
	public String getPreu(){
		return preu;
	}
	public String getPreuQuantitat(){
		return preuQuantitat;
	}
	
	//settr
	public void setArticle(String article){
		this.article=article;
	}
	public void setQuantitat(String quantitat){
		this.quantitat=quantitat;
	}
	public void setPreu(String preu){
		this.preu=preu;
	}
	public void setPreuQuantitat(String preuQuantitat){
		this.preuQuantitat=preuQuantitat;
	}
	
	//Tostring
	public String toString(){
		return "Article{ "+codi+", producte: "+article+", quantitat: "+quantitat+", preu: "+preu+", preu*quantitat: "+preuQuantitat+" }"; 
	}
}