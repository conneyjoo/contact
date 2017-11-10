package com.jinshun.contact.utils;

import java.io.Serializable;

public class LanguageEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7608396690865211966L;

	private Long idx;
	
	private String languageType;
	
	private String multiName;

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public String getMultiName() {
		return multiName;
	}

	public void setMultiName(String multiName) {
		this.multiName = multiName;
	}
	
	
}
