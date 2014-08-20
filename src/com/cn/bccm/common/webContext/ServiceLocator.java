package com.cn.bccm.common.webContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLocator
{
    private static ApplicationContext CONTEXT;

    public static ApplicationContext getApplicationContext(){
        if (CONTEXT == null)
            CONTEXT = new ClassPathXmlApplicationContext(
                    new String[]{
                            "classpath:spring_hibernate.xml"});
        return CONTEXT;

    }

    public static void setApplicationContext(ApplicationContext outcontext){
        CONTEXT = outcontext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
    
    public static <T> T getBean(String name, Class<T> classz) {
        return (T)getApplicationContext().getBean(name, classz);
    }
    
    
}
