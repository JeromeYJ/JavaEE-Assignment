package org.example.Service.Implements;

import org.example.DAO.BookDao;
import org.example.Service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Override
    public boolean save() {
        System.out.println("book service save ...");
        return bookDao.save();
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
