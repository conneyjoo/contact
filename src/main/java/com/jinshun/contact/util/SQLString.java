package com.jinshun.contact.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SQLString {
	
	private StringBuffer sql = null;
	
	private List<Object> params = new ArrayList<Object>(1);
	
	public SQLString() {
		this.sql = new StringBuffer();
	}
	
	public SQLString(String sql) {
		this.sql = new StringBuffer(sql).append(" ");
	}
	
	public SQLString append(String sql) {
		this.sql.append(sql).append(" ");
		return this;
	}
	
	public SQLString append(String sql, Object... params) {
		this.sql.append(sql).append(" ");
		
		for (Object param : params) {
			addParam(param);
		}
		
		return this;
	}
	
	public SQLString deleteLast(int length) {
		this.sql.delete(this.sql.length() - length - 1, this.sql.length() - 1);
		return this;
	}

	public void addCondition(String condition, Object value) {
		if (value != null && value instanceof String && !StringUtils.isEmpty(value)) {
			append(condition, value);
		} else if (value != null) {
			append(condition, value);
		}
	}
	
	public void addParam(Object param) {
		params.add(param);
	}
	
	public Object[] getParams() {
		return params.toArray(new Object[] {});
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}
	
	public String toString() {
		return this.sql.toString();
	}
}
