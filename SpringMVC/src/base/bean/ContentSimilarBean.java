package base.bean;

public class ContentSimilarBean {

	String contentSource;
	String contentDest;
	double Similar;
	
	public ContentSimilarBean(){
		
	}
	
	public ContentSimilarBean(String contentSource,String contentDest,double Similar){
		this.contentSource=contentSource;
		this.contentDest=contentDest;
		this.Similar=Similar;
	}

	public String getContentSource() {
		return contentSource;
	}

	public void setContentSource(String contentSource) {
		this.contentSource = contentSource;
	}

	public String getContentDest() {
		return contentDest;
	}

	public void setContentDest(String contentDest) {
		this.contentDest = contentDest;
	}

	public double getSimilar() {
		return Similar;
	}

	public void setSimilar(double similar) {
		Similar = similar;
	}

}
