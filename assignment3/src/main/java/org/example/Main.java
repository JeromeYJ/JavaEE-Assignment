package org.example;

import org.example.ApplicationContext.MyApplicationContext;
import org.example.Service.BookService;
import org.example.Service.TestService;


public class Main {
    public static void main(String[] args) {
        try{
            //测试第一个类的save()方法
            MyApplicationContext myApplicationContext = new MyApplicationContext("applicationContext.xml");
            BookService bookService = (BookService) myApplicationContext.getBean("bookService");
            bookService.save();

            //测试第二个类的run()方法
            TestService testService = (TestService) myApplicationContext.getBean("testService");
            testService.run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}