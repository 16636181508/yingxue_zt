<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
<!--导航条-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- 导航条标题 -->
        <div class="navbar-header">
            <!--自适应-->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">应学视频App管理系统V1.0</a>
        </div>

        <!-- 导航栏上工具-->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <!--右边工具栏-->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:${requestScope.user.name}</a></li>
                <li><a href="${pageContext.request.contextPath}/user/exit">退出系统</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <div class="panel-footer" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-success">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <button type="button" class="btn btn-success">
                                <a href="javascript:$('#mainIn').load('${path}/user/showUser.jsp')">用户管理</a>
                            </button>
                            <br>
                            <br>
                            <button type="button" class="btn btn-danger">
                                <a href="javascript:$('#mainIn').load('${path}/test/TestEcharts.jsp')">用户统计</a>
                            </button>
                            <br>
                            <br>
                            <button type="button" class="btn btn-primary">
                                <a href="javascript:$('#mainIn').load('${path}/test/Testchain.jsp')">用户分布</a>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                分类管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <button type="button" class="btn btn-block">
                                <a href="javascript:$('#mainIn').load('${path}/category/showCategory.jsp')">分类展示</a>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                视频管理
                            </a>
                        </h4>
                    </div>

                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <button type="button" class="btn btn-default">
                                <a href="javascript:$('#mainIn').load('${path}/video/showVideo.jsp')">视频管理</a>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="panel panel-warning">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseThree">
                                日志管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseFour">
                        <div class="panel-body">
                            <button type="button" class="btn btn-default">
                                <a href="javascript:$('#mainIn').load('${path}/log/showLog.jsp')">日志信息</a>
                            </button>
                        </div>
                    </div>
                </div>


            </div>
        </div>

        <div class="container-fluid" >
            <div class="col-sm-10" id="mainIn">
                <div >
                    <div class="jumbotron">
                        <h4><b>应学后台管理系统</b></h4>

                    </div>
                </div>
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" align="center">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic1.jpg" alt="...">
                                <div class="carousel-caption">
                                </div>
                            </div>
                            <div class="item">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic2.jpg" alt="...">
                                <div class="carousel-caption">
                                </div>
                            </div>
                            <div class="item">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic3.jpg" alt="...">
                                <div class="carousel-caption">
                                </div>
                            </div>
                            <div class="item">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic4.jpg" alt="...">
                                <div class="carousel-caption">
                                </div>
                            </div>
                            ...
                        </div>


                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                <div align="center">
                    <p>@家和</p>
                </div>
                </div>

        </div>
    </div>

</div>


</body>
</html>

