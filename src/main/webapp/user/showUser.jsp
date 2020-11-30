<%@page pageEncoding="UTF-8" isELIgnored="false"  %>




<script>
    function update(id,status){
        let bb= confirm("确定要修改状态吗");
        if (bb==true){
            $.ajax({
                type:'post',
                url: "${pageContext.request.contextPath}/auser/update",
                datatype: 'json', //返回的对象格式
                data:{"id":id,"freeze":status},
                success:function (data) {
                if (data.status=='200'){
                    $('#userId').jqGrid("clearGridData"); //清空上一次修改
                    $('#userId').trigger('reloadGrid'); //重新加载新数据
                }
                else alert(data.messages);
                }
            })
        }
    }

    $(function(){
        $("#userId").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/auser/queryBy",
            // edit:"",
            datatype: "json",
            rowNum:10,
            rowList:[10,20,30],
            pager: '#userPage',
            sortname: 'id',

            autowidth:true,
            height:"auto",
            viewrecords: true,  //是否展示总条数书
            colNames:['id','昵称', '头像', '手机号','简介','学分','创建时间',"状态"],
            colModel:[
                {name:'id',index:'id', width:55},
                {name:'nikeName',index:'invdate', width:90},
                {name:'picImg',index:'name asc, invdate', width:100,
                    formatter:function (value,option,row) {
                        return "<img src='${pageContext.request.contextPath}/bootstrap/img/"+value+"' style='height:50px;'>"
                    }
                },
                {name:'phone',index:'amount', width:80, align:"right"},
                {name:'brief',index:'tax', width:80, align:"right"},
                {name:'score',index:'total', width:80,align:"right"},
                {name:'createDate',index:'note', width:150, sortable:false},
                {name:'freeze',index:'note', width:150, sortable:false,
                    formatter(value,option,row){
                        console.log(row)
                        if(value==1)return"<a class='btn btn-success' onclick='update(\""+row.id+"\",\""+value+"\");'>正常</a>";
                        else return "<a class='btn btn-danger' onclick='update(\""+row.id+"\",\""+value+"\");'>冻结</a>";
                    }
                }
            ]
        });
        $("#userId").jqGrid('navGrid','#userPage',{edit:false,add:false,del:false});

    });

</script>
<script>
    $(function () {
        $("#aliyun").click(function () {
            //获取手机号
            var phone = $("#phone").val();
            //发送请求
            $.post("${pageContext.request.contextPath}/auser/phoneCode",{"phone":phone},function (data) {
                if (data.status=="200"){
                    alert(data.message);
                }else{
                    alert(data.message);
                }

            },"JSON")
        })
    })

</script>

<script>
    $(function () {
        $("#Exce").click(function () {
            var b = confirm("确定要导出信息吗");
            if (b==true){
                $.ajax({
                    type: 'post',
                    url:"${pageContext.request.contextPath}/auser/exce",
                    datatype:'Json'
                });
            }
        })

    })

</script>


<%--设置面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>

    <div>
        <button class="btn btn-info" id="Exce">导出用户信息</button>
        <button class="btn btn-success">导出用户信息</button>

        <div class="pull-right col-sm-5">
            <form>
                <div class="col-md-4 col-md-offset-6" style="padding: 0px;">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号..." required
                           minlength="11">
                </div>
                <div class="col-md-2 pull-right" style="padding: 0px;">
                    <button type="button" id="aliyun" class="btn btn-info btn-block">发送验证码</button>
                </div>
            </form>
        </div>
    </div>
        <br>


    <%--表单--%>
    <table id="userId" />

    <%--分页工具栏--%>
    <div id="userPage" />

</div>


