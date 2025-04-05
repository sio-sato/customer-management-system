<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.CustomerBean, model.entity.AreaBean, model.entity.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集完了画面</title>
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
	height: 65%;
	margin: auto;
	margin-top: 30px;
	border-radius: 5px;
} 
.edit_comp_p {
	text-align: left;
	margin: 0px;
	padding: 0px 10px;
	border-left: solid 5px #318fa8;
	font-size: 20px;
	margin-bottom: 40px;
}
.edit_comp_form {
	display: flex;
	align-items: center;
}
.edit_comp_form_name {
	text-align: left;
	width: 250px;
}
.edit_comp_text {
	margin-left: auto;
	width: 250px;
	height: 30px;
}
.edit_comp_button {
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
			<% 
			   CustomerBean customer = (CustomerBean) request.getAttribute("edit"); 
			%>
			
			<p class="edit_comp_p">更新完了</p>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">顧客名：</div>
			  	<div class="edit_comp_text"><%= customer.getCustomerName() %></div>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">顧客名かな：</div>
				<div class="edit_comp_text"><%= customer.getCustomerNameKana() %></div>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">郵便番号：</div>
				<div class="edit_comp_text"><%= customer.getPostalCode() %></div>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">所在地：</div>
				<div class="edit_comp_text"><%= customer.getAddress() %></div>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">地区：</div>
			    <% 
			      AreaBean area = (AreaBean)request.getAttribute("area");
			  	  if (area != null) {
			  	%> 
			    <div class="edit_comp_text"><%= area.getAreaName() %></div>
			 	 <% } %>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">担当者名：</div>
				<div class="edit_comp_text"><%= customer.getContactPersonName() %></div>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">担当者名かな：</div>
				<div class="edit_comp_text"><%= customer.getContactPersonNameKana() %></div>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">担当者電話番号：</div>
				<div class="edit_comp_text"><%= customer.getContactPersonTel() %></div>
			</div>
			<br>
			<div class="edit_comp_form">
				<div class="edit_comp_form_name">営業担当者：</div>
				 <% 
			      UserBean user = (UserBean)request.getAttribute("user");
			  	  if (user != null) {
			  	%> 
			    <div class="edit_comp_text"><%= user.getUserName() %></div>
			 	 <% } %>
			</div>
			<br>
			<br>
	  		<form action="listCustomer" method="get">
    			<input type="submit" value="顧客リストへ" class="edit_comp_button">
  			</form>
  		</div>
	</body>
</html>