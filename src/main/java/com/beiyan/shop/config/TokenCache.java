package com.beiyan.shop.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

import static com.beiyan.shop.config.Constants.CACHE_EXPIRE_MINUTES;

/**
 * @author： BeiYan
 * @Date： 2018-06-24 16:21
 * @Description:
 */

public class TokenCache {

    private static Cache<String, String> userToken = CacheBuilder.newBuilder().expireAfterAccess(CACHE_EXPIRE_MINUTES, TimeUnit.MINUTES).build();

    /**
     * 判断Token是否存在缓存中
     */
    public static boolean tokenExist(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        String username = userToken.getIfPresent(token);
        return !StringUtils.isEmpty(username);
    }


    public static void put(String key, String value) {
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
            userToken.put(key, value);
        }

    }


}

