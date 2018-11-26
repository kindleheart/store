package com.kindleheart.store.dao.impl;

import com.kindleheart.store.dao.CategoryDao;
import com.kindleheart.store.domain.Category;
import com.kindleheart.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAllCats() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from category";
        return runner.query(sql, new BeanListHandler<>(Category.class));
    }
}
