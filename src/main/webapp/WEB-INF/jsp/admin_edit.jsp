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
    <script>
        layui.use('form', function(){
            var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

            //……

            //如果你的 HTML 是动态生成的，自动渲染就会失效
            //因此你需要在相应的地方，执行下述方法来进行渲染
            form.render();
        });
    </script>
</head>

<body>

<div class="x-body">
    <%--把表单封装成一个User对象传给服务端--%>
    <form class="layui-form"  id="f_auto" action="/updateUser" method="post">
        <input type="hidden" value="${sessionScope.a.id}" name="id" id="id" class="layui-input"/>
        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="">用户名</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="username"
                       autocomplete="off" value="${sessionScope.a.username}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="password" class="layui-form-label">
                <span class="">密码</span>
            </label>
            <div class="layui-input-inline">
                <input type="password" id="password" name="password"
                       autocomplete="off" value="${sessionScope.a.password}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
                <span class="">姓名</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name"
                       autocomplete="off" value="${sessionScope.a.name}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="phone" class="layui-form-label">
                <span class="">电话</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="phone" name="a_phone"
                       autocomplete="off" value="${sessionScope.a.phone}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="userRole" class="layui-form-label">
                <span class="">级别</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="userRole" name="userRole"
                       autocomplete="off" value="${sessionScope.a.userRole}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="department" class="layui-form-label">
                <span class="">部门</span>
            </label>
            <div class="layui-input-block">
                    <select id="department" name="department" lay-verify="required">
                        <option value=""></option>
                        <option value="物联网">物联网</option>
                        <option value="电科">电科</option>
                        <option value="通工">通工</option>
                        <option value="安全">安全</option>
                        <option value="对抗">对抗</option>
                    </select>
            </div>
        </div>
        <div class="layui-form-item" id="btn_xg">
            <button  class="layui-btn" id="btn_on"  lay-submit="" lay-filter="updateAdmin">
                修改
            </button>
        </div>
    </form>
</div>
<script>
    layui.use('form', function(){
        var form = layui.form;
        form.render(); //更新全部
        form.render('select'); //刷新select选择框渲染
        //各种基于事件的操作，下面会有进一步介绍
    });
</script>
<script>
    // layui.use(['form','layer','laydate'], function(){
    //     var form = layui.form,
    //         $ = layui.jquery,
    //         laydate = layui.laydate;
    //
    //     $("#f_auto")[0].reset();
    //     form.on('submit(updateAdmin)', function(data) {
    //
    //         var param=data.field;
    //
    //         $.ajax({
    //             url: '/updateAdmin',
    //             type: "post",
    //             data: JSON.stringify(param),
    //             contentType: "application/json; charset=utf-8",
    //             success:function(){
    //                 console.log(data);
    //                 layer.msg('修改成功', {icon: 1, time: 3000});
    //                 setTimeout(function () {window.location.href='/findAdmin';},2000);
    //
    //             },
    //             error:function(){
    //                 console.log(data);
    //                 layer.msg('修改失败',{icon:0,time:3000});
    //                 setTimeout(function () {window.location.href='/findAdmin';},2000);
    //             }
    //         });
    //     })
    //
    // });
</script>
</body>
</html>
