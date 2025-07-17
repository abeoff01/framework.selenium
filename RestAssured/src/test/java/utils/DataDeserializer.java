package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataDeserializer {
	@JsonProperty("data") 
	private UserDeserialize data;
	private SupportDeserializer support;
	
	public void setData(UserDeserialize data) {
		this.data = data;
	}
	public SupportDeserializer getSupport() {
		return support;
	}
	public void setSupport(SupportDeserializer support) {
		this.support = support;
	}
	public UserDeserialize getData() {
		// TODO Auto-generated method stub
		return data;
	}
	

}
