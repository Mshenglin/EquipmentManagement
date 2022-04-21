<%--
  Created by IntelliJ IDEA.
  User: 周训凯
  Date: 2019/4/28
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="com.xu.entity.OperationalLog" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <%--<meta http-equiv="Cache-Control" content="no-siteapp" />--%>

    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
    <script src="/lib/layui/layui.js"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
    <script src="/layui_exts/excel.js"></script>

    <style type="text/css">
        .layui-table{
            text-align: center;
        }
        .layui-table th{
            text-align: center;
        }
    </style>
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="/findOperational">维修信息</a>

      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="/findOperational" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="/findOperational" >
            <input class="layui-input" placeholder="请输入实验器材ID" name="id" id="d_id">
            <input class="layui-input" placeholder="请输入操作人名称" name="name" id="name">

            <input class="layui-input" type="hidden" name="pageIndex" value="1">
            <input class="layui-input" type="hidden" name="pageSize" value="5">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button id="addStudnetBtn" class="layui-btn layui-btn-normal"> <i class="layui-icon">&#xe654;</i>添加 </button>
        <button class="layui-btn layui-btn-warm" lay-filter="toolbarDemo" lay-submit=""><i class="layui-icon">&#xe67c;</i>导出</button>
        <span class="x-right" style="line-height:40px">共有数据：${mi.totalCount} 条</span>
    </xblock>


    <%--表格数据--%>
    <table class="layui-table">
        <thead>
        <tr>
                <th>ID</th>
                <th>操作人名称</th>
                <th>器材ID</th>
                <th>操作时间</th>
                <th>操作类型(1:增加,2:修改,3:删除)</th>
                <th>操作细节</th>
        </thead>
        <tbody>
        <c:forEach items="${mi.list}" var="m">
            <tr>
                <td>${m.id}</td>
                <td>${m.username}</td>
                <td>${m.equipmentId}</td>
                <td>
                    <jsp:useBean id="dateValue" class="java.util.Date"/>
                    <jsp:setProperty name="dateValue" property="time" value="${m.createTime}"/>
                    <fmt:formatDate value="${dateValue}" pattern="yyyy/MM/dd HH:mm"/>
                </td>
                <td>${m.operationalId}</td>
                <td>${m.remark}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="" >
        <input type="hidden" id="totalPageCount" value="${mi.pageTotalCount}"/>
        <c:import url="pageBtn.jsp">
            <c:param name="totalCount" value="${mi.totalCount}"/>
            <c:param name="currentPageNo" value="${mi.pageIndex}"/>
            <c:param name="totalPageCount" value="${mi.pageTotalCount}"/>
        </c:import>
    </div>
    <script>

        layui.config({
            base: 'layui_exts/',
        }).extend({
            excel: 'excel',
        });
        layui.use(['jquery', 'excel','form','layer','laydate'], function(){
            var form = layui.form,
                $ = layui.jquery,
                laydate = layui.laydate;
            var excel = parent.layui.excel;

            //执行一个laydate实例
            laydate.render({
                elem: '#start' //指定元素
            });

            form.on('submit(toolbarDemo)', function(){

                $.ajax({
                    url: '/findOperationalList',
                    type: 'post',
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    success: function (data) {
                        console.log(data);

                        // 1. 如果需要调整顺序，请执行梳理函数
                        var dt = excel.filterExportData(data, [
                            'id'
                            ,'username'
                            ,'equipmentId'
                            ,'createTime'
                            ,'operationalId'
                            ,'remark'
                        ]);

                        // 2. 数组头部新增表头
                        dt.unshift({id: 'ID', username: '操作人名称', equipmentId: '操作器材ID', operationalId: '操作类型', remark: '操作细节'});

                        // 意思是：A列40px，B列60px(默认)，C列120px，D、E、F等均未定义
                        var colConf = excel.makeColConfig({
                            'F': 160,
                            'G': 160
                        }, 60);

                        var timestart = Date.now();
                        // 3. 执行导出函数，系统会弹出弹框
                        excel.exportExcel({
                            sheet1: dt
                        }, '维修登记数据.xlsx', 'xlsx', {
                            extend: {
                                '!cols': colConf
                            }
                        });

                        var timeend = Date.now();
                        var spent = (timeend - timestart) / 1000;
                        layer.alert('导出耗时 '+spent+' s');
                        //setTimeout(function () {window.location.href='/findAdmin';},2000);
                    },

                    error: function () {
                        //console.log(data);
                        setTimeout(function () {window.location.href='/findOperational';},2000);
                    }
                });
            });


        });
    </script>


</body>


</html>

