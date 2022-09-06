<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--导入JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <script src="jquery/jquery-1.11.1-min.js"></script>
    <title>演示文件下载</title>
    <script type="text/javascript">
        $(function (){
            /**
             * 下载按钮添加单击事件
             */
            $('#fileDownloadBtn').on('click',function () {
                /*发送文件下载的请求*/
                window.location.href="workbench/activity/fileDownload.do";
            })
        });
    </script>
</head>
<body>
<button type="button" value="文件下载" id="fileDownloadBtn">文件下载</button>
</body>
</html>
