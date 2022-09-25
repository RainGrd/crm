<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>演示自动补全插件</title>
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>
    <link href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
    <script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>
    <script>
        $(function () {
            $('#customerName').typeahead({
                source: function (jquery, process) {
                    /*收集参数*/
                    let name = $('#customerName').val();
                    /*发送Ajax请求*/
                    $.ajax({
                        url: 'workbench/transaction/queryAllCustomerName.do',
                        type: 'get',
                        dataType: 'json',
                        data:{
                            name:name,
                        },
                        success: function (data) {
                            console.log(data)
                            /*赋值*/
                            process(data);
                        }, error: function (error) {
                            console.log('出错了')
                        }
                    })
                }

            })
        })
    </script>
</head>
<body>
<input type="text" id="customerName">
</body>
</html>
