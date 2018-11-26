package com.kindleheart.store.dao.impl;

import com.kindleheart.store.dao.ProductDao;
import com.kindleheart.store.domain.Product;
import com.kindleheart.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public class ProductDaoImpl implements ProductDao{

    @Override
    public List<Product> findNews() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pflag=0 order by pdate desc limit 0,9";
        return runner.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> findHots() throws SQLException{
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0,9";
        return runner.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        return runner.query(sql, new BeanHandler<>(Product.class), pid);
    }

    @Override
    public int findTotalRecords(String cid) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from product where cid = ?";
        Long num = (Long) runner.query(sql, new ScalarHandler<>(), cid);
        return num.intValue();
    }

    @Override
    public List<Product> findProductByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where cid=? limit ?,?";
        return runner.query(sql, new BeanListHandler<>(Product.class), cid, startIndex, pageSize);
    }

}
