package com.example.aspectdemo.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试类
 */
public class AspectDemoTest {

    public static void main(String[] args) {
        // 静态代理
        AspectDemoImpl demoImpl = new AspectDemoImpl();
        AspectDemo demo = new AspectDemoProxyImpl(demoImpl);
        demo.printAspectStr();
        System.out.println("==========我是分割线==========");
        // 动态代理
        AspectDemo aspectDemo = new AspectDemoImpl();
        InvocationHandler demoHandler = new AspectDemoInvocationHandler<AspectDemo>(aspectDemo);
        AspectDemo demoProxy = (AspectDemo)Proxy.newProxyInstance(AspectDemo.class.getClassLoader(), new Class<?>[]{AspectDemo.class}, demoHandler);
        demoProxy.printAspectStr();

    }

}
