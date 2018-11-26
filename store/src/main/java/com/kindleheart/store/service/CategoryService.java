package com.kindleheart.store.service;

import com.kindleheart.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public interface CategoryService {
    List<Category> getAllCats() throws SQLException;
}
