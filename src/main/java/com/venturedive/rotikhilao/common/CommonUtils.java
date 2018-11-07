package com.venturedive.rotikhilao.common;

import com.venturedive.rotikhilao.exception.ApplicationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {
    public static void checkRequiredField(Long value){
        if (empty(value)){
            throw new ApplicationException("Invalid field value");
        }
    }

    public static void checkRequiredField(Integer value){
        if (empty(value)){
            throw new ApplicationException("Invalid field value");
        }
    }

    public static void checkRequiredField(String value){
        if (empty(value)){
            throw new ApplicationException("Invalid field value");
        }
    }

    public static boolean empty(Long value){
        return value == null || value <=0;
    }

    public static boolean empty(Integer value){
        return value == null || value <=0;
    }

    public static boolean empty(String value){
        return value == null || value.isEmpty();
    }
}
