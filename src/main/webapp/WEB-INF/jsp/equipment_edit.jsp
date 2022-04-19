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
    <script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
    <script src="/lib/layui/layui.js"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
</head>

<body>

<div class="x-body">
    <form class="layui-form" action="/updateEquipment" method="post"  id="f_auto" accept-charset="UTF-8">
        <input type="hidden" value="${sessionScope.s.id}" name="id" id="id"/>
        <div class="layui-form-item">
            <label for="code" class="layui-form-label">
            器材编号:
            </label>
            <div class="layui-input-inline">
                <input type="text" id="code" name="code"
                       autocomplete="off" value="${sessionScope.s.code}" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
               器材名称:
            </label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name"
                       autocomplete="off" value="${sessionScope.s.name}" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="price" class="layui-form-label">
                价格:
            </label>
            <div class="layui-input-inline">
                <input type="text" id="price" name="price"
                       autocomplete="off" value="${sessionScope.s.price}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="description" class="layui-form-label">
                器材描述:
            </label>
            <div class="layui-input-inline">
                <input type="text" id="description" name="description"
                       autocomplete="off" value="${sessionScope.s.description}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="department" class="layui-form-label">部门选择:</label>
            <div class="layui-input-inline">
                <select name="department" lay-verify="required" id="department" >
                    <option value="${sessionScope.s.department}">${sessionScope.s.department}</option>
                    <option value="物联网">物联网</option>
                    <option value="电科">电科</option>
                    <option value="通工">通工</option>
                    <option value="安全">安全</option>
                    <option value="对抗">对抗</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="classifyId" class="layui-form-label">负责人：</label>
            <div class="layui-input-block">
                <select name="leaderId" id="classifyId">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="classTypeId" class="layui-form-label">器材类型：</label>
            <div class="layui-input-block">
                <select name="equipmentTypeId" id="classTypeId">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item" id="btn_xg">
            <button  class="layui-btn"  id="btn_on" lay-filter="updateForm">
                修改
            </button>
        </div>
    </form>
</div>

<script>
    layui.use(['form', 'upload', 'layer'],function(){
        var form = layui.form;
        $.ajax({
            url: '/findUserIdAndName',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                //使用循环遍历，给下拉列表赋值
                for(let i of data){
                    $('#classifyId').append(new Option(i.name, i.id));
                }
                $('#classifyId').val(${sessionScope.s.leaderId});
                form.render();
                //重新渲染 固定写法
            }
        })
        $.ajax({
            url: '/findEquipmentTypeList',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                //使用循环遍历，给下拉列表赋值
                for(let i of data){
                    $('#classTypeId').append(new Option(i.name, i.id));
                }
                console.log(${sessionScope.s.equipmentTypeId});
                $('#classTypeId').val(${sessionScope.s.equipmentTypeId});
                form.render();
                //重新渲染 固定写法
            }
        })
    });

</script>
</body>
</html>
