package es.emasesa.intranet.webservices.jaxrs.beans;

import java.io.Serializable;

public class ResponseData implements Serializable {
	private boolean error;
	private Object data;
	private String errorCode;
	private String errorMessage;
	
	public ResponseData(boolean error, Object data, String errorCode, String errorMessage) {
		this.error = error;
		this.data = data;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public boolean getError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
