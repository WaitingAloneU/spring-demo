package com.example.aspectdemo.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理handler
 * @param <T>
 */
public class AspectDemoInvocationHandler<T> implements InvocationHandler {

    T target;

    public AspectDemoInvocationHandler(T target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" + method.getName() + "方法");
        Object result = method.invoke(target, args);
        return result;
    }
}
