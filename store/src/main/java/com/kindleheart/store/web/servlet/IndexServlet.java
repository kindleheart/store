package com.kindleheart.store.web.servlet;

import com.kindleheart.store.domain.Product;
import com.kindleheart.store.service.ProductService;
import com.kindleheart.store.service.impl.ProductServiceImpl;
import com.kindleheart.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
@WebServlet(name = "IndexServlet", urlPatterns = "/IndexServlet")
public class IndexServlet extends BaseServlet {
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProductService productService = new ProductServiceImpl();
       List<Product> list01 = productService.findHots();
       List<Product> list02 = productService.findNews();
       request.setAttribute("hots", list01);
       request.setAttribute("news", list02);
       return "jsp/index.jsp";
   }
}
