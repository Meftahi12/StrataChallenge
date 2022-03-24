package strata.coding.challenge.models;

public class ConversionRequest {
	
	//Object used to send infos in the POST/GET requests
	private String conversionType;
	private String toConvert;
	
	
	public ConversionRequest() {
		this.conversionType = "0";
		this.toConvert = "";
	}
	
	public ConversionRequest(String conversionType, String toConvert) {
		this.conversionType = conversionType;
		this.toConvert = toConvert;
	}

	public String getConversionType() {
		return conversionType;
	}
	
	public void setConversionType(String conversionType) {
		this.conversionType = conversionType;
	}
	
	public String getToConvert() {
		return toConvert;
	}
	
	public void setToConvert(String toConvert) {
		this.toConvert = toConvert;
	}

}
