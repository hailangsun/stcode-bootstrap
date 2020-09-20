package com.stcode.bootstrap.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class OptionReaderDict<T> {
    Class<T> clazz;

    public OptionReaderDict(Class<T> clazz) {
        this.clazz = clazz;
    }


    public Field[] getFields() {
        Field[] fields = null;
        Class cz = this.clazz;
        if ((cz != null) && (cz != Object.class)) {
           fields = cz.getDeclaredFields();
           cz = cz.getSuperclass();
           this.clazz = cz;
        }
       return fields;
    }
}
