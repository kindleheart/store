package com.kindleheart.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    //个数不确定的购物项
    private Map<String, CartItem> map = new HashMap<>();
    //总计积分
    private double total = 0;

    //添加购物项到购物车
    public void addCartItemToCart(CartItem cartItem) {
        //之前买过就直接增加数量，否则直接放进去
        String pid = cartItem.getProduct().getPid();
        if(map.containsKey(pid)) {
            CartItem old = map.get(pid);
            old.setNum((old.getNum() + cartItem.getNum()));
        } else {
            map.put(pid, cartItem);
        }
    }

    public Collection getCartItems() {
        return  map.values();
    }

    //移除购物项
    public  void removeCartItem(String pid) {
        map.remove(pid);
    }
    //清空购物车
    public void clearCart() {
        map.clear();
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    public double getTotal() {
        Collection<CartItem> values = map.values();
        total = 0;
        for(CartItem cartItem : values) {
            total += cartItem.getSubTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
