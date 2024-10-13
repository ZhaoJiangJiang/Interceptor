package com.example.interceptor.context;

public class BaseContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentUserName(String userName) {
        threadLocal.set(userName);
    }

    public static String getCurrentUserName() {
        return threadLocal.get();
    }
}
