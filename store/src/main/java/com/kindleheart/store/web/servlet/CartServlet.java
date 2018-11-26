package com.kindleheart.store.web.servlet;

import com.kindleheart.store.domain.Cart;
import com.kindleheart.store.domain.CartItem;
import com.kindleheart.store.domain.Product;
import com.kindleheart.store.service.ProductService;
import com.kindleheart.store.service.impl.ProductServiceImpl;
import com.kindleheart.store.web.base.BaseServlet;

import javax.mail.Session;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {
    //添加购物车购物项到购物车
    public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(null == cart) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //得到商品id，数量
        String pid = request.getParameter("pid");
        int num = Integer.parseInt(request.getParameter("quantity"));
        //根据id得到商品
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductByPid(pid);
        //创建购物项
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setNum(num);
        //调用购物车的方法
        cart.addCartItemToCart(cartItem);
        response.sendRedirect("jsp/cart.jsp");
        return null;
    }
}
