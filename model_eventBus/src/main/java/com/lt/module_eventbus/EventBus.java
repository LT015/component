package com.lt.module_eventbus;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EventBus {

    private HashMap<Object, List<SubscribeMethod>> cacheMap;
    private static volatile EventBus instance;

    public static EventBus getDefault(){
        if(instance == null){
            instance = new EventBus();
        }
        return instance;
    }
    private EventBus(){
        cacheMap = new HashMap<>();
    }

    public void register(Object obj) {
        //寻找到所有object类带有subscribe注解的方法
        List<SubscribeMethod> list = cacheMap.get(obj);
       if(list == null){
           list = findSubscribeMethod(obj);
           cacheMap.put(obj,list);
       }
    }

    private List<SubscribeMethod> findSubscribeMethod(Object obj) {
        List<SubscribeMethod> list = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        while(clazz != null){
            String name = clazz.getName();
            if(name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")){
                break;
            }
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if(subscribe == null){
                    continue;
                }
                //判断带有subscribe注解方法中的参数类型
                Class<?>[] types = method.getParameterTypes();
                if(types.length != 1){
                    Log.d("错误", "eventbus only accpet one para: ");
                }
                ThreadMode threadMode = subscribe.threadMode();
                SubscribeMethod sbm = new SubscribeMethod(method,threadMode,types[0]);
                list.add(sbm);
            }
            clazz = clazz.getSuperclass();
        }

        return  list;

    }

    public void post(Object type) {
        //直接循环cacheMap里的所有方法
        Set<Object> set = cacheMap.keySet();
        Iterator<Object> iterator = set.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            List<SubscribeMethod> list = cacheMap.get(obj);
            for(SubscribeMethod subscribeMethod : list){ //通过传来得到参数来确定函数
                if(subscribeMethod.getType().isAssignableFrom(type.getClass())){

                    subscribeMethod.getmThreadMode();
                    //通过mode得模式 来进行线程切换
                    invoke(subscribeMethod,obj,type);
                }
            }
        }
    }
    private void invoke(SubscribeMethod subscribeMethod,Object obj,Object type){
        Method method = subscribeMethod.getmMethod();
        try {
            method.invoke(obj,type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //要维护一个删除
}
