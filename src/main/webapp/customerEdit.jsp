<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.AreaBean, model.entity.CustomerBean, model.entity.UserBean,javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客編集画面</title>
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

.edit_p {
	text-align: left;
	margin: 0px;
	padding: 0px 10px;
	border-left: solid 5px #318fa8;
	font-size: 20px;
	margin-bottom: 40px;
}

.edit_form {
	display: flex;
	align-items: center;
}

.edit_form_name {
	text-align: left;
	width: 250px;
}

.edit_box {
	margin-left: auto;
	width: 250px;
	height: 30px;
	border-radius: 5px;
	border: 1px solid;
}

.edit_name_side {
	background-color: #f33;
	color: #fff;
	border-radius: 5px;
	border: none;
	width: 50px;
	text-align: center;
	display: inline-block;
}

.edit_button {
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
				List<AreaBean> areaList = (List<AreaBean>)request.getAttribute("AreaList");
   				CustomerBean customer = (CustomerBean)request.getAttribute("customer");
   				List<UserBean> userList = (List<UserBean>)request.getAttribute("userList");
   				String selectedAreaCode = (String) session.getAttribute("selectedAreaCode"); 
   				String selectedUserName = (String) session.getAttribute("selectedUserName");
   			%>

			<p class="edit_p">顧客編集画面</p>

			<form action="editCustomer" method="post">
				<input type="hidden" name="customerId"
					value="<%= request.getAttribute("customerId") %>">
				<div class="edit_form">
					<div class="edit_form_name">
						顧客名
						<div class="edit_name_side">必須</div>
					</div>
					<input type="text" name="customerName" class="edit_box" value="<%= customer.getCustomerName() %>">
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">顧客名かな</div>
					<input type="text" name="customerNameKana" class="edit_box" value="<%= customer.getCustomerNameKana() %>">
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">郵便番号</div>
					<input type="text" name="postalCode" class="edit_box" value="<%= customer.getPostalCode() %>" pattern="\d{3}-\d{4}" title="郵便番号は、XXX-XXXXの形式で入力してください">
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">所在地</div>
					<input type="text" name="address" class="edit_box" value="<%= customer.getAddress() %>">
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">地区</div>
					<select name="area" class="edit_box">
						<!-- 地区名を引き継いだ状態で表示 -->
						<% for (AreaBean area : areaList){ %>
						<option value="<%=area.getAreaCode() %>"
							<%= (area.getAreaCode().equals(selectedAreaCode)) ? "selected" : "" %>><%=area.getAreaName() %></option>
						<% } %>
					</select>
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">
						担当者名
						<div class="edit_name_side">必須</div>
					</div>
					<input type="text" name="personName" class="edit_box" value="<%= customer.getContactPersonName() %>">
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">担当者名かな</div>
					<input type="text" name="personNameKana" class="edit_box" value="<%= customer.getContactPersonNameKana() %>">
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">
						担当者電話番号
						<div class="edit_name_side">必須</div>
					</div>
					<input type="text" name="personTel" class="edit_box" value="<%= customer.getContactPersonTel() %>">
				</div>
				<br>
				<div class="edit_form">
					<div class="edit_form_name">営業担当者</div>
					<select name="user" class="edit_box">
						<% for (UserBean user : userList){ %>
						<option value="<%=user.getUserId() %>"
							<%= (user.getUserName().equals(selectedUserName)) ? "selected" : "" %>><%= user.getUserName() %></option>
						<% } %>
					</select>
				</div>

				<br>
				<br>
				<input type="submit" value="更新" class="edit_button">
			</form>
		</div>
	</div>

</body>
</html>