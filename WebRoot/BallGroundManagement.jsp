<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = "";
if(request.getAttribute("s_id")!=null){
	int s_id = (Integer)request.getAttribute("s_id");
	id = "s"+s_id;
}
if(request.getAttribute("a_id")!=null){
	int a_id = (Integer)request.getAttribute("a_id");
	id = "a"+a_id;
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>球场管理</title>
    
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
		span {
		font-size: 10px;
		margin-left: 10px;
	}
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
		
		td:first-child{text-align:right}
	</style>
	
	<script type="text/javascript">
	var num = 1;
	var isBallType = false;
	var isGroundName = false;
	var isGroundNum = false;
	var isGroundPrice = false;
	var isGroundPhone = false;
	var isPlace = false;
		function AddPic(){
			num = num+1;
			var picid = "pic"+num;
			var preid = "pre"+num;
			document.getElementById(picid).style.display="";
			document.getElementById(preid).style.display="";
			if(num==3){
				document.getElementById("uploadpic").style.display="none";
			}
		}
		
		function toVaild(){
			var lng = document.getElementById("Map").contentDocument.getElementById("lng").value;
			var lat = document.getElementById("Map").contentDocument.getElementById("lat").value;
			var city = document.getElementById("Map").contentDocument.getElementById("city").value;
			var address = document.getElementById("Map").contentDocument.getElementById("address").value;
			document.getElementById("lng").value = lng;
			document.getElementById("lat").value = lat;
			document.getElementById("city").value = city;
			document.getElementById("address").value = address;
			var button = document.getElementsByName("BallType");
			for(var i=0;i<button.length;i++) {
				if(button[i].checked==true) {
					isBallType = true;
				}
			}
			if(lng==""||lat==""){
				isPlace = false;
			}else if(lng!=""&&lat!=""){
				isPlace = true;
			}
			if(isGroundName&&isBallType&&isGroundNum&&isGroundPrice&&isGroundPrice&&isPlace){
				return true;
			}else{
				alert("信息填写不完整或者有误，请重新填写");
				return false;
			}
				return true;
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
        		return;  
    		} 
    	var reader = new FileReader();  
    	reader.onload = function(e) {  
        	var img = document.getElementById(targetId);  
        	img.src = this.result;  
    	};

    	reader.readAsDataURL(document.getElementById(sourceId).files[0]); 
		}
	}
	
	function hind(sourceId){
		var span = document.getElementById(sourceId).parentNode.lastChild;
		var source = document.getElementById(sourceId).value;
		var phone = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
		var num = /^[0-9]*$/;
		switch(sourceId){
			case "GroundName":
				if(source.length==0){
					isGroundName = false;
					span.innerHTML = "球场名称不能为空";
				}else{
					isGroundName = true;
					span.innerHTML = "";
				}
			break;
			case "GroundInfo":
				span.innerHTML = "";
			break;
			case "GroundPhone":
				if(source.length==0){
					isGroundPhone = false;
					span.innerHTML = "联系电话不能为空";
				}else if((source.length==11||source.length==12)&&phone.test(source)){
					isGroundPhone = true;
					span.innerHTML = "";
				}else{
					isGroundPhone = false;
					span.innerHTML = "联系电话格式有误";
				}
			break;
			case "GroundNum":
				if(source.length==0){
					isGroundNum = false;
					span.innerHTML = "场地数不能为空";
				}else if(num.test(source)){
					isGroundNum = true;
					span.innerHTML = "";
				}else{
					isGroundNum = false;
				}
			break;
			
			case "GroundPrice":
				var button = document.getElementsByName("isFree");
				if(button[0].checked==true){
					source = "";
					isGroundPrice = true;
				}else{
					if(source.length==0){
						isGroundPrice = false;
						alert("不填入价格，此场地价格为免费");
					}else if(num.test(source)){
						isGroundPrice = true;
					}
				}
				
			break;
		}
	}
	function hint(sourceId){
		var span = document.getElementById(sourceId).parentNode.lastChild;	
		switch(sourceId){
			case "GroundName":
				span.innerHTML = "请填写球场名称";
			break;
			case "GroundInfo":
				span.innerHTML = "请介绍下球场的简要信息";
			break;
			case "GroundPhone":
				span.innerHTML = "请输入球场的联系电话（例如：0716-8513840）或者联系手机";
			break;
		}
	}
	
	function free(){
		document.getElementById("GroundPrice").disabled = true;
		document.getElementById("GroundPrice").value = "";
	}
	
	function pay(){
		document.getElementById("GroundPrice").disabled = false;
	}
	
	</script>
  </head>
  
  <body>
  	<div style="margin: 0;position: relative;">
  		<div style="background: #4682B4;height: 70px; margin: 0px;width: 100%">
  			<img alt="WebTitle" src="WebImage/WebTitle_Sell.png" style="margin-left: 150px">
  		</div>
  		<form action="BallGroundManagementServlet?id=<%=id%>"  method="post"  enctype="multipart/form-data"  onsubmit= "return toVaild()" >
  			<div style="height: 500px;width: 1200px;margin-top: 50px">
  			<h2 style="margin-left: 100px">球场信息</h2>
  			<hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-left: 100px;" />
  			<table style="margin-left: 150px;margin-top: 20px;" cellpadding="10px">
  				<tr>
  					<td>球场名称</td>
  					<td colspan="3"><input id="GroundName" name="GroundName" type="text" style="width: 515px;height: 30px;font-size: 16px" onfocus="hint(this.id)" onblur="hind(this.id)" /><span ></span></td>
  				</tr>
  				<tr>
  					<td>球场类型</td>
  					<td colspan="3">
  						<input type="radio" name="BallType" value="1"  />足球
  						<input type="radio" name="BallType" value="2"  />篮球
  						<input type="radio" name="BallType" value="7"  />排球
  						<input type="radio" name="BallType" value="3"  />乒乓球
  						<input type="radio" name="BallType" value="4"  />台球
  						<input type="radio" name="BallType" value="5"  />羽毛球
  						<input type="radio" name="BallType" value="6"  />网球
  						<span></span>
  					</td>
  				</tr>
  				<tr >
  					<td>场地数量</td>
  					<td><input type="text" id="GroundNum" name="GroundNum" style="width: 100px;" onfocus="hint(this.id)" onblur="hind(this.id)" /> 个	<span></span></td>
  					<td>场地价格</td>
  					<td><input type="text" id="GroundPrice" name="GroundPrice" style="width: 100px" onfocus="hint(this.id)" onblur="hind(this.id)" /> 元/时	 
						<input type="radio" name="isFree" value = "0"  onclick="free()" /> 免费
						<input type="radio" name="isFree" value = "1" checked="checked" onclick="pay()" /> 付费
					</td>
  				</tr>
  				<tr>
  					<td>球场电话</td>
  					<td colspan="3"><input id="GroundPhone" name="GroundPhone" type="text" style="width: 200px;" onfocus="hint(this.id)" onblur="hind(this.id)" /><span></span></td>
  				</tr>
  				<tr>
  					<td>场地介绍</td>
  					<td colspan="3"><textarea id="GroundInfo" rows="" cols=""  name="GroundInfo" style="width: 450px;height: 100px;font-size: 16px;" onfocus="hint(this.id)" onblur="hind(this.id)" ></textarea><span></span></td>
  				</tr>
  				<tr>
  					<td>场地位置</td>
  					<td><iframe id="Map" name="Map" src="BaiduMap.html" style="width: 300px;height: 300px" ></iframe>
  						<input type="hidden" value="" id="lng" name="lng" />
  						<input type="hidden" value="" id="lat" name="lat" />
  						<input type="hidden" value="" id="city" name="city" />
  						<input type="hidden" value = "" id = "address" name = "address" />
  					</td>
  				</tr>
  				<tr>
  					<td>
  						球场图片
  					</td>
  					<td colspan="3">
  						<img alt="" src="WebImage/preview.png" width="150px" height="125px" id="pre1" />
  						<img alt="" src="WebImage/preview.png" width="150px" height="125px" id="pre2" style="display: none;" />
  						<img alt="" src="WebImage/preview.png" width="150px" height="125px" id="pre3" style="display: none;" /><br>
  						<input type="file" name="pic1" id="pic1"  style="width:68px;margin-left: 35px" onchange="preImg(this.id, 'pre1')" />
  						<input type="file" name="pic2" id="pic2" size="10" style="display: none;width: 68px;margin-left: 85px"  onchange="preImg(this.id, 'pre2')"  />
  						<input type="file" name="pic3" id="pic3"  size="10"style="display: none;width: 68px;margin-left: 85px"  onchange="preImg(this.id, 'pre3')" />
  						<input type="button" id="uploadpic" value="增加上传图片" onclick="AddPic()" style="margin-left: 50px"/>
  					</td>
  				</tr>

  				<tr>
  					<td></td>
  					<td><input id="round" type="submit" value="提交" /></td>
  				</tr>
  			</table>
  			</div>
  		</form>
  	
  	</div>
     
  </body>
</html>
