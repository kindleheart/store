package com.kindleheart.store.service;

import com.kindleheart.store.domain.PageModel;
import com.kindleheart.store.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public interface ProductService {
    List<Product> findHots() throws SQLException;
    List<Product> findNews() throws SQLException;
    Product findProductByPid(String pid) throws SQLException;
    PageModel findProductsByCidWithPage(String cid, int num) throws SQLException;
}
