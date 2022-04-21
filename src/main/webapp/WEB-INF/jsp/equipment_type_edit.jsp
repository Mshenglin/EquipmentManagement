<%--
  Created by IntelliJ IDEA.
  User: hkw
  Date: 2018/11/14
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>修改信息</title>
    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <link rel="stylesheet" href="/css/pg_btn.css">
    <script type="text/javascript" src="./js/jquery-1.3.2.min.js"></script>
    <script src="lib/layui/layui.js"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
</head>

<body>

<div class="x-body">
    <form class="layui-form"  id="f_auto" action="/updateEquipmentType" method="post" >
        <div class="layui-form-item">
            <label for="EquipmentTypeId" class="layui-form-label">
                <span class="">器材编号</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="EquipmentTypeId" name="id"
                       autocomplete="off" value="${sessionScope.d.id}" class="layui-input" readonly>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="EquipmentTypeName" class="layui-form-label">
                <span class="">器材类型名称</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="EquipmentTypeName" name="name"
                       autocomplete="off" value="${sessionScope.d.name}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" id="btn_xg">
            <button  class="layui-btn" id="btn_on"  lay-submit="" lay-filter="updateClass">
                修改
            </button>
        </div>
    </form>
</div>

<script>

</script>
</body>
</html>
