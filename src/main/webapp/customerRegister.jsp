<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.List,model.entity.AreaBean, model.entity.UserBean" 
    import="javax.servlet.http.HttpSession" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客登録画面</title>
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
.register_p {
	text-align: left;
	margin: 0px;
	padding: 0px 10px;
	border-left: solid 5px #318fa8;
	font-size: 20px;
	margin-bottom: 40px;
}
.register_form {
	display: flex;
	align-items: center;
}
.register_form_name {
	text-align: left;
	width: 250px;
}
.register_box {
	margin-left: auto;
	width: 250px;
	height: 30px;
	border-radius: 5px;
	border: 1px solid;
}
.name_side {
	background-color: #f33;
	color: #fff;
	border-radius: 5px;
	border: none;
	width: 50px;
	text-align: center;
	display: inline-block;
}
.register_button {
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
			List<AreaBean> areaList = (List<AreaBean>)request.getAttribute("areaList");
		    List<UserBean> userList = (List<UserBean>)request.getAttribute("userList");
		%>

  		<p class="register_p">顧客情報登録</p>
  
  		<form action="registerCustomer" method="post">
  			<div class="register_form">
  				<div class="register_form_name">顧客名
  					<div class="name_side">必須</div>
  				</div>
  				<input type="text" name="customerName" class="register_box">
  			</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">顧客名かな</div>
  				<input type="text" name="customerNameKana" class="register_box">
  			</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">郵便番号</div>
  				<input type="text" name="postalCode" pattern="\d{3}-\d{4}" title="郵便番号は、XXX-XXXXの形式で入力してください" placeholder="000-0000" class="register_box">
  			</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">所在地</div>
  				<input type="text" name="address" class="register_box">
  			</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">地区</div>
  				<select name="area" class="register_box">
  				<% for (AreaBean area : areaList){ %>
    				<option value="<%=area.getAreaCode() %>"><%=area.getAreaName() %></option>
   		 		<% } %>
       			</select>
       		</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">担当者名
  					<div class="name_side">必須</div>
  				</div>
  				<input type="text" name="personName" class="register_box">
  			</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">担当者名かな</div>
  				<input type="text" name="personNameKana" class="register_box">
  			</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">担当者電話番号
  					<div class="name_side">必須</div>
  				</div>
  				<input type="text" name="personTel" class="register_box">
  			</div>
  			<br>
  			<div class="register_form">
  				<div class="register_form_name">営業担当者</div>
  				<select name="user" class="register_box">
  				<% for (UserBean user : userList){ %>
    				<option value="<%=user.getUserId() %>"><%=user.getUserName() %></option>
   		 		<% } %>
       			</select>
  			</div>
  			<br>
  			<br>
 	 		<input type="submit" value="登録" class="register_button">
  		</form>
  		</div>
	</div>
   
</body>
</html>