package com.stcode.bootstrap.utils;

import java.util.UUID;

public class GenerateId {

    public static String generateId(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

}
