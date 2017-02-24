<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>约球吧商家中心注册</title>
    
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
	
	a{
		text-decoration: none;
		color: #4682B4;
	}
	a:HOVER {
		text-decoration: underline;
		color: #4682B4;
	}
	td:first-child{text-align:right}
	
		.unchanged {
		border: 0;
		margin-left: 10px;
		font-size: 15px;
	}
		#round {
   		width:60px; height:30px;
   		border: 1px solid #dedede;
   		-moz-border-radius: 5px;      /* Gecko browsers */
   		-webkit-border-radius: 5px;   /* Webkit browsers */
   		border-radius:5px;        /* W3C syntax */
   		background: #FF7F50;
   		color: white;
	}
	
	#round:HOVER {
	width:60px; height:30px;
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
	var ajax;
	var isAccount = false;
	var isPwd = false;
	var isConPwd = false;
	var isCode = false;
	function createCode(){
		var autoRandom = document.getElementById("autoRandom");
		var seed = new Array(
		'abcdefghijklmnopqrstuvwxyz',  
        'ABCDEFGHIJKLMNOPQRSTUVWXYZ',  
        '0123456789' ); //创建需要的数据数组
        var idx,i;
        var result = '';//返回的结果变量
        
        for(i=0;i<4;i++){
        	idx = Math.floor(Math.random()*3);
        	result += seed[idx].substr(Math.floor(Math.random()*(seed[idx].length)),1);//根据随机数获取数据中一个值  
        }
        autoRandom.value = result;
	}
	
	function toVaild(){
		var pwd = document.getElementById("pwd");
		var conpwd = document.getElementById("confirmpwd");
		var autoRandom = document.getElementById("autoRandom");
		var code = document.getElementById("code");
		
		if(isAccount&&isPwd&&isCode&&isConPwd){
			if(code.value!=autoRandom.value){
				alert("验证码输入错误！");
				code.value="";
				pwd.value="";
				conpwd.value="";
				createCode();
				return false;
			}else{
				return true;
			}
		}else{
			document.getElementById("button_hint").innerHTML = "您的信息尚未填写完整完整";
			return false;
		}
	}
	
	function checkAccount(){
		//创建ajax对象
            var account = document.getElementById("account");
            var content = "account="+account.value;
            var url = "${pageContext.request.contextPath}/AccountCheckServlet";
            var method = "post";
            
            //如果输入的字符串为空
            if(null!=account&&account.value.trim()==""){
            	document.getElementById("account_hint").innerHTML = "账号不能为空";
            }else if(account.value.length<5||account.value.length>20){
            	document.getElementById("account_hint").innerHTML = "账号名应该由5-20位字母、数字和下划线'_'组成";
            }else if(!account.value.match("^([a-z0-9A-Z]+[-|\\.]?)")){
            	document.getElementById("account_hint").innerHTML = "账号名应该由5-20位字母、数字和下划线'_'组成";
            }else{
            ajax=null;
			if (window.XMLHttpRequest)
  			{// code for Firefox, Opera, IE7, etc.
  				ajax=new XMLHttpRequest();
  			}
			else if (window.ActiveXObject)
  			{// code for IE6, IE5
  				ajax=new ActiveXObject("Microsoft.XMLHTTP");
  			}
            if(ajax!=null){
            	if(account.value!=""){
            	ajax.onreadystatechange = state_Change;
            	ajax.open(method,url,true);
            	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            	ajax.send(content);
            	}
            }
            }

	}
	
	function state_Change(){
			if (ajax.readyState==4){
				// 4 = "loaded"
  				if (ajax.status==200){
  				// 200 = "OK"
  					var status = ajax.responseText;
  					if(status=="200"){
  						isAccount = true;
  						document.getElementById("account_hint").innerHTML = "该账号可以注册";
  					}else if(status=="201"){
  						isAccount = false;
  						document.getElementById("account_hint").innerHTML = "该账号已经注册";
  					}
    			}else{
    				alert("Problem retrieving data:" + ajax.statusText);
    			}
 		 	}
	}
	
	function hint(x){
		var span = document.getElementById(x).parentNode.lastChild;
		switch (x) {
		case "account":
			span.innerHTML = "5-20位字符，支持字母、数字及下划线‘_’组合";
			break;
		case "pwd":
			span.innerHTML = "6-16位字符，建议由字母和符号两种以上组合";
			break;
		case "confirmpwd":
			span.innerHTML = "请再次输入密码";
			break;
		case "code":
			span.innerHTML = "请输入验证码";
			break;
		default:
			break;
		}
		
	}
	
	function hind(x){
		var span = document.getElementById(x).parentNode.lastChild;
		switch (x) {
		case "account":
			checkAccount();
			break;
		case "pwd":
		var pwd = document.getElementById("pwd").value;
			if(pwd.length==0){
				span.innerHTML="密码不能为空";
				if(isPwd){
					isPwd = false;
				}
			}else if(pwd.length<6||pwd.length>16){
				span.innerHTML="密码长度只能在6-16之间";
				if(isPwd){
					isPwd = false;
				}
			}else{
				span.innerHTML="";
				isPwd = true;
			}
			break;
		case "confirmpwd":
			var pwd = document.getElementById("pwd").value;
			var conpwd = document.getElementById("confirmpwd").value;
			if(conpwd.length==0){
				span.innerHTML = "密码不能为空";
				if(isConPwd){
					isConPwd = false;
				}
			}else if(pwd!=conpwd){
				span.innerHTML = "两次密码输入不一致， 请重新输入";
				if(isConPwd){
					isConPwd = false;
				}
			}else{
				span.innerHTML = "";
				isConPwd = true;
			}
			
			break;
		case "code":
			var code = document.getElementById("code").value;
			if(code.length==0){
				span.innerHTML = "验证码不能为空";
				if(isCode){
					isCode = false;
				}
			}else{
				span.innerHTML = "";
				isCode = true;
			}
			break;
		default:
			break;
		}
	}
	
	</script>
  </head>
  
  <body onload="createCode()">
    <div style="margin: 0;position: relative;">
    	<div style="background: #4682B4;height: 70px; margin: 0px;width: 100%">
    		<img alt="WebTitle" src="WebImage/WebTitle_Sell.png" style="margin-left: 150px">
    	</div>
    	
    	<div style="border: 1px solid #B5B5B5;height: 500px;width: 1000px;margin: auto auto;margin-top: 50px;">
    	<h2 style="margin-left: 100px">商家注册</h2>
    		<hr style="margin-left: 80px;margin-right: 80px;margin-top: 0px">
    		<form action="SellerRegisterSerlvet" method="post" onsubmit="return toVaild()">
    			<table style="margin-left: 150px;margin-top: 50px"cellpadding="10px">
    				<tr>
    					<td>账号</td>
    					<td><input id="account" type="text" name="Seller_Account" style="width: 260px;height: 30px;" onfocus="hint(this.id)" onblur="hind(this.id)" /><span  id="account_hint"></span></td>
    					
    				</tr>
    				<tr>
    					<td>密码</td>
    					<td><input id="pwd" type="password" name="Seller_Password" style="width: 260px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span></span></td>
    				</tr>
    				<tr>
    					<td>确认密码</td>
    					<td><input id="confirmpwd" type="password" style="width: 260px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span></span></td>
    				</tr>
    				<tr>
 						<td>验证码</td>
 						<td><input id="code" type="text" name="Seller_Code" style="width: 100px;height: 30px" onfocus="hint(this.id)" onblur="hind(this.id)" /><input id="autoRandom" class="unchanged" readonly="readonly" onclick="createCode()" style="width: 70px"/><span></span></td>
 					</tr>
 					
 					<tr>
 						<td></td>
 						<td><input id="round" type="submit" value="注册" /><span id="button_hint"></span></td>
 					</tr>

    			</table>
    		</form>
    	</div>
    </div>
    
    
  </body>
</html>
