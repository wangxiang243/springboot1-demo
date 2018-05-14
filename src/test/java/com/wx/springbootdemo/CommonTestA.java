package com.wx.springbootdemo;

import org.junit.Test;

public class CommonTestA extends CommonTest{

    @Test
    public void test(){
        CommonTest commonTest = new CommonTest();
        commonTest.c();
        CommonTestA commonTestA = new CommonTestA();
        commonTestA.c();
    }

}
