package org.example.Service.Implements;

import org.example.DAO.TestClass;
import org.example.Service.TestService;

public class TestServiceImpl implements TestService {
    private TestClass test;

    @Override
    public int run(){
        int result = test.func();
        return result;
    }

    public void setTestClass(TestClass test){
        this.test = test;
    }
}
