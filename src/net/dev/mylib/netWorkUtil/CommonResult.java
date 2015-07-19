package net.dev.mylib.netWorkUtil;

import java.io.Serializable;

public class CommonResult implements Serializable{
	public String state;
	public String message;
	public String total;
	public String limit;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	public boolean isSuccess(){
		return "1000".equals(state);
	}
}
