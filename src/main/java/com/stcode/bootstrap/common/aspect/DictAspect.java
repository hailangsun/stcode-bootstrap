package com.stcode.bootstrap.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stcode.bootstrap.common.OptionReaderDict;
import com.stcode.bootstrap.common.annotation.Dict;
import com.stcode.bootstrap.domain.DmMx;
import com.stcode.bootstrap.mapper.DmMxMapper;
import com.stcode.bootstrap.mapper.aopdemo.AopDemoMapper;
import com.stcode.bootstrap.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数据字典切面 先注释掉
 *
 */
@Aspect
@Component
@Slf4j
public class DictAspect {

    /**
     * 字典后缀
     */
    private static String DICT_TEXT_SUFFIX = "Text";

    @Resource
    private AopDemoMapper aopDemoMapper;

    @Resource
    private DmMxMapper dmMxMapper;

    private Map<String, Map<String,String>> tempMap = new HashMap<>();

    /**
     * 切点，切入 service 包下面的所有方法
     */
    @Pointcut("execution( * com.stcode.bootstrap.service..*.search*(..))")
    public void dict() {

    }

    @Around("dict()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time1 = System.currentTimeMillis();
        Object result = pjp.proceed();
        long time2 = System.currentTimeMillis();
        log.debug("获取JSON数据 耗时：" + (time2 - time1) + "ms");
        long start = System.currentTimeMillis();
        this.parseDictText(result);
        long end = System.currentTimeMillis();
        log.debug("解析注入JSON数据  耗时" + (end - start) + "ms");
        return result;
    }

    private void parseDictText(Object result) {
        if (result instanceof R) {
            List<JSONObject> items = new ArrayList<>();
            R rr = (R) result;
            if (rr.size() > 0) {
                List<?> list = (List<?>) rr.get("rows");
                for (Object record : list) {
                    ObjectMapper mapper = new ObjectMapper();
                    String json = "{}";
                    try {
                        // 解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
                        json = mapper.writeValueAsString(record);
                    } catch (JsonProcessingException e) {
                        log.error("Json解析失败：" + e);
                    }
                    JSONObject item = JSONObject.parseObject(json);
                    OptionReaderDict optionReader = null;
                    try {
                        optionReader = new OptionReaderDict(Class.forName(record.getClass().getName()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Field[] fields = optionReader.getFields();
                    // 解决继承实体字段无法翻译问题
                    for (Field field : fields) {
                        //解决继承实体字段无法翻译问题
                        // 如果该属性上面有@Dict注解，则进行翻译
                        if (field.getAnnotation(Dict.class) != null) {
                            // 拿到注解的dictDataSource属性的值
                            String dictType = field.getAnnotation(Dict.class).dictCode();
                            // 拿到注解的dictText属性的值
                            String text = field.getAnnotation(Dict.class).dictText();
                            //获取当前带翻译的值
                            String key = String.valueOf(item.get(field.getName()));
                            //翻译字典值对应的text值
                            String textValue = translateDictValue(dictType, key);
                            // DICT_TEXT_SUFFIX的值为，是默认值：
                            // public static final String DICT_TEXT_SUFFIX = "_dictText";
                            log.debug("字典Val: " + textValue);
                            log.debug("翻译字典字段：" + field.getName() + DICT_TEXT_SUFFIX + "： " + textValue);
                            //如果给了文本名
                            if (!StringUtils.isBlank(text)) {
                                item.put(text, textValue);
                            } else {
                                // 走默认策略
                                item.put(field.getName() + DICT_TEXT_SUFFIX, textValue);
                            }
                        }
                        // date类型默认转换string格式化日期
                        if ("java.util.Date".equals(field.getType().getName())
                                && field.getAnnotation(JsonFormat.class) == null
                                && item.get(field.getName()) != null) {
                            SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                        }
                    }
                    items.add(item);
                }
                rr.put("rows",items);
            }
        }
    }

    /**
     * 翻译字典文本
     *
     * @param dictType
     * @param key
     * @return
     */
    private String translateDictValue(String dictType, String key) {
        if (StringUtils.isEmpty(key) || "null".equals(key)) {
            return null;
        }
//        StringBuffer textValue = new StringBuffer();
//        String[] keys = key.split(",");
//        for (String k : keys) {
//            if (k.trim().length() == 0) {
//                continue;
//            }
//            /**
//             * 根据 dictCode 和 code 查询字典值，例如：dictCode:sex,code:1，返回:男
//             * 应该放在redis，提高响应速度
//             */
//            DictDomain dictData = aopDemoMapper.byTypeAndCode(dictType, key);
//            if (dictData.getName() != null) {
//                if (!"".equals(textValue.toString())) {
//                    textValue.append(",");
//                }
//                textValue.append(dictData.getName());
//            }
//            log.info("数据字典翻译: 字典类型：{}，当前翻译值：{}，翻译结果：{}", dictType, k.trim(), dictData.getName());
//        }
//        return textValue.toString();
        // 后续取值改为redis
        String textValue = null;
        if(tempMap.get(dictType) == null){
            List<DmMx> dmmxList = dmMxMapper.getDmMxListByDmm(dictType);
            Map<String,String> dmmxMap = new HashMap<>();
            for (DmMx dm:dmmxList) {
                dmmxMap.put(dm.getDmmxid(),dm.getMxmc());
            }
            tempMap.put(dictType,dmmxMap);
        }
        textValue = tempMap.get(dictType).get(key);
        return textValue;
    }
}
