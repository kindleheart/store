package com.kindleheart.store.dao;

import com.kindleheart.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public interface CategoryDao {
    List<Category> getAllCats() throws SQLException;
}
