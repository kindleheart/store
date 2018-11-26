package com.kindleheart.store.service.impl;

import com.kindleheart.store.dao.CategoryDao;
import com.kindleheart.store.dao.impl.CategoryDaoImpl;
import com.kindleheart.store.domain.Category;
import com.kindleheart.store.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAllCats() throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.getAllCats();
    }
}
