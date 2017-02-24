<%@page import="com.ball.bean.Seller"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%
String path = request.getContextPath();
String id = "";
if(request.getAttribute("s_id")!=null){
	int s_id = (Integer)request.getAttribute("s_id");
	id = "s"+s_id;
}
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>约球吧商家信息填写</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body {
			margin:0px;
  			width:100%;
  			min-width:1024px;
  			max-width:100%;
  			height:100%;
		}
		td:first-child{text-align:right}
		
		#btn {
			width: 150px;
			height: 40px;
			border: 1px solid #dedede;
			-moz-border-radius: 5px;      /* Gecko browsers */
   			-webkit-border-radius: 5px;   /* Webkit browsers */
   			border-radius:5px;        /* W3C syntax */
   			background: #BFBFBF;
   			color: black;
			font-size: 15px;
		}
		
		#file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
		
		#round {
   		width:100px; height:30px;
   		border: 1px solid #dedede;
   		-moz-border-radius: 5px;      /* Gecko browsers */
   		-webkit-border-radius: 5px;   /* Webkit browsers */
   		border-radius:5px;        /* W3C syntax */
   		background: #FF7F50;
   		color: white;
	}
		
		#round:HOVER {
	width:100px; height:30px;
   		border: 1px solid #dedede;
   		-moz-border-radius: 5px;      /* Gecko browsers */
   		-webkit-border-radius: 5px;   /* Webkit browsers */
   		border-radius:5px;        /* W3C syntax */
   		background: #FF7256;
   		color: white;
	} 
	span {
		font-size: 10px;
		margin-left: 10px;
	}
	</style>
	
	<script type="text/javascript">
		var isName = false;
		var isPhone = false;
		var isMail = false;
		var isIdCard = false;
		var isIdCardPic = false;
		var isLicenceNum = false;
		var isLicenceName = false;
		var isLicenceDate = false;
		var isLicencePic = false;
		function  hasLicence(){
			document.getElementById("nolicence").style.display="none";
			document.getElementById("haslicence").style.display="";
			document.getElementById("SellerIdCard").value = "";
		}
		
		function noLicence(){
			document.getElementById("haslicence").style.display="none";
			document.getElementById("nolicence").style.display="";
			document.getElementById("LicenceNum").value = "";
			document.getElementById("LicenceName").value = "";
			document.getElementById("inputdate").value = "";
			
		}
		
		function forever(){
			document.getElementById("inputdate").disabled = true;
			isLicenceDate = true;
		}
		
		function never(){
			document.getElementById("inputdate").disabled = false;
		}
		
		function checkPic(picPath){
			var type = picPath.substring(picPath.lastIndexOf(".")+1,picPath.length).toLowerCase();
			if(type != "jpg" && type != "bmp" && type != "gif" && type != "png"){
				alert("请您选择正确的图片文件");
				return false;
			}
			return true;
		}
		
		function preImg(sourceId, targetId) {
		var picPath;
		picPath = document.getElementById(sourceId).value;
		if(checkPic(picPath)){
			if (typeof FileReader === 'undefined') {  
        		alert('Your browser does not support FileReader...');  
        	switch(sourceId){
    			case "licence":
    				isLicencePic = false;
    			break;
    			case "idcard":
    				isIdCardPic = false;
    			break;
    		}
        		return;  
    		} 
    	var reader = new FileReader();  
      	switch(sourceId){
    			case "licence":
    				isLicencePic = true;
    			break;
    			case "idcard":
    				isIdCardPic = true;
    			break;
    		}
    	reader.onload = function(e) {  
        	var img = document.getElementById(targetId);  
        	img.src = this.result;  
    	};

    	reader.readAsDataURL(document.getElementById(sourceId).files[0]); 
		}else{
			  switch(sourceId){
    			case "licence":
    				isLicencePic = false;
    			break;
    			case "idcard":
    				isIdCardPic = false;
    			break;
    		} 
		}
} 

	function toVaild(){
		if(isName&&isPhone&&isMail){
			if(isLicenceNum&&isLicenceName&&isLicenceDate&&isLicencePic){
				return true;
			}else if(isIdCard&&isIdCardPic){
				return true;
			}else{
				alert("信息填写有误");
				return false;
			}
		}else{
			alert("信息填写有误");
			return false;
		}
	}
	
	function hint(x){
		var span = document.getElementById(x).parentNode.lastChild;
		switch(x){
			case "SellerName":
				span.innerHTML = "请填写联系人姓名";
			break;
			case "SellerPhone":
				span.innerHTML = "请填写联系人手机号码";
			break;
			case "SellerMail":
				span.innerHTML = "请填写联系人邮箱地址";
			break;
			case "LicenceNum":
				span.innerHTML = "请填写营销执照注册号";
			break;
			case "LicenceName":
				span.innerHTML = "请填写营销执照上公司名";
			break;
			case "SellerIdCard":
				span.innerHTML = "请填写有效身份证号";
			break;
		}
	}
	
	function hind(x){
			var span = document.getElementById(x).parentNode.lastChild;
			var value = document.getElementById(x).value;
			var phone = /(13[0-9]|14[57]|15[^4,\D]|17[678]|18\d)\d{8}|170[059]\d{7}/;
			var mail = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
		switch(x){
			case "SellerName":
				if(value.length==0){
					span.innerHTML = "联系人姓名不能为空";
					isName = false;
				}else{
					span.innerHTML = "";
					isName = true;
				}
			break;
			case "SellerPhone":
				if(value.length==0){
					span.innerHTML = "联系人手机号码不能为空";
					isPhone = false;
				}else if(value.length==11&&phone.test(value)){
					span.innerHTML = "";
					isPhone = true;
				}else{
					span.innerHTML = "请填写正确的格式";
					isPhone = false;
				}
				
			break;
			case "SellerMail":
				if(value.length==0){
					span.innerHTML = "联系人邮箱不能为空";
					isMail = false;
				}else if(mail.test(value)){
					span.innerHTML = "";
					isMail = true;
				}else{
					span.innerHTML = "请填写正确的格式";
					isMail = false;
				}
				
			break;
			case "LicenceNum":
				if(value.length==0){
					span.innerHTML = "营业执照注册码不能为空";
					isLicenceNum = false;
				}else if(!value.match("[0-9]+")){
						span.innerHTML = "请填写正确的格式";
						isLicenceNum = false;
				}else{
					span.innerHTML = "";
					isLicenceNum = true;
				}
			break;
			case "LicenceName":
				if(value.length==0){
					span.innerHTML = "营业执照公司名不能为空";
					isLicenceName = false;
				}else{
					span.innerHTML = "";
					isLicenceName = true;
				}
			break;
			case "SellerIdCard":
				if(value.length==0){
					span.innerHTML = "身份证号码不能为空";
					isIdCard = false;
				}else if(value.length==18&&value.match("[0-9]{17}[0-9Xx]")){
					span.innerHTML = "";
					isIdCard = true;
				}else if(value.length==15&&value.match("[0-9]{15}")){
					span.innerHTML = "";
					isIdCard = true;
				}else{
					span.innerHTML = "请填写正确的格式";
					isIdCard = false;
				}
			break;
		}
	}
	
	</script>

  </head>
  
  <body>
  <div style="margin: 0;position: relative;">
  	<div style="background: #4682B4;height: 70px; margin: 0px;width: 100%">
    		<img alt="WebTitle" src="WebImage/WebTitle_Sell.png" style="margin-left: 150px">
   	</div>
   	<form action="SellerInfoServlet?Seller_id=<%=id %>" method="post"  enctype="multipart/form-data" onsubmit="return toVaild()">
