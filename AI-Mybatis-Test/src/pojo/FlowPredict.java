package pojo;

public class FlowPredict {
	private String predictName;
	private String frameName;
	private String modelNAME;
	private int itemId;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPredictName() {
		return predictName;
	}

	public void setPredictName(String predictName) {
		this.predictName = predictName;
	}

	public String getFrameName() {
		return frameName;
	}

	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

	public String getModelNAME() {
		return modelNAME;
	}

	public void setModelNAME(String modelNAME) {
		this.modelNAME = modelNAME;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
