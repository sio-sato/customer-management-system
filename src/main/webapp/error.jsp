<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面</title>
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
	padding: 80px;
	text-align: center;
	background-color: #fff;
	width: 35%;
	height: 25%;
	margin: auto;
	margin-top: 30px;
	border-radius: 5px;
	display: flex;
} 
.error_p {
	text-align: center;
	margin: 0px;
	padding: 0px 10px;
	font-size: 30px;
	margin-bottom: 40px;
}
.error_p2 {
	text-align: center;
	margin: 0px;
	padding: 0px 10px;
	font-size: 20px;
	color: red;
	margin-bottom: 40px;
}
.error_button {
    width: 180px;
	height: 40px;
	background-color: #318fa8;
	color: #fff;
	border-radius: 10px;
	border: none;
	font-size: 20px;
	margin-bottom: 30px;
}
.error_box {
	margin: auto;
}
</style>

<body>
	<div class="img">
	<jsp:include page="header.jsp" flush="true" />
		<div class="background">
			<div class="error_box">
  				<p class="error_p">エラーが発生しました</p>
 			 	<% String message = (String)request.getAttribute("message");
    				if(message != null) {
  				%>
   			 	<p class="error_p2"><%= message %></p>
  				<% } %>
  				<form action="listCustomer" method="get">
    				<input type="submit" value="顧客リストへ戻る" class="error_button">
  				</form>
  			</div>
  		</div>
  	</div>

</body>
</html>