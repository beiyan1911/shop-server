package com.beiyan.shop.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;


public class ResultUtil {


    public static final String success = "{\"success\":1}";

    public static String successMsg(String msg) {
        return "{\"success\":1,\"msg\":\"" + msg + "\"}";
    }

    public static String errMsg(String msg) {
        return "{\"success\":0,\"msg\":\"" + msg + "\"}";
    }

    public static String successData(Object object) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", 1);
        res.put("data", object);
        return JSON.toJSONString(res, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }
}
