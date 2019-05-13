package com.zab.bootdecoupling.utils;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Set;

@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public BeanUtils() {
        Reflections reflections = new Reflections(this.getClass(), new FieldAnnotationsScanner());
        Set<Field> fields = reflections.getFieldsAnnotatedWith(Resource.class);

        for (Field field : fields) {
            String simpleName = field.getType().getSimpleName();
            String beanName = simpleName.replace(String.valueOf(simpleName.charAt(0)), String.valueOf(simpleName.charAt(0)).toLowerCase());
            Object bean = getBean(beanName);
            if (bean == null) {
                return;
            }
            try {
                field.setAccessible(true);
                field.set(this, bean);
            } catch (Exception e) {
                continue;
            }
        }


    }
}
