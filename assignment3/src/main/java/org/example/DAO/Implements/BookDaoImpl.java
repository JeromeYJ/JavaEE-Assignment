package org.example.DAO.Implements;

import org.example.DAO.BookDao;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean save() {
        System.out.println("book dao save ...（1）");
        return true;
    }
}