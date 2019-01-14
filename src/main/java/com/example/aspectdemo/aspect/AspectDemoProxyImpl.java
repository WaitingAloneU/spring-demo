package com.example.aspectdemo.aspect;

/**
 * 静态代理实现
 */
public class AspectDemoProxyImpl implements AspectDemo {

    private AspectDemo demo;

    public AspectDemoProxyImpl(AspectDemo demo){
        this.demo = demo;
    }


    private void printBefor(){
        System.out.println("befor aspect");
    }

    @Override
    public void printAspectStr() {
        printBefor();
        demo.printAspectStr();
        printAfter();
    }

    private void printAfter(){
        System.out.println("after aspect");
    }

}
