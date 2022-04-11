<%--
  Created by IntelliJ IDEA.
  User: Alkmg
  Date: 2022/3/16
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />
    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
    <script src="/lib/layui/layui.js"></script>
    <script>
        layui.use('form', function(){
            var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

            //……

            //如果你的 HTML 是动态生成的，自动渲染就会失效
            //因此你需要在相应的地方，执行下述方法来进行渲染
            form.render();
        });
    </script>
    <title>用户注册</title>
</head>
<body>
<div id="sign">
<form method="post" action="/sign" >
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名，用于登录" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名字</label>
        <div class="layui-input-block">
            <input type="text" name="name" required  lay-verify="required" placeholder="请输入名字" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">部门选择</label>
        <div class="layui-input-block">
            <select name="department" lay-verify="required">
                <option value=""></option>
                <option value="物联网">物联网</option>
                <option value="电科">电科</option>
                <option value="通工">通工</option>
                <option value="安全">安全</option>
                <option value="对抗">对抗</option>
            </select>
        </div>

    </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
</form>
</div>
</body>
<script>
    layui.use('form', function(){
        var form = layui.form;
        form.render(); //更新全部
        form.render('select'); //刷新select选择框渲染
        //各种基于事件的操作，下面会有进一步介绍
    });
</script>
</html>
