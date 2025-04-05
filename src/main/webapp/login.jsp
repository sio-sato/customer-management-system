<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
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
	height: 300px;
	margin: auto;
	margin-top: 30px;
	padding: 20px;
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
	margin-top: 30px;
	margin: 7px;
	background-color: #318fa8;
	color: #fff;
	border-radius: 10px;
	border: none;
}
.button2 {
	width: 45%;
	height: 30px;
	margin: 7px;
	background-color: none;
	color: #318fa8;
	border-radius: 10px;
	border: 1px solid #318fa8;
}
.title {
	font-family: 'Avenir','Helvetica Neue','Helvetica','Arial','Hiragino Sans','ヒラギノ角ゴシック',YuGothic,'Yu Gothic','メイリオ', Meiryo,'ＭＳ Ｐゴシック','MS PGothic';
}


</style>

<body>
  <div class="img">
  <header>
    <img src="img/rogo3.PNG" alt="ロゴ"  height="100px">
  </header>
  
  	<div class="background">
  	
  	<p class="title">Sales Management System</p>
  	<form action="login" method="post">
    	<input type="text" name="id" placeholder="ユーザーID" class="login_text">
    	<br>
    	<input type="password" name="password" placeholder="パスワード" class="login_text">
    	<br>
    	<input type="submit" value="ログイン" class="button"> 
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