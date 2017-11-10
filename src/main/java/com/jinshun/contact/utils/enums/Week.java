package com.jinshun.contact.utils.enums;

public enum Week {

	MONDAY("星期�?", "Monday", "Mon.", 1), 
	TUESDAY("星期�?", "Tuesday", "Tues.", 2), 
	WEDNESDAY("星期�?", "Wednesday", "Wed.", 3), 
	THURSDAY("星期�?", "Thursday","Thur.", 4), 
	FRIDAY("星期�?", "Friday", "Fri.", 5), 
	SATURDAY("星期�?","Saturday", "Sat.", 6), 
	SUNDAY("星期�?", "Sunday", "Sun.", 7);

	String name_cn;
	String name_en;
	String name_enShort;
	int number;

	Week(String name_cn, String name_en, String name_enShort, int number) {
		this.name_cn = name_cn;
		this.name_en = name_en;
		this.name_enShort = name_enShort;
		this.number = number;
	}

	public String getChineseName() {
		return name_cn;
	}

	public String getName() {
		return name_en;
	}

	public String getShortName() {
		return name_enShort;
	}

	public int getNumber() {
		return number;
	}
}