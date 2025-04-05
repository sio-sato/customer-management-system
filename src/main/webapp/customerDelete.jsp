<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.CustomerBean, model.entity.AreaBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
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
	background-color: #fff;
	width: 35%;
	height: 65%;
	margin: auto;
	margin-top: 30px;
	border-radius: 5px;
}

.delete_p {
	text-align: left;
	margin: 0px;
	padding: 0px 10px;
	border-left: solid 5px #318fa8;
	font-size: 20px;
	margin-bottom: 40px;
}

.delete_form {
	display: flex;
	align-items: center;
}

.delete_form_name {
	text-align: left;
	width: 250px;
}

.delete_text {
	margin-left: auto;
	width: 250px;
	height: 30px;
}

.delete_button {
	width: 180px;
	height: 40px;
	background-color: #318fa8;
	color: #fff;
	border-radius: 10px;
	border: none;
	font-size: 20px;
	margin-bottom: 30px;
}

.cancel_button {
	width: 180px;
	height: 40px;
	background-color: #fff;
	color: #318fa8;
	border-radius: 10px;
	border: solid 2px #318fa8;
	font-size: 20px;
}
</style>

<body>
	<div class="img">
		<jsp:include page="header.jsp" flush="true" />
		<div class="background">
			<% 
			   CustomerBean customer = (CustomerBean) request.getAttribute("Delete"); 
			   if(customer != null){
			%>
			<%   /* ログインしているユーザー（営業担当）を表示する */
			   session = request.getSession(false);  /* セッションの新規作成はしない */
			   String userId = null;
			   String userName = null;
			   if (session != null) {
				   userId = (String) session.getAttribute("user");
				   userName = (String) session.getAttribute("userName");
			   } else {
				   userId = null;
			   }
			%>

			<p class="delete_p">削除確認画面</p>

			<div class="delete_form">
				<div class="delete_form_name">顧客名：</div>
				<div class="delete_text"><%= customer.getCustomerName() %></div>
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">顧客名かな：</div>
				<div class="delete_text"><%= customer.getCustomerNameKana() %></div>
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">郵便番号：</div>
				<div class="delete_text"><%= customer.getPostalCode() %></div>
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">所在地：</div>
				<div class="delete_text"><%= customer.getAddress() %></div>
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">地区：</div>
				<% List<AreaBean> areaList = (List<AreaBean>)request.getAttribute("AreaList");
			  		String areaName = "不明";
			  		if (customer != null && areaList != null) {
				 		for (AreaBean area : areaList) {
					  		if (area.getAreaCode().equals(customer.getAreaCode())){
								areaName = area.getAreaName();
								break;
							}
						}
					}
				%>
				<div class="delete_text"><%= areaName %></div>
				<!-- リストで選択された地区コードとプルダウンの地区コードが一致していたら地区名に変換して表示する -->
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">担当者名：</div>
				<div class="delete_text"><%= customer.getContactPersonName() %></div>
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">担当者名かな：</div>
				<div class="delete_text"><%= customer.getContactPersonNameKana() %></div>
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">担当者電話番号：</div>
				<div class="delete_text"><%= customer.getContactPersonTel() %></div>
			</div>
			<br>
			<div class="delete_form">
				<div class="delete_form_name">営業担当者：</div>
				<% if (userName != null) { %>
				<div class="delete_text"><%= userName %></div>
				<% } %>
			<% } %>
			</div>
			<br> <br>
			<form action="deleteCustomer" method="post">
				<input type="hidden" name="customerId"
					value="<%= request.getAttribute("customerId") %>"> <input
					type="submit" class="delete_button" value="削除する">
			</form>
			<form action="listCustomer" method="get">
				<input type="submit" class="cancel_button" value="キャンセル">
			</form>
		</div>
	</div>
</body>
</html>