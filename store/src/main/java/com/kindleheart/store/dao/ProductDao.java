package com.kindleheart.store.dao;

import com.kindleheart.store.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public interface ProductDao {
    List<Product> findNews() throws SQLException;
    List<Product> findHots() throws SQLException;
    Product findProductByPid(String pid) throws SQLException;
    int findTotalRecords(String cid) throws SQLException;
    List<Product> findProductByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;
}
