<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.CustomerBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規タスク登録画面</title>
</head>
<style>
body {
	font-family: 'Avenir', 'Helvetica Neue', 'Helvetica', 'Arial',
		'Hiragino Sans', 'ヒラギノ角ゴシック', YuGothic, 'Yu Gothic', 'メイリオ', Meiryo,
		'ＭＳ Ｐゴシック', 'MS PGothic';
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
	border-radius: 5px;
	text-align: center;
	background-color: #fff;
	width: 35%;
	height: 65%;
	margin: auto;
	margin-top: 30px;
}
.task_p {
	text-align: left;
	margin: 0px;
	padding: 0px 10px;
	border-left: solid 5px #318fa8;
	font-size: 20px;
	margin-bottom: 40px;
}
.task_box1 {
	margin-left: auto;
	margin-bottom: 30px;
	width: 320px;
	height: 40px;
	border-radius: 5px;
	border: 1px solid;
	font-size: 16px;
}
.task_box2 {
	margin: 0;
	width: 100px;
	height: 30px;
	border-radius: 5px;
	border: 1px solid;
}
.task_box3 {
	margin-left: auto;
	margin-bottom: 50px;
	width: 320px;
	height: px;
	border-radius: 5px;
	border: 1px solid;
	font-size: 16px;
}
.time_box {
	display: flex;
	width: 320px;
	margin: auto;
	margin-bottom: 30px;
}
.time_p {
	margin: auto;
	
}
.task_button {
    width: 220px;
	height: 40px;
	background-color: #318fa8;
	color: #fff;
	border-radius: 10px;
	border: none;
	font-size: 20px;
}
</style>

<body>
	<div class="img">
		<jsp:include page="header.jsp" flush="true" />
		<div class="background">

			<p class="task_p">新規タスク登録</p>

			<form action="NewTaskServlet" method="post">
				<input type="text" name="taskTitle" placeholder="タイトル" class="task_box1">
				<br>
				<div class="time_box">
				<p class="time_p">開始</p>
				<br>
				<input type="date" name="dateStart" class="task_box2">
				<br>
				<p class="time_p">終了</p>
				<br>
				<input type="date" name="dateStop" class="task_box2">
				</div>
				<textarea name="memo" placeholder="メモ" rows="20" class="task_box3"></textarea>
				<input type="submit" value="保存" class="task_button">
			</form>
		</div>
	</div>
</body>
</html>