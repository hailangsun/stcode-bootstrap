//package com.stcode.bootstrap.common;
//
//
//import com.stcode.bootstrap.common.annotation.Option;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//
//public class OptionReader<T>
//{
//    Class<T> clazz;
//
//    public OptionReader(Class<T> clazz)
//    {
//        this.clazz = clazz;
//    }
//
//    public boolean editable(String filedName)
//    {
//        try
//        {
//            Field field = this.clazz.getDeclaredField(filedName);
//            Annotation[] aarray = field.getAnnotations();
//            for (Annotation a : aarray) {
//                if ((a instanceof Option))
//                {
//                    Option c = (Option)a;
//                    return c.editable();
//                }
//            }
//            return false;
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return false;
//    }
//
//    public String getName(String filedName)
//    {
//        try
//        {
//            Field field = this.clazz.getDeclaredField(filedName);
//            Annotation[] aarray = field.getAnnotations();
//            for (Annotation a : aarray) {
//                if ((a instanceof Option))
//                {
//                    Option c = (Option)a;
//                    return c.name();
//                }
//            }
//            return null;
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public String getName(Field field)
//    {
//        try
//        {
//            Annotation[] aarray = field.getAnnotations();
//            for (Annotation a : aarray) {
//                if ((a instanceof Option))
//                {
//                    Option c = (Option)a;
//                    return c.name();
//                }
//            }
//            return null;
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public Map<String, Field> getFieldMapByOption()
//    {
//        Map<String, Field> fieldMap = new HashMap();
//
//        Class cz = this.clazz;
//        while ((cz != null) && (cz != Object.class))
//        {
//            Field[] fields = cz.getDeclaredFields();
//            for (Field field : fields)
//            {
//                String fieldName = field.getName();
//
//                String tt = field.getName();
//                if (tt != null) {
//                    fieldMap.put(tt, field);
//                }
//            }
//            cz = cz.getSuperclass();
//            this.clazz = cz;
//        }
//        return fieldMap;
//    }
//}
