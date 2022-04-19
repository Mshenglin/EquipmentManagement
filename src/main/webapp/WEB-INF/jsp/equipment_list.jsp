<%--
  Created by IntelliJ IDEA.
  User: hkw
  Date: 2018/10/31
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  import="com.xu.entity.EquipmentResult" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script type="text/javascript" src="/js/equipment.js"></script>
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
        <a href="/findEquipment">器材信息</a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="/findEquipment" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="/findEquipment" >
            <input class="layui-input" placeholder="请输入器材id" name="id" id="id">
            <input class="layui-input" placeholder="请输入器材编号" name="code" id="code">
            <input class="layui-input" placeholder="请输入名称" name="name" id="name">
            <input class="layui-input" placeholder="请输入器材类型" name="equipmentType" id="equipmentType">
            <input class="layui-input" placeholder="请输入所属部门" name="department" id="department">
            <input class="layui-input" type="hidden" name="pageIndex" value="1">
            <input class="layui-input" type="hidden" name="pageSize" value="5">
            <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button id="addEquipmentBtn" class="layui-btn layui-btn-normal"> <i class="layui-icon">&#xe654;</i>添加 </button>
        <button class="layui-btn layui-btn-warm" lay-filter="toolbarDemo" lay-submit=""><i class="layui-icon">&#xe67c;</i>导出</button>
        <button id="importData" class="layui-btn  layui-btn-normal" lay-filter="toolImport" lay-submit="" onclick="upload()"><i class="layui-icon">&#xe601;</i>导入</button>
        <span class="x-right" style="line-height:40px">共有数据：${pi.totalCount} 条</span>
    </xblock>
    <div style="display: none;padding: 5px" id="showUploadExcelDiv">
        <form action="" method="post" class="layui-form layui-form-pane" id="exportFrm" lay-filter="exportFrm">
            <div class="layui-upload" >
                <button type="button" style="margin-top: 20px;margin-left: 20px" class="layui-btn layui-btn-warm"
                        onclick="downloadTemplate()">下载模板</button>
                <button type="button" style="margin-top: 20px;margin-left: 20px" class="layui-btn layui-btn-normal"
                        id="chooseFile">选择文件</button>
                <button type="button" style="margin-top: 20px" class="layui-btn"
                        id="uploadFle">开始上传</button>
            </div>
        </form>
    </div>
    <%--添加模态框--%>
    <div class="layui-row" id="test" style="display: none;">
        <div class="layui-col-md10">
            <form class="layui-form" id="addEmployeeForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">器材编号：</label>
                    <div class="layui-input-block">
                        <input type="text" lay-verify="required" name="code"   class="layui-input" placeholder="请输入器材编号">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">名称：</label>
                    <div class="layui-input-block">
                        <input type="text" lay-verify="required" name="name"  class="layui-input" placeholder="请输入器材名称">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">价格：</label>
                    <div class="layui-input-block">
                        <input type="text" name="price" class="layui-input" i placeholder="请输入价格">
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
                    <label class="layui-form-label">描述：</label>
                    <div class="layui-input-block">
                        <input type="text"  name="description"  class="layui-input" placeholder="请输入器材描述">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">负责人：</label>
                    <div class="layui-input-block">
                    <select name="leaderId" id="classifyId">
                        <option value="">请选择</option>
                    </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">器材类型：</label>
                    <div class="layui-input-block">
                        <select name="equipmentTypeId" id="classTypeId">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <%--编辑模态框--%>

    <div class="layui-row" id="updteDiv" style="display: none;">
        <div class="layui-col-md10">
            <form class="layui-form" id="addUpdatForm" >
                <input value="${sessionScope.sid}" type="text"  name="id" id="edit_id"/>
                <div class="layui-form-item">
                    <label class="layui-form-label">学号：</label>
                    <div class="layui-input-block">
                        <input type="text"  name="s_studentid" id="edit_studentid"  value=""  class="layui-input" placeholder="请输入学号">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">姓名：</label>
                    <div class="layui-input-block">
                        <input type="text"  name="s_name" id="edit_names"  value="" class="layui-input" placeholder="请输入姓名">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="s_sex" id="edit_sex" value="男" title="男" checked="">
                        <input type="radio" name="s_sex" id="edit_sex" value="女" title="女">
                        <%--<input type="text" name="s_sex" class="layui-input" id="s_sex" placeholder="请输入性别">--%>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">年龄：</label>
                    <div class="layui-input-block">
                        <input type="text" name="s_age" class="layui-input" id="edit_age" value="" placeholder="请输入年龄">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">电话：</label>
                    <div class="layui-input-block">
                        <input type="text"  name="s_phone" id="edit_phone" value="" class="layui-input" placeholder="请输入电话">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">班级编号：</label>
                    <div class="layui-input-block">
                        <input type="text" name="s_classid" id="edit_classids" value="" class="layui-input" placeholder="请输入班级编号">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">班级名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="s_classname" id="edit_classname" value="" class="layui-input" placeholder="请输入班级名">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">宿舍编号：</label>
                    <div class="layui-input-block">
                        <input type="text" name="s_dormitoryid"  id="edit_dormitoryids" value="" class="layui-input" placeholder="请输入宿舍编号">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="updateForm">更新</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <%--表格数据--%>
    <table class="layui-table">
        <thead>
        <tr>
            <%--<th>--%>
                <%--<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>--%>
            <%--</th>--%>
            <th>ID</th>
            <th>器材编号</th>
            <th>名称</th>
            <th>价格</th>
            <th>负责人</th>
            <th>器材类型</th>
                <th>部门</th>
            <th>创建时间</th>
            <th>修改时间</th>
            <th>描述</th>
            <th>器材状态</th>
                <th>操作</th>
        </thead>
        <tbody id="admin-list">
<c:forEach items="${pi.list}" var="equipment">
        <tr>
            <%--<td>--%>
                <%--<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>--%>
            <%--</td>--%>
            <td>${equipment.id}</td>
            <td>${equipment.code}</td>
            <td>${equipment.name}</td>
            <td>${equipment.price}</td>
            <td>${equipment.leaderName}</td>
            <td>${equipment.equipmentTypeName}</td>
                <td>${equipment.department}</td>
            <td>
                <jsp:useBean id="dateValue" class="java.util.Date"/>
                <jsp:setProperty name="dateValue" property="time" value="${equipment.createTime}"/>
                <fmt:formatDate value="${dateValue}" pattern="yyyy/MM/dd HH:mm"/>
            </td>
            <td>
                <jsp:useBean id="dateValueTwo" class="java.util.Date"/>
                <jsp:setProperty name="dateValueTwo" property="time" value="${equipment.updateTime}"/>
                <fmt:formatDate value="${dateValueTwo}" pattern="yyyy/MM/dd HH:mm"/>
            </td>
                <td>${equipment.description}</td>
                <c:if test="${equipment.equipmentStatus==0}">
                <td>已入库</td>
                </c:if>
                <c:if test="${equipment.equipmentStatus==1}">
                    <td>已出库</td>
                </c:if>
                <td>
                <a title="编辑"    id= "updateEdit"    href="/findEquipmentById?id=${equipment.id}">
                    <i class="layui-icon">&#xe642;</i>
                </a>
                <a title="出库" onclick="member_update_Status(this,'${equipment.id}')" href="javascript:;">
                    <i class="layui-icon">&#x1007;</i>
                </a>
                    <a title="删除" onclick="member_del(this,'${equipment.id}','${equipment.equipmentStatus}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                </a>
                </td>
        </tr>
</c:forEach>
        </tbody>
    </table>

<div class="" >
    <input type="hidden" id="totalPageCount" value="${pi.pageTotalCount}"/>
    <c:import url="pageBtn.jsp">
        <c:param name="totalCount" value="${pi.totalCount}"/>
        <c:param name="currentPageNo" value="${pi.pageIndex}"/>
        <c:param name="totalPageCount" value="${pi.pageTotalCount}"/>
    </c:import>
</div>
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
                url: '/exportEquipmentList',
                type: 'post',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log(data);

                    // 1. 如果需要调整顺序，请执行梳理函数
                    var dt = excel.filterExportData(data, [
                        'id'
                        ,'code'
                        ,'name'
                        ,'price'
                        ,'leaderName'
                        ,'equipmentTypeName'
                        ,'department'
                        ,'formatCreatTime'
                        ,'formatUpdateTime'
                        ,'formatEquipmentStatus'
                    ]);

                    // 2. 数组头部新增表头
                    dt.unshift({id: 'ID', code: '器材编号', name: '器材名称', price: '器材价格', leaderName: '负责人', equipmentTypeName: '器材类型', department: '部门', formatCreatTime: '创建时间', formatUpdateTime: '更新时间',formatEquipmentStatus:'器材状态'});

                    // 意思是：A列40px，B列60px(默认)，C列120px，D、E、F等均未定义
                    var colConf = excel.makeColConfig({
                        'B': 90,
                        'C': 80,
                        'F': 90
                    }, 60);

                    var timestart = Date.now();
                    // 3. 执行导出函数，系统会弹出弹框
                    excel.exportExcel({
                        sheet1: dt
                    }, '器材数据.xlsx', 'xlsx', {
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
                    setTimeout(function () {window.location.href='/findEquipment';},2000);
                }
            });
        });

        /*添加弹出框*/
        $("#addEquipmentBtn").click(function () {
            layer.open({
                type:1,
                title:"添加实验室器材",
                skin:"myclass",
                area:["50%"],
                anim:2,
                content:$("#test")
            });
            //下拉框可能会用到 New option
            //这个里面的参数说明 第一个是显示的文本，第二个是value值
            //例如：new Option(item.xm, item.id)第一个参数是下拉列表中显示的值  第二个参数是选中传递给后台的值
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
                        form.render();
                        //重新渲染 固定写法
                    }
                })
            });
            $("#addEmployeeForm")[0].reset();
            form.on('submit(formDemo)', function(data) {
                // layer.msg('aaa',{icon:1,time:3000});
                var param=data.field;
                // console.log(JSON.stringify(param));
                $.ajax({
                    url: '/addEquipment',
                    type: "post",
                    data:JSON.stringify(param),
                    contentType: "application/json; charset=utf-8",
                    success:function(){
                            layer.msg('添加成功', {icon: 1, time: 3000});
                            setTimeout(function () {window.location.href='/findEquipment';},2000);

                    },
                    error:function(){
                        layer.msg('添加失败',{icon:0,time:3000});
                        setTimeout(function () {window.location.href='/findEquipment';},2000);
                    }
                });
                // return false;
            });
        });


    });

    /**
     * 添加导入动态模拟框
     */
    function upload(){
        layer.open({
            type: 1,
            area: ['800px','150px'],
            fixed: false,
            title: '导入数据',
            content: $("#showUploadExcelDiv"),
            end: function () {
                location.reload();
            }
        })
    }
    function downloadTemplate(){
        //后端下载地址 TODO
        location.href="/downloadTemplate";
    }
    /* 导入 */
    layui.use(['form', 'layedit','upload'], function(){
        var form = layui.form,
            $ = layui.jquery,
            layer = layui.layer,
            layedit = layui.layedit,
            upload=layui.upload;

        upload.render({
            elem: "#chooseFile",
            url: '/importExcel',
            // 与bindAction一起使用，不直接上传，当点击#uploadFle按钮时才上传
            auto: false,
            bindAction: '#uploadFle',
            accept: "file",
            size: 10240,
            exts: 'xls|xlsx',
            /* 上传完毕回调 */
            done: function (res) {
                /* 后端传code值：1代表成功，0代表失败 */
                var code = res.code;
                if(code == "1"){
                    layer.confirm(res.msg, {title:'批量导入结果'},function(index){
                        layer.close(index);
                        window.location.reload()
                    });
                }else{
                    //失败后弹出页面，上面附有失败原因（后端写好传来）
                    layer.confirm(res.msg, {title:'导入失败，系统提示'},function(index){
                        layer.close(index);
                        window.location.reload()
                    });
                }
            },
            error: function(){
                //请求异常回调
                top.$.jBox.tip("服务器异常，请稍后再试");
                window.location.reload()
            }
        });
    })
    /*删除*/
    function member_del(obj,id,status){
        layer.confirm('确认要删除吗？',function(index){
            console.log(status);
            if(status==0){
                layer.alert("对不起,该器材没有出库，无法删除！");
            }
            else {
                //发异步删除数据
                $.get("/deleteEquipment", {"id": id}, function (data) {
                    if (data = true) {
                        layer.msg('删除成功!', {icon: 1, time: 2000});
                        setTimeout(function () {
                            window.location.href = '/findEquipment';
                        }, 2000);

                    } else {
                        layer.msg('删除失败!', {icon: 1, time: 3000});
                        setTimeout(function () {
                            window.location.href = '/findEquipment';
                        }, 2000);
                    }
                });
            }
        });
    }
    /*出库*/
    function member_update_Status(obj,id){
        layer.confirm('确认要出库吗？',function(index){
            //发异步
            console.log(id);
            $.get("/updateEquipmentStatus",{"id":id},function (data) {
                if(data =true){
                    layer.msg('出库成功!',{icon:1,time:2000});
                    setTimeout(function () {window.location.href='/findEquipment';},2000);

                }else {
                    layer.msg('出库失败!',{icon:1,time:3000});
                    setTimeout(function () {window.location.href='/findEquipment';},2000);
                }
            });
        });
    }
</script>


</body>


</html>
