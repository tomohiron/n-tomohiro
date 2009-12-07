<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.ResourceBundle" %>
<%@page import="java.util.TimeZone" %>
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

ResourceBundle bundle = ResourceBundle.getBundle("gacha");
String maxString = bundle.getString("group.max");
int max = Integer.parseInt(maxString);

TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
Calendar cal = Calendar.getInstance(tz);
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH) + 1;
int date = cal.get(Calendar.DATE);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="content-style-type" content="text/css">
<title>TK日報フォーマット</title>
</head>

<body>

<div>TK日報フォーマット ver.1.1</div>
<div>(管理者:村田尚彌)</div>

<hr />

<div>管理者からのメッセージ</div>
<textarea rows="2" readonly="readonly"><%= message %></textarea>

<hr />

<form method="post" action="send">

<div>
<select name="year" >
<% for(int i=2009;i<=2011;i++) {
     if( i == year ) { %>
  <option value="<%= i %>" selected="selected"><%= i %>年</option>
<%   } else { %>
  <option value="<%= i %>"><%= i %>年</option>
<%   }
   } %>
</select><select name="month" >
<% for(int i=1;i<=12;i++) {
     if( i == month ) { %>
  <option value="<%= i %>" selected="selected"><%= i %>月</option>
<%   } else { %>
  <option value="<%= i %>"><%= i %>月</option>
<%   }
   } %>
</select><select name="date" >
<% for(int i=1;i<=31;i++) {
     if( i == date ) { %>
  <option value="<%= i %>" selected="selected"><%= i %>日</option>
<%   } else { %>
  <option value="<%= i %>"><%= i %>日</option>
<%   }
   } %>
</select>
</div>

<div>
送信者:<input type="text" name="soushin" />
</div>
<div>
現場名:<input type="text" name="genba" />
</div>

<hr />

<% for(int count=1;count<=max;count++) { %>

<div>
<select name="group<%= count %>" >
  <option value=""></option>
<%   for(int i=1;true;i++) {
       if( !bundle.containsKey("group."+i) ) {
         break;
       }
       String s = bundle.getString("group."+i); %>
  <option value="<%= s %>"><%= s %></option>
<%   } %>
</select>x<select name="amount<%= count %>">
  <option value="0">0名</option>
  <option value="1">1名</option>
  <option value="2">2名</option>
  <option value="3">3名</option>
  <option value="4">4名</option>
  <option value="5">5名</option>
  <option value="6">6名</option>
  <option value="7">7名</option>
  <option value="8">8名</option>
  <option value="9">9名</option>
</select>
</div>

<% } %>

<div>
合計:<input type="text" name="total" size="2"
       istyle="4" format="*N" mode="numeric" />名
</div>

<hr />

<div>
連絡事項・前日の残業等
</div>
<div>
<textarea name="memo" rows="3"></textarea>
</div>

<hr />

<input type="submit" value="送信！" />

</form>

</body>
</html>
