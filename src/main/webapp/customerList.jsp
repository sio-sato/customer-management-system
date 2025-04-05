<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.CustomerBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報一覧画面</title>
</head>
<style>
body {
	font-family: 'Avenir','Helvetica Neue','Helvetica','Arial','Hiragino Sans','ヒラギノ角ゴシック',YuGothic,'Yu Gothic','メイリオ', Meiryo,'ＭＳ Ｐゴシック','MS PGothic';
}
.img {
    width: 100%;
	height: 100%;
	background-image: url(img/header2.png); 
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}
.background {
	padding: 20px;
	text-align: center;
	background-color: #fff;
	width: 95%;
	height: 100%;
	margin: auto;
	border-radius: 5px;
} 
.list_p {
	text-align: left;
	font-size: 25px;
	
}
.list_edit_button {
	width: 100%;
	height: 100%;
	background-color: #114b55;
	color: #fff;
	border-radius: 8px;
	border: 1px solid #114b55;
}
.list_delete_button {
	width: 100%;
	height: 100%;
	background-color: #fff;
	color: #000;
	border-radius: 8px;
}
.list_register_button {
	display: flex;
}
.list_register_button {
	width: 150px;
	height: 30px;
	border: none;
	background-color: #318fa8;
	color: #fff;
	border-radius: 8px;
	margin-top: 30px;
	justify-content: flex-start;
}

table {
	width: 100%;
	border-style:solid;
	margin: auto;
	font-size: 14px;
}
th {
	background: #5fb2b0;
	color: #000;
}
td {
	background: #fff;
}
tr:nth-child(odd) td {
	background: #eeeddc;
}
.search_box {
	display: flex;
}
.search_form {
	display: flex;
	margin: auto;
	margin-left: 30px;
	margin-right: 20px;
	border: solid 1px #333;
	border-radius: 8px;
	
}
.clear_form {
	display: flex;
	margin: auto;
	margin-left: 0px;
}
.list_box {
	display: flex;
	margin: auto;
	margin-right: 0px;
}
.search_form_input {
	width: 200px;
    height: 27px;
    border: none;
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
}
.clear_form_input{
	width: 100px;
    height: 27px;
    background-color: #318fa8;
	color: #fff;
	border-radius: 10px;
	border: none;
}
.search_form_button {
	background-image:url("img/search_icon.png");
    background-repeat: no-repeat;
    background-size: cover;
    background-color: #5fb2b0;
	width: 33px;
    height: 27xp;
    padding: auto;
    margin: autos;
    border: none;
    border-left: solid 1px #333;
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
}

</style>

<body>
	<div class="img">
	<jsp:include page="header.jsp" flush="true" />
		<div class="background">
			<% List<CustomerBean> customerList = (List<CustomerBean>) request.getAttribute("customerList"); %>
			<% session = request.getSession(false);
   			String userId = null;
   			String userName = null;
   			if (session != null) {
	   			userId = (String) session.getAttribute("user");
	   			userName = (String) session.getAttribute("userName");
   			} else {
	   			userId = null;
  			}
			%>
			<div class="search_box">
				<p class="list_p">顧客一覧</p>
				<form action="listCustomer" method="post" class="search_form">
			  		<input type="text" name="search" placeholder="キーワードで検索" class="search_form_input">
			  		<button type="submit" value="検索"  class="search_form_button"></button>
			  	</form>
			  	<form action="listCustomer" method="get" class="clear_form">
			  		<input type="submit" value="クリア" class="clear_form_input">
			  	</form>
			  	<% if (userName != null) { %>
			  			<p class="list_box">ログイン中：<%= userName %></p>
			    	<% } %>
			</div>
			<table border="1" style="border-collapse: collapse">
				<tr>
			    	<th>顧客ID</th>
			        <th>顧客名</th>
			        <th>顧客名かな</th>
			      	<th>郵便番号</th>
			      	<th>所在地</th>
			      	<th>地区</th>
			      	<th>担当者</th>
			     	<th>担当者かな</th>
			      	<th>担当者電話番号</th>
			      	<th>営業担当</th>
				</tr>
			 	<% for (CustomerBean customer : customerList) { %>
			  	<tr>
			  	  	<td><%= customer.getCustomerId() %></td>
			  	  	<td><%= customer.getCustomerName() %></td>
			  	  	<td><%= customer.getCustomerNameKana() %></td>
			  	  	<td><%= customer.getPostalCode() %></td>
			  	 	<td><%= customer.getAddress() %></td>
			  	  	<td><%= customer.getAreaName() %></td>
			  	  	<td><%= customer.getContactPersonName() %></td>
			  	  	<td><%= customer.getContactPersonNameKana() %></td>
			  	  	<td><%= customer.getContactPersonTel() %></td>
			  	  	<td><%= customer.getUserName() %></td>
			  	 	<td>
			  		<form action="editCustomer" method="get">
			  	    <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
			  	    <input type="submit" value="編集" class="list_edit_button">
			  	  </form>
			  	  </td>
			  	  <td>
			  	  <form action="confirmDelete" method="post">
			  	    <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
			  	    <input type="submit" value="削除" class="list_delete_button">
			  	  </form>
			  	  </td>
			  	</tr>
			  	<% } %>
			</table>
			  <form action="registerCustomer" method="get" class="list_register_form">
			  <input type="submit" value="顧客登録画面へ" class="list_register_button">
			  </form>
		</div>
	</div>

</body>
</html>