<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar" %>
<%@page import="jp.gacha.AdminService" %>

<%
Calendar calNow = Calendar.getInstance();
Calendar calOld = Calendar.getInstance();
calOld.set(1970,0,1,0,0,0);
response.setDateHeader("Last-Modified", calNow.getTime().getTime());
response.setDateHeader("Expires", calOld.getTime().getTime());
response.setHeader("pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");

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
<font size="-1">

<div>TK日報フォーマット</div>
<div>[管理画面]</div>

<hr />

<form method="post" action="update">

<div>管理者からひとこと</div>
<div>
<textarea name="message" rows="2" cols="16"><%= message %></textarea>
</div>

<hr />

<input type="submit" value="更新" />

</form>

</font>
</body>
</html>
