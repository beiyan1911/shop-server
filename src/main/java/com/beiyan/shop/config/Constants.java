package com.beiyan.shop.config;

public class Constants {

    //过期时间 （s）
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    //Token 剩余时间少于此时间时刷新Token（s）
    public static final long REFRESH_TOKEN_TIME = 1 * 60 * 60;
    //加密key
    public static final String SIGNING_KEY = "qnrtfve1911";
    //Token 前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    //Header
    public static final String HEADER_STRING = "Authorization";
    //Token缓存过期时间
    public static final int CACHE_EXPIRE_MINUTES = 60 * 6;
}
