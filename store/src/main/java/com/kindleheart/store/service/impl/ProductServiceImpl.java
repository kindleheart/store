package com.kindleheart.store.service.impl;

import com.kindleheart.store.dao.ProductDao;
import com.kindleheart.store.dao.impl.ProductDaoImpl;
import com.kindleheart.store.domain.PageModel;
import com.kindleheart.store.domain.Product;
import com.kindleheart.store.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> findHots() throws SQLException {
        return productDao.findHots();
    }

    @Override
    public List<Product> findNews() throws SQLException {
        return productDao.findNews();
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        return  productDao.findProductByPid(pid);
    }

    @Override
    public PageModel findProductsByCidWithPage(String cid, int num) throws SQLException {
        //统计当前分类下商品个数
        int totalRecords = productDao.findTotalRecords(cid);
        PageModel pageModel = new PageModel(num, totalRecords,12);
        List<Product> list = productDao.findProductByCidWithPage(cid, pageModel.getStartIndex(), pageModel.getPageSize());
        pageModel.setList(list);
        pageModel.setUrl("ProductServlet?method=findProductsByCidWithPage&cid=" + cid);
        return pageModel;
    }
}
