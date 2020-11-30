<!doctype html>
<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/bootstrap/js/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            $.get("${pageContext.request.contextPath}/user/user.json",function () {





            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '用户统计信息',
                    link: "${pageContext.request.contextPath}/main/main.jsp"
                },
                tooltip: {}, //鼠标移动提示
                legend: {
                    data:['男生','女生']  //选项卡
                },
                xAxis: {
                    data: ["一月","二月","三月","四月","五月","六月"]
                },
                yAxis: {},
                series: [{  //里面存放数据
                    name: '男生',
                    type: 'bar',
                    data: [60, 70, 36, 40, 10, 70]
                },
                    {
                        name: '女生',
                        type: 'bar',
                        data: [5, 20, 40, 10, 50, 90]
                    }

                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            },"json")
        });

    </script>

</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>







</body>
</html>