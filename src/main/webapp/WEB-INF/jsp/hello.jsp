<%--
  Created by IntelliJ IDEA.
  User: skuro
  Date: 8/2/12
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Welcome to the Spring Security Switch User sample</h1>
<p>This part of the page should be loaded using a different SecurityContext than the iframe below.</p>
<strong>I am: </strong> ${username}
<hr />
<iframe src="/dumper?j_switch_username=user" />
</body>
</html>