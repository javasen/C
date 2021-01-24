<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/13
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form action="/upload2" enctype="multipart/form-data" method="post">
            <input type="text" name="desc"><br>
            <input type="file" name="uploadFile"> <br>
            <input type="submit" value="提交">
        </form>

</body>
</html>