<%--    	<input type="hidden" name="Seller_id" value="<%=s_id%>"  /> --%>
   	<div style="height: 500px;width: 1000px;margin-top: 50px">
   		<h2 style="margin-left: 100px">联系人信息</h2>
   		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-left: 100px;" />
   			<table style="margin-left: 150px;margin-top: 20px;" cellpadding="10px">
   				<tr>
   					<td>联系人姓名</td>
   					<td><input type="text" id="SellerName" name="SellerName" style="width: 400px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span ></span></td>
   				</tr>
   				<tr>
   					<td>联系人手机号码</td>
   					<td><input type="text" id="SellerPhone" name="SellerPhone" style="width: 400px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span ></span></td>
   				</tr>
   				<tr>
   					<td>联系人邮箱地址</td>
   					<td><input type="text" id="SellerMail" name="SellerMail" style="width: 400px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span ></span></td>
   				</tr>
   			</table>
   			
   		<h2 style="margin-left: 100px">资质信息</h2>
   		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-left: 100px;" />
   		<table style="margin-left: 150px;margin-top: 20px;"  cellpadding="10px">
   			<tr>
   				<td>资质类型</td>
   				<td><input id="btn" type="button" value="我有营业执照" onclick="hasLicence()" /></td>
   				<td><input id="btn" type="button" value="暂时没有营业执照" onclick="noLicence()" /></td>
   			</tr>
   		</table>
   		<div id="haslicence" style="display: none;">
   		<table style="margin-left: 150px;margin-top: 20px;"  cellpadding="10px">
   			<tr>
   				<td>营业执照注册码</td>
   				<td><input type="text" id="LicenceNum" name="LicenceNum" style="width: 400px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span id="lincenceNumhint"></span></td>
   			</tr>
   			<tr>
   				<td>营业执照名称</td>
   				<td><input type="text" id="LicenceName" name="LicenceName" style="width: 400px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span id="lincenceNamehint"></span></td>
   			</tr>
   			<tr>
   				<td>营业执照到期时间</td>
   				<td><input name="licencedate" type="radio" value="1" onclick="forever()" checked="checked" />永久
   					<input name="licencedate" type="radio" value="2" onclick="never()"  />有期
   					<input id="inputdate" name="LicenceDate" type="date" onchange="isLicenceDate = true" />
   				</td>
   			</tr>
   			<tr>
   				<td>营业执照照片</td>
   				<td><img id="LicencePic" alt="营业执照照片" src="/WebImage/preview.png" width="200px" height="150px" ><br/>
   				<!-- <input id="LicencePicPath" name="LicencePicPath" type="hidden" value=""> -->
   				<input id="btn1" type="button" value="上传照片" style="position: absolute;margin-left: 0px;">
   				<input type="file" id="licence"  name="licence" style="position:absolute;filter:alpha(opacity=0);width:150px;opacity: 0 ;margin-left: 0px;height: 40px" onchange="preImg(this.id, 'LicencePic')"  />
   				</td>
   			</tr>
   			<tr>
   				<td></td>
 				<td></br><input id="round" type="submit" value="提交" /></td>
   			</tr>
   		</table>
   		
   		
   		</div>
   		<div id="nolicence" style="display: none;">
   		<table style="margin-left: 150px;margin-top: 20px;"  cellpadding="10px">
   			<tr>
   				<td>身份证号码</td>
   				<td><input type="text" id="SellerIdCard" name="SellerIdCard" style="width: 400px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span id="idcardhint"></span></td>
   			</tr>
   			<tr>
   				<td>身份证照片</td>
   				<td><img id="IdCardPic" alt="身份证照片" src="/WebImage/preview.png" width="200px" height="150px">
   					<br>
   					<!-- <input id="IdCardPicPath" name="IdCardPicPath" type="hidden" value=""> -->
   					<input id="btn2" type="button" value="上传照片" style="position: absolute;margin-left: 0px;">
   					<!--  document.getElementById('idcardpicpath').value=this.value-->
   					<input type="file" id="idcard" name="idcard" style="position:absolute;filter:alpha(opacity=0);width:150px;opacity: 0 ;margin-left: 0px;height: 40px" onchange="preImg(this.id, 'IdCardPic')"/>
   				</td>
			<tr>
   				<td></td>
 				<td></br><input id="round" type="submit" value="提交"/></td>
   			</tr>
   		</table >
   		</div>
   	</div>
   	</form>
   	
  </div>
  </body>
</html>
