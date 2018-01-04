package hm.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private int code = 200;
	private String message;
	private Map<String, Object> data;
	private transient boolean success = true;
	private Map<String, Object> error;

	public Response() {
		data = new HashMap<>();
		error = new HashMap<>();
	}

	public void addData(String key, Object value) {
		data.put(key, value);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Map<String, Object> getError() {
		return error;
	}

	public void setError(Map<String, Object> error) {
		this.error = error;
	}

}
