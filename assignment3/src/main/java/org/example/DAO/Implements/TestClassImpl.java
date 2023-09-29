package org.example.DAO.Implements;

import org.example.DAO.TestClass;

public class TestClassImpl implements TestClass {
    @Override
    public int func() {
        System.out.println("Running successfully!");
        return 2;
    }
}
