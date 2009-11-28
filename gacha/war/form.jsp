<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar" %>

<%
Calendar cal = Calendar.getInstance();
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH) + 1;
int date = cal.get(Calendar.DATE);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>TK日報フォーマット</title>
</head>

<body>

<div>TK日報フォーマット</div>
<div>ver.1.0</div>

<hr />

<form method="post" action="gacha">

<div>
<select name="year" >
<% for(int i=2009;i<=2011;i++) {
     if( i == year ) { %>
  <option value="<%= i %>" selected="selected"><%= i %></option>
<%   } else { %>
  <option value="<%= i %>"><%= i %></option>
<%   }
   } %>
</select>
年
<select name="month" >
<% for(int i=1;i<=12;i++) {
     if( i == month ) { %>
  <option value="<%= i %>" selected="selected"><%= i %></option>
<%   } else { %>
  <option value="<%= i %>"><%= i %></option>
<%   }
   } %>
</select>
月
<select name="date" >
<% for(int i=1;i<=31;i++) {
     if( i == date ) { %>
  <option value="<%= i %>" selected="selected"><%= i %></option>
<%   } else { %>
  <option value="<%= i %>"><%= i %></option>
<%   }
   } %>
</select>
日 
</div>
<div>
現場名：<input type="text" name="genba" />
</div>

<hr />

<div>
<input type="text" name="group01" />
×
<select name="amount01">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
</select>
名
</div>
<div>
<input type="text" name="group02" />
×
<select name="amount02">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
</select>
名
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
