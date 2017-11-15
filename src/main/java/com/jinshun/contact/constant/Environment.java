package com.jinshun.contact.constant;

public class Environment {

    public static final String CURRENT_USER_KEY = "current_user";

    public static final String CURRENT_COMPANY_KEY = "current_company";

    public static final String ROOT_PATH = Environment.class.getResource("").getPath().substring(0, Environment.class.getResource("").getPath().indexOf("/WEB-INF/classes"));

    public static final String UPLOAD_PATH = "/upload/";
}
