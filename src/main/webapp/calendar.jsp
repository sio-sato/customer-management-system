<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.CalendarBean"%>
<% 
	CalendarBean cb = (CalendarBean)request.getAttribute("cb");
	if (cb == null) {
		cb = new CalendarBean(); // `cb` が `null` の場合は仮のインスタンスを作成
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理画面</title>
</head>
<style>
body {
	font-family: 'Avenir', 'Helvetica Neue', 'Helvetica', 'Arial',
		'Hiragino Sans', 'ヒラギノ角ゴシック', YuGothic, 'Yu Gothic', 'メイリオ', Meiryo,
		'ＭＳ Ｐゴシック', 'MS PGothic';
}

.img {
	width: 100%;
	height: 1300px;
	background-image: url(img/header2.png);
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}

.background {
	padding: 70px;
	text-align: center;
	background-color: #fff;
	width: 70%;
	height: 70%;
	margin: auto;
	border-radius: 5px;
}

.today {
	background-color: #eff7cb;
	vertical-align: top;
}

.table_box {
	display: inline-block;
	magin: auto;
}

.calendar_th {
	width: 100px;
	height: auto;
	background-color: #318fa8;
	color: #fff;
}

.calendar_tr {
	height: 80px;
}

.calendar_td {
	vertical-align: top;
}

.title_box {
	display: flex;
}

.title_a {
	margin: auto;
	width: 100px;
	height: 40px;
	line-height: 40px;
	background-color: #808080;
	color: #fff;
	border-radius: 10px;
	border: none;
	text-decoration: none;
}

.task_register_button {
	width: 200px;
	height: 40px;
	background-color: transparent;
	color: #000000;
	border-radius: 10px;
	border: 2px solid #000000;
	font-weight: bold;
	margin: 20px auto;
}

.task_register_form {
	text-align: left;
}
</style>

<body>
	<div class="img">
		<jsp:include page="header.jsp" flush="true" />
		<div class="background">
			<form action="NewTaskServlet" method="get" class="task_register_form">
				<input type="submit" value="新規タスク登録" class="task_register_button">
			</form>
			<%
   				int prevYear = cb.getYear();
    			int prevMonth = cb.getMonth() - 1;
    			if (prevMonth < 1) {
        			prevMonth = 12;	
        			prevYear--;
    			}

    			int nextYear = cb.getYear();
    			int nextMonth = cb.getMonth() + 1;
    			if (nextMonth > 12) {
        			nextMonth = 1;
       				nextYear++;
   				}
			%>
			<div class="title_box">
				<a href="?year=<%= prevYear %>&month=<%= prevMonth %>" class="title_a">前月</a>
				<h2><%= cb.getGengou() %>年<%= cb.getMonth() %>月</h2>

				<a href="?year=<%= nextYear %>&month=<%= nextMonth %>" class="title_a">翌月</a>
			</div>
			<table class="table_box">
				<tr class="calendar_tr">
					<th class="calendar_th">日</th>
					<th class="calendar_th">月</th>
					<th class="calendar_th">火</th>
					<th class="calendar_th">水</th>
					<th class="calendar_th">木</th>
					<th class="calendar_th">金</th>
					<th class="calendar_th">土</th>
				</tr>
				<% 
					String[][] data = cb.getData();
					for(int i = 0; i < data.length; i++) { 
				%>
				<tr class="calendar_tr">
					<% for(int j = 0; j < data[i].length; j++) { %>
					<% String col = data[i][j]; %>

					<% if(i == cb.getTodayRow() && j == cb.getTodayCol()) { %>
					<td class="today"><%= col %></td>
					<% }else{ %>
					<td class="calendar_td"><%= col %></td>
					<% } %>

					<% } %>
				</tr>
				<% } %>
			</table>
		</div>
	</div>

</body>
</html>