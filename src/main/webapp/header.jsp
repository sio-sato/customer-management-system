<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヘッダー</title>
</head>

<style>
header {
    width: auto;
	display: flex;
	align-items: center;
	padding: 0px 50px;
	background-color: none;
}
.header_img {
    width: auto;
	display: flex;
	align-items: center;
	padding: 20px 50px;
	margin: auto;
}

.header_button {
	width: 100px;
	height: 40px;
	background-color: transparent;
	color: #fff;
	border-radius: 10px;
	border: 2px solid #fff;
	font-weight: bold;
	margin: auto;
}
a {
    margin: 0px 10.56px;
    text-decoration: none;
    width: 100px;
	height: 40px;
	line-height: 40px;
	background-color: transparent;
	color: #fff;
	border-radius: 10px;
	border: 2px solid #fff;
	font-weight: bold;
	text-align: center;
}

</style>

<body>

  <header>
    <a href="menu.jsp">メニューへ</a>
    <img src="img/rogo3.PNG" alt="ロゴ"  height="100px" class="header_img">
    <form action="logout" method="post" class="header_form">
      <input type="submit" value="ログアウト" class="header_button">
    </form>
  </header>

</body>
</html>