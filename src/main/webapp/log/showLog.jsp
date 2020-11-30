<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){
        $("#videoTable").jqGrid({
            url:"${path}/log/findAll",   //page:当前页   rows每页展示条数    page  rows数据 records总条数  total总页数
            /*editurl:"${path}/video/exit",*/
            datatype: "json",
            rowNum:5,
            rowList:[5,10,20],
            pager: '#userPage',

            sortname: 'id',
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            viewrecords: true,  //是否展示总条数书
            colNames:['ID','用户名','操作时间','具体操作','状态'],
            colModel:[
                {name:'id',index:'id', width:100},
                {name:'name',index:'name',width:100},
                {name:'time',index:'time',width:100},
                {name:'options',index:'options',width:100},
                {name:'status',index:'status',width:100}

            ]
        });
    });
</script>
<%--设置面板--%>
<div class="panel panel-info">
    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">视频管理</a></li>
    </ul>

    <div>
        <button class="btn btn-info">视频信息</button>
    </div><br>
    <%--表单--%>
    <table id="videoTable" />
    <%--分页工具栏--%>
    <div id="userPage" />
</div>
