package com.jinshun.contact.util;

public class ConditionUtils {

    public static boolean checkNullOrBlank(String condition){
        boolean result = false;
        if(condition!=null && !"".equals(condition)){
            result = true;
        }
        return result;
    }


}
