<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>

    $(function(){
        pageInit();
    });

    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${pageContext.request.contextPath}/category/queryBypage",
            datatype : "json",
            rowNum : 4,
            rowList : [ 8, 10, 20, 30 ],
            pager : '#catePage',
            sortname : 'id',
            viewrecords : true,
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            editurl:"${pageContext.request.contextPath}/category/edit",
            colNames : [ 'id', '名称', '级别', '父类别id'],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cateName',index : 'invdate',width : 90,editable:true},
                {name : 'levels',index : 'name',width : 100,editable:true},
                {name : 'parentId',index : 'amount',width : 80,align : "right",editable:true},

            ],
            subGrid : true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded : function(subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add :true,edit : true,del : true},
            {
                closeAfterEdit:true, //关闭修改面板
                reloadAfterSubmit: true,
            },  //修改
            {closeAfterAdd :true,
                reloadAfterSubmit: true,}, //添加
            {
                closeAfterDelete :true,
                reloadAfterSubmit: true,
                afterSubmit:function (response) {
                        alert(response.responseJSON.message)
                    return"true";
                }
            }, //删除

        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowId){

        var subgridTableTd= subgridId + "Table";
        var pagerId= subgridId+"Page";


        $("#"+subgridId).html("" +
            "<table id='"+subgridTableTd+"' />" +
            "<div id='"+pagerId+"' />"
        );



        $("#" + subgridTableTd).jqGrid({
            url : "${path}/category/queryFile?id=" + rowId,
            datatype : "json",
            rowNum : 20,
            pager : "#"+pagerId,
            sortname : 'num',
            sortorder : "asc",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            editurl:"${pageContext.request.contextPath}/category/edit",
            colNames : [ 'id', '名称', '级别', '父类别Id' ],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cateName',index : 'invdate',width : 90,editable:true},
                {name : 'levels',index : 'name',width : 100,editable:true,editoptions:{value:"一级类别:一级类别;二及类别:二及类别"},search:false},
                {name : 'parentId',index : 'amount',width : 80,align : "right",editable:true},

            ]
        });

        $("#" + subgridTableTd).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true});
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage" />

</div>

<%--
    删除要有提示信息
--%>
