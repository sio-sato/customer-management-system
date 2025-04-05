<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
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
.background {
	padding: 40px;
	text-align: center;
} 
input {
	width: 100px;
	height: 100px;
	margin: 80px 100px 0px 100px;
	border-radius: 20px;
	border: 3px solid #666;
	
}
.menu_form {
	display: inline-block;
}
.menu_p {
	font-family: 'Avenir','Helvetica Neue','Helvetica','Arial','Hiragino Sans','ヒラギノ角ゴシック',YuGothic,'Yu Gothic','メイリオ', Meiryo,'ＭＳ Ｐゴシック','MS PGothic';
}
</style>

<body>
	<div class="img">
	<jsp:include page="header.jsp" flush="true" />
		<div class="background">
			<form action="registerCustomer" method="get" class="menu_form">
  				<input type="image" src="img/menu2.png" alt="顧客登録" class="">
  				<p class="menu_p">顧客登録</p>
			</form>
    		<form action="listCustomer" method="get" class="menu_form">
      			<input type="image" src="img/menu1.png" alt="顧客リスト表示" class="">
      			<p class="menu_p">顧客リスト</p>
    		</form>
    		<form action="TaskCalendarServlet" method="get" class="menu_form">
      			<input type="image" src="img/menu4.png" alt="タスク管理カレンダー" class="">
      			<p class="menu_p">タスク管理カレンダー</p>
    		</form>
    		<% int num = (int)session.getAttribute("manager");
    		if ( num == 1) { %>
    		<form action="NewUserRegister" method="get" class="menu_form">
      			<input type="image" src="img/menu3.png" alt="新規アカウント作成" class="">
      			<p class="menu_p">新規アカウント作成</p>
    		</form>
    		<% } %>
    	</div>
	</div>
</body>
</html>