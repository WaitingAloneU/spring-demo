package com.example.aspectdemo.aspect;

/**
 * 动态代理接口实现
 */
public class AspectDemoImpl implements  AspectDemo {
    @Override
    public void printAspectStr() {
        System.out.println("print aspect");
    }
}
