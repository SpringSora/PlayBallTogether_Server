<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AdministratorLogin.jsp' starting page</title>
    
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
	
	.unchanged {
		border: 0;
		margin-left: 10px;
		font-size: 15px;
	}
	td:first-child{text-align:right}
	
	</style>
	<script type="text/javascript">
	function toVaild(){
		var account = document.getElementById("account").value;
		var pwd = document.getElementById("pwd").value;
		var autoRandom = document.getElementById("autoRandom");
		var code = document.getElementById("code");
		if(code.value!=autoRandom.value){
			alert("验证码输入错误！");
			createCode();
			return false;
		}else{
			if(account!=null&&account==""){
				alert("请输入正确的账号和密码");
				return false;
			}else if(account==null){
				alert("请输入正确的账号和密码");
				return false;
			}else{
				if(pwd!=null&&pwd==""){
					alert("请输入正确的账号和密码");
					return false;
				}else if(pwd==null){
					alert("请输入正确的账号和密码");
					return false;
				}else{
					return true;
				}
			}
		}
	
	}
	
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
	
	
	
	</script>
  </head>
  
  <body onload="createCode()">
  	<div style="margin: 0;position: relative;">
  		<div style="background: #4682B4;height: 70px; margin: 0px;width: 100%">
    		<img alt="WebTitle" src="/WebImage/WebTitle_admin.png" style="margin-left: 150px">
    	</div>
    	<div style="border: 1px solid #B5B5B5;height: 500px;width: 1000px;relative;margin: auto auto;margin-top: 50px;">
    		<h2 style="margin-left: 100px">管理员登录</h2>
    		<hr style="margin-left: 80px;margin-right: 80px;margin-top: 0px">
    		<form action="www.baidu.com" method="post" onsubmit="return toVaild()" >
    			<table style="margin-left: 150px;margin-top: 50px"cellpadding="10px">
  					<tr>
   						<td>账号</td>
    					<td><input id="account" type="text" name="Seller_Account" style="width: 260px;height: 30px;" /></td>
 					</tr>
 					<tr>
 						<td>密码</td>
 						<td><input id="pwd" type="password" name="Seller_Password" style="width: 260px;height: 30px" /></td>
 					</tr>
 					<tr>
 						<td>验证码</td>
 						<td><input id="code" type="text" name="Seller_Code" style="width: 100px;height: 30px"/><input id="autoRandom" class="unchanged" readonly="readonly" onclick="createCode()" /></td>
 					</tr>
 					<tr>
 						<td></td>
 						<td><input id="round" type="submit" value="登录" /></td>
 					</tr>
				</table>
    		</form>
    	</div>
    	
  	</div>
  </body>
</html>
