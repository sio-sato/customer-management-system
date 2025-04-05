<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.AccountBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザー登録画面</title>
</head>

<style>
body {
	font-family: 'Avenir','Helvetica Neue','Helvetica','Arial','Hiragino Sans','ヒラギノ角ゴシック',YuGothic,'Yu Gothic','メイリオ', Meiryo,'ＭＳ Ｐゴシック','MS PGothic';
}
.img {
    width: 100%;
	height: 1000px;
	background-image: url(img/header2.png); 
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}
header {
    width: auto;
	display: flex;
	align-items: center;
	padding: 20px 50px;
}
img {
    text-align: center;
    margin: auto;
}
.background {
	background-color: #fff;
	width: 500px;
	height: auto;
	margin: auto;
	margin-top: 30px;
	padding: 40px;
	border-radius: 20px;
	text-align: center;
}
.login_text {
	border: 1px solid #999;
	width: 50%;
	height: 30px;
	margin: 10px;
	border-radius: 10px;
}
.button {
	width: 45%;
	height: 30px;
	margin-top: 0px;
	margin: 7px;
	background-color: #c74302;
	color: #fff;
	border-radius: 10px;
	border: none;
}
.button2 {
	width: 45%;
	height: 30px;
	margin: 7px;
	background-color: none;
	color: #c74302;
	border-radius: 10px;
	border: 1px solid #c74302;
}
.title {
	font-family: 'Avenir','Helvetica Neue','Helvetica','Arial','Hiragino Sans','ヒラギノ角ゴシック',YuGothic,'Yu Gothic','メイリオ', Meiryo,'ＭＳ Ｐゴシック','MS PGothic';
}


</style>

<body>
  <div class="img">
  <jsp:include page="header.jsp" flush="true" />
  	<div class="background">
  	<% List<AccountBean> accountList = (List<AccountBean>)request.getAttribute("accountList");%>
  	
  	<p class="title">新規ユーザー登録</p>
  	<form action="NewUserRegister" method="post">
    	<input type="text" name="id" placeholder="ユーザーID" class="login_text">
    	<br>
    	<input type="password" name="password" placeholder="パスワード" class="login_text">
    	<br>
    	<input type="text" name="name" placeholder="営業担当者名" class="login_text">
    	<br>
  		<select name="roleId" class="login_text">
  			<% for (AccountBean account : accountList){ %>
    			<option value="<%=account.getRoleId() %>"><%=account.getRoleName() %></option>
   		 	<% } %>
       	</select>
       	<br>
    	<input type="submit" value="登録" class="button"> 
    	<br>
    	<input type="reset" value="クリア" class="button2">
  	</form>
  	<% String error = (String)request.getAttribute("error"); %>
  	<%if (error != null) { %>
    	<p style="color: red;"><%= error %></p>	
 	 <% } %>
  	</div>
  </div>
</body>
</html>