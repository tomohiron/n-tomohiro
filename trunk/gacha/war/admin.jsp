<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="jp.gacha.AdminService" %>

<%
String message = AdminService.getMessage();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="content-style-type" content="text/css">
<title>TK日報フォーマット[管理画面]</title>
</head>

<body>

<div>TK日報フォーマット[管理画面]</div>

<hr />

<form method="post" action="update">

<div>管理者からのメッセージ</div>
<div>
<textarea name="message" rows="2"><%= message %></textarea>
</div>

<hr />

<input type="submit" value="更新" />

</form>

</body>
</html>
