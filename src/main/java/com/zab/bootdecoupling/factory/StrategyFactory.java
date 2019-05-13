package com.zab.bootdecoupling.factory;

import com.zab.bootdecoupling.annotation.Pay;
import com.zab.bootdecoupling.strategy.AgeMultiplyWeightStrategy;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class StrategyFactory {

    private StrategyFactory() {
    }

    private volatile static StrategyFactory strategyFactory = null;

    public static StrategyFactory getStrategyFactory() {
        if (strategyFactory == null) {
            synchronized(StrategyFactory.class){
                if(strategyFactory == null){
                    strategyFactory = new StrategyFactory();
                }
            }
        }
        return strategyFactory;
    }

    private static HashMap<Integer, String> hashMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.zab.bootdecoupling.strategy.impl");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Pay.class);
        for (Class<?> clazz : classSet) {
            hashMap.put(clazz.getAnnotation(Pay.class).id(), clazz.getCanonicalName());
        }
    }

    public AgeMultiplyWeightStrategy createStrategy(Integer id) throws Exception {
        String className = hashMap.get(id);
        Class clazz = Class.forName(className);
        return (AgeMultiplyWeightStrategy) clazz.newInstance();
    }

}
