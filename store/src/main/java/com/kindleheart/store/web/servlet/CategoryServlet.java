package com.kindleheart.store.web.servlet;

import com.kindleheart.store.domain.Category;
import com.kindleheart.store.service.CategoryService;
import com.kindleheart.store.service.impl.CategoryServiceImpl;
import com.kindleheart.store.utils.JedisUtils;
import com.kindleheart.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by kindleheart happily.
 */
@WebServlet(name = "CategoryServlet", urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    //findAllCats;
    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //在redis中获取全部分类信息
        //Jedis jedis=JedisUtils.getJedis();
        Jedis jedis = new Jedis("127.0.0.1");
        String jsonStr = jedis.get("allCats");
        if(null==jsonStr || "".equals(jsonStr)){
            //调用业务层获取全部分类
            CategoryService CategoryService=new CategoryServiceImpl();
            List<Category> list = CategoryService.getAllCats();
            //将全部分类转换为JSON格式的数据
            jsonStr=JSONArray.fromObject(list).toString();
            //将获取到的JSON格式的数据存入redis
            jedis.set("allCats", jsonStr);
        }
        //将全部分类信息响应到客户端
        //告诉浏览器本次响应的数据是JSON格式的字符串
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(jsonStr);
        //JedisUtils.closeJedis(jedis);
        return null;
    }

}
