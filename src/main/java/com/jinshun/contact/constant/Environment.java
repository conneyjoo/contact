package com.jinshun.contact.constant;

public class Environment {

    public static final String LOGIN_USER_KEY = "current_user";

    public static final String LOGIN_USER_ID_KEY = "current_user_id";

    public static final String ROOT_PATH = Environment.class.getResource("").getPath().substring(0, Environment.class.getResource("").getPath().indexOf("/WEB-INF/classes"));

    public static final String UPLOAD_PATH = "/upload/";
}
