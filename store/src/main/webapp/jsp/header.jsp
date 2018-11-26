<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid">
    <!--
        描述：菜单栏
    -->
    <div class="container-fluid">
        <div class="col-md-4">
            <img src="${pageContext.request.contextPath}/img/logo2.png" />
        </div>
        <div class="col-md-5">
            <img src="${pageContext.request.contextPath}/img/header.png" />
        </div>
        <div class="col-md-3" style="padding-top:20px">
            <ol class="list-inline">
                <c:if test="${empty user}">
                    <li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
                    <li><a href="${pageContext.request.contextPath}/UserServlet?method=registerUI">注册</a></li>
                </c:if>
                <c:if test="${not empty user}">
                    <li>欢迎${user.username}</li>
                    <li><a href="${pageContext.request.contextPath}/UserServlet?method=logOut">退出</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
                </c:if>
            </ol>
        </div>
    </div>
    <!--
        描述：导航条
    -->
    <div class="container-fluid">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp">首页</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav" id="myUL">
                        <%--<c:forEach items="${allCats}" var="c">--%>
                            <%--<li><a href="#">${c.cname}</a></li>--%>
                        <%--</c:forEach>--%>
                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        //向服务器发送Ajax请求
        $.post("CategoryServlet", {"method":"findAllCats"}, function (data) {
                $.each(data, function (i, obj) {
                    var li = "<li><a href='ProductServlet?method=findProductsByCidWithPage&num=1&cid=" + obj.cid +"'>" + obj.cname + "</a></li>";
                    $("#myUL").append(li);
                });
            }, "json");
    });
</script>
</html>
