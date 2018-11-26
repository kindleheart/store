package com.kindleheart.store.domain;

public class CartItem {
    private Product product;//目的携带购物项的三种参数(图片路径，商品名称，商品价格)
    private int num = 0;//数量
    private double subTotal = 0;//小计

    public CartItem(Product product, int num, double subTotal) {
        this.product = product;
        this.num = num;
        this.subTotal = subTotal;
    }

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSubTotal() {
        subTotal = num * product.getShop_price();
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
