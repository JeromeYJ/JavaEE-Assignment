package org.example.DAO.Implements;

import org.example.DAO.BookDao;

public class BookDaoImpl2 implements BookDao {
    @Override
    public boolean save() {
        System.out.println("book dao save ...（2）");
        return true;
    }
}
