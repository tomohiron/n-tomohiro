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
<font size="-1">

<div>TK日報フォーマット</div>
<div>(管理者:村田尚彌)</div>

<hr />

<div>管理者からひとこと</div>
<textarea rows="2" cols="16" readonly="readonly"><%= message %></textarea>

<hr />

<form method="post" action="send">

<div>
<select name="year" >
<% for(int i=2009;i<=2011;i++) {
     if( i == year ) { %>
  <option value="<%= i %>" selected="selected"><%= i %></option>
<%   } else { %>
  <option value="<%= i %>"><%= i %></option>
<%   }
   } %>
</select>/<select name="month" >
<% for(int i=1;i<=12;i++) {
     if( i == month ) { %>
  <option value="<%= i %>" selected="selected"><%= i %></option>
<%   } else { %>
  <option value="<%= i %>"><%= i %></option>
<%   }
   } %>
</select>/<select name="date" >
<% for(int i=1;i<=31;i++) {
     if( i == date ) { %>
  <option value="<%= i %>" selected="selected"><%= i %></option>
<%   } else { %>
  <option value="<%= i %>"><%= i %></option>
<%   }
   } %>
</select>
</div>

<div>
送信者:<input type="text" name="soushin" size="10" />
</div>
<div>
現場名:<input type="text" name="genba" size="10" />
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
  <option value="0">0</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
</select>名
</div>

<% } %>

<div>
合計:<input type="text" name="total" size="2"
       istyle="4" format="*N" mode="numeric" />名
</div>

<hr />

<div>
連絡事項等
</div>
<div>
<textarea name="memo" rows="3" cols="16" ></textarea>
</div>

<hr />

<input type="submit" value="送信！" />

<hr />

<div>version.1.2</div>
<div>powered by Mac</div>

</form>

</font>
</body>
</html>
