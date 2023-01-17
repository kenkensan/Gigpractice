<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dto.Kadaidto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			Kadaidto ka = (Kadaidto)session.getAttribute("input_data");
	%>
		<p style="color:blue">登録に失敗しました。</p>
		<h3>新規会員登録</h3>
		<form action="Kadai1Confirm" method="post">
			名前：<input type="text" name="name" value="<%=ka.getName()%>"><br>
			年齢：<input type="number" name="age" value="<%=ka.getAge()%>"><br>
			性別:<input type="radio" name="gender" value="<%=ka.getGender()%>">男
		<input type="radio" name="gender" value="<%=ka.getGender()%>">女<br>
			電話番号：<input type="number" name="tell" value="<%=ka.getTell()%>"><br>
			メール：<input type="email" name="mail" value="<%=ka.getMail()%>"><br>
			パスワード：<input type="password" name="pw"><br>
			<input type="submit" value="登録">
		</form>
	<%
		} else {
	%>
	<h3>新規会員登録</h3>
	<form action="Kadai1Confirm" method="post">
		名前：<input type="text" name="name"><br>
		年齢：<input type="number" name="age" ><br>
			性別:<input type="radio" name="gender" value="0">男
		<input type="radio" name="gender" value="1">女<br>
			電話番号：<input type="number" name="tell"><br>
					メール：<input type="email" name="mail"><br>
		パスワード：<input type="password" name="pw"><br>
		<input type="submit" value="登録">
	</form>
	<%
		}
	%>
</body>
</html>