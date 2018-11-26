package com.kindleheart.store.web.servlet;

import com.kindleheart.store.domain.PageModel;
import com.kindleheart.store.domain.Product;
import com.kindleheart.store.service.ProductService;
import com.kindleheart.store.service.impl.ProductServiceImpl;
import com.kindleheart.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kindleheart happily.
 */
@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends BaseServlet {
    public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pid = request.getParameter("pid");
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductByPid(pid);
        request.setAttribute("product", product);
        return "jsp/product_info.jsp";
    }

    public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int num = Integer.parseInt(request.getParameter("num"));
        String cid = request.getParameter("cid");
        ProductService productService = new ProductServiceImpl();
        PageModel pageModel = productService.findProductsByCidWithPage(cid, num);
        request.setAttribute("page", pageModel);
        return "jsp/product_list.jsp";
    }
}
