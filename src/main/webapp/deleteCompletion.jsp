<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除完了画面</title>
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
.delete_comp_p {
	text-align: center;
	margin: 0px;
	padding: 0px 10px;
	font-size: 30px;
	margin-bottom: 40px;
}
.delete_comp_button {
    width: 180px;
	height: 40px;
	background-color: #318fa8;
	color: #fff;
	border-radius: 10px;
	border: none;
	font-size: 20px;
	margin-bottom: 30px;
}
.delete_box {
	margin: auto;
}
</style>

<body>
	<div class="img">
	<jsp:include page="header.jsp" flush="true" />
		<div class="background">
			<div class="delete_box">
  				<p class="delete_comp_p">削除完了</p>
 	  			<form action="listCustomer" method="get">
    				<input type="submit" value="顧客リストへ" class="delete_comp_button">
  				</form>
  			</div>
  		</div>
  	</div>
  

</body>
</html>