<%@page import="com.ball.bean.BallType"%>
<%@page import="com.ball.bean.SellerInfo"%>
<%@page import="com.ball.bean.BallGround"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
BallGround ballGround = (BallGround)request.getSession().getAttribute("ballGround");
SellerInfo sellerInfo = (SellerInfo)request.getSession().getAttribute("sellerInfo");
if(null==ballGround||null==sellerInfo){
	response.sendRedirect("SellerLogin.jsp");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>资质信息</title>
    
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
		td:first-child{text-align:right;}
		
				#save {
   		width:100px; height:30px;
   		border: 1px solid #dedede;
   		-moz-border-radius: 5px;      /* Gecko browsers */
   		-webkit-border-radius: 5px;   /* Webkit browsers */
   		border-radius:5px;        /* W3C syntax */
   		background: #FF7F50;
   		color: white;
	}
		
		#save:HOVER {
	width:100px; height:30px;
   		border: 1px solid #dedede;
   		-moz-border-radius: 5px;      /* Gecko browsers */
   		-webkit-border-radius: 5px;   /* Webkit browsers */
   		border-radius:5px;        /* W3C syntax */
   		background: #FF7256;
   		color: white;
	}
</style>
	
	<script type="text/javascript">
	var num = 1;
		function Modify(sourceId){
			document.getElementById(sourceId).style.visibility="hidden";
			document.getElementById(sourceId).parentNode.firstChild.style.display="none";
			document.getElementById(sourceId).previousElementSibling.style.display="";
			document.getElementById("save").style.display = "";
		}
		
		
		function checkPic(picPath){
			var type = picPath.substring(picPath.lastIndexOf(".")+1,picPath.length).toLowerCase();
			if(type != "jpg" && type != "bmp" && type != "gif" && type != "png"){
				alert("请您选择正确的图片文件");
				return false;
			}
			return true;
		}
		
		document.onkeydown = function (e) {
            var ev = window.event || e;
            var code = ev.keyCode || ev.which;
            if (code == 116) {
                ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
                cancelBubble = true;
                return false;
            }
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
	</script>

  </head>
  
  <body>
    <div style="margin: 0;position: relative;">
    	<div style="background: #4682B4;height: 70px; margin: 0px;width: 100%">
    		<img alt="WebTitle" src="WebImage/WebTitle_Sell.png" style="margin-left: 150px">
    		<img src = "WebImage/mail.png" style="width: 34px;height: 28px;margin-left: 900px"  />
    	</div>
    	<div style="height: 500px;width: 1500px;margin-top: 50px">
    		<h2 style="margin-left: 100px">联系人信息</h2>
    		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-left: 100px;" />
    		<table style="margin-left: 150px;margin-top: 20px;" cellpadding="10px" border="2">
    			<tr>
    				<td>商家负责人姓名</td>
    				<td style="width: 500px"><%=sellerInfo.getSellerName() %></td>
    			</tr>
    			<tr>
    				<td>商家负责人手机</td>
    				<td style="width: 500px"><%=sellerInfo.getSellerPhone() %></td>
    			</tr>
    			<tr>
    				<td>商家负责人邮箱</td>
    				<td style="width: 500px"><%=sellerInfo.getSellerMail() %></td>
    			</tr>
    		</table>
    		<h2 style="margin-left: 100px">球场信息</h2>
    		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-left: 100px;" />
    	<form action="ModifyGroundInfoServlet?s_id=<%=sellerInfo.getSeller().getSeller_id()%>"  method="post"  enctype="multipart/form-data">
    		<table style="margin-left: 150px;margin-top: 20px;" cellpadding="10px" border="2" >
    			<tr>
    				<td>球场类型</td>
    				<td style="width: 500px" ><%=BallType.BallTypeString(ballGround.getBallType()) %></td>
    			</tr>
    			<tr>
    				<td>球场数量</td>
    				<td style="width: 500px"><span><%=ballGround.getGroundNum() %></span>
    				<input  id="GroundNum" name="GroundNum" type="text" value="<%=ballGround.getGroundNum() %>" style="display: none;"  />
    				<input style="float: right;" id="Mod1" type="button" value="修改信息" onclick="Modify(this.id)" />
    				</td>
    			</tr>
    			<tr>
    				<td>球场剩余数量</td>
    				<td style="width: 500px"><span><%=ballGround.getGroundLeft() %></span>
    				<input  id="GroundLeft" name="GroundLeft" type="text" value="<%=ballGround.getGroundLeft() %>" style="display: none;"  />
    				<input style="float: right;" id="Mod2" type="button" value="修改信息" onclick="Modify(this.id)" /></td>
    			</tr>
    			<tr>
    				<td>球场价格</td>
    				<td style="width: 500px"><span><%=ballGround.getGroundPrice() %></span>
    				<input  id="GroundPrice" name="GroundPrice" type="text" value="<%=ballGround.getGroundPrice() %>" style="display: none;"  />
    				<input style="float: right;" id="Mod3" type="button" value="修改信息" onclick="Modify(this.id)" /></td>
    			</tr>
    			<tr>
    				<td>球场联系电话</td>
    				<td style="width: 500px"><span><%=ballGround.getGroundPhone() %></span>
    				<input  id="GroundPhone" name="GroundPhone" type="text" value="<%=ballGround.getGroundPhone() %>" style="display: none;"  />
					<input style="float: right;" id="Mod4" type="button" value="修改信息" onclick="Modify(this.id)" /></td>
    			</tr>
    			<tr>
    				<td>球场介绍</td>
    				<td style="width: 500px"><span><%=ballGround.getGroundInfo() %></span>
    				<textarea id="GroundInfo" rows="" cols=""  name="GroundInfo"   style="width: 450px;height: 100px;font-size: 16px;display: none;"><%=ballGround.getGroundInfo() %></textarea>
    				<input style="float: right;" id="Mod5" type="button" value="修改信息" onclick="Modify(this.id)" /></td>
    			</tr>
    			
    			<tr>
    				<td>门头及环境图</td>
    				<td style="width: 900px"><span><%if((ballGround.getBallGroundPic1Path()==null||"".equals(ballGround.getBallGroundPic1Path()))&&
    											(ballGround.getBallGroundPic2Path()==null||"".equals(ballGround.getBallGroundPic2Path()))&&
    											(ballGround.getBallGroundPic3Path()==null||"".equals(ballGround.getBallGroundPic3Path()))){ %> 
    											未上传图片
    										<%}else{ %>
    											<%if(ballGround.getBallGroundPic1Path()!=null||!"".equals(ballGround.getBallGroundPic1Path())){ %>
    												<img alt="pic1" src="<%=ballGround.getBallGroundPic1Path()%>" style="width: 260px;height: 200px">
    											<%} %>
    											<%if(ballGround.getBallGroundPic2Path()!=null||!"".equals(ballGround.getBallGroundPic2Path())){ %>
    												<img alt="pic2" src="<%=ballGround.getBallGroundPic2Path()%>" style="width: 260px;height: 200px">
    											<%} %>
    											<%if(ballGround.getBallGroundPic3Path()!=null||!"".equals(ballGround.getBallGroundPic3Path())){ %>
    												<img alt="pic3" src="<%=ballGround.getBallGroundPic3Path()%>" style="width: 260px;height: 200px">
    											<%} %>
    										<%} %>
    										</span>
    										<span style="display: none;">
    										<img alt="" src="WebImage/preview.png" width="150px" height="125px" id="pre1" />
  						<img alt="" src="WebImage/preview.png" width="150px" height="125px" id="pre2" style="display: none;" />
  						<img alt="" src="WebImage/preview.png" width="150px" height="125px" id="pre3" style="display: none;" /><br>
  						<input type="file" name="pic1" id="pic1"  style="width:68px;margin-left: 35px" onchange="preImg(this.id, 'pre1')" />
  						<input type="file" name="pic2" id="pic2" size="10" style="display: none;width: 68px;margin-left: 85px"  onchange="preImg(this.id, 'pre2')"  />
  						<input type="file" name="pic3" id="pic3"  size="10"style="display: none;width: 68px;margin-left: 85px"  onchange="preImg(this.id, 'pre3')" />
  						<input type="button" id="uploadpic" value="增加上传图片" onclick="AddPic()" style="margin-left: 50px"/>
    										
    										</span>
    										<input style="float: right;" id="Mod6" type="button" value="修改信息" onclick="Modify(this.id)" />
    										</td>
    			</tr>
    			<tr>
    				<td></td>
    				<td>
    				<input id="save" style="display: none;" type="submit" value="保存"  /></td>
    			</tr>
    			
    			<tr>
					<td></td>
    				<td>
    					<%if(ballGround.getIsBusiness()==0){ %>
    					<input type = "hidden" name="business" value="1"  />
    					<input type="submit" value="开始营业"  />
    					<%}else{ %>
    					<input type = "hidden" name="business" value="0"  />
    					<input type="submit" value="暂停营业"  />
    					<%} %>
    				</td>
    			</tr>
    		</table>
    		</form>
    		<h2 style="margin-left: 100px">资质信息</h2>
    		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-left: 100px;" />
    		<table style="margin-left: 150px;margin-top: 20px;" cellpadding="10px" border="2" >
    			<tr>
    				<td>营业执照注册号</td>
    				<td style="width: 500px"><%=sellerInfo.getLicenceNum() %></td>
    			</tr>
    			<tr>
    				<td>营业执照名称</td>
    				<td style="width: 500px"><%=sellerInfo.getLicenceName()%></td>
    			</tr>
    			<tr>
    				<td>营业执照有效期</td>
    				<td style="width: 500px"><%if(null==sellerInfo.getLicenceDate()){out.println("");} %></td>
    			</tr>
    			
    			<tr>
    				<td>营业执照照片</td>
    				<td style="width: 500px"></td>
    			</tr>
    			
    			<tr>
    				<td>身份证号码</td>
    				<td style="width: 500px"><%=sellerInfo.getIdCard() %></td>
    			</tr>
    			
    			<tr>
    				<td>身份证照片</td>
    				<td style="width: 500px"><%if(sellerInfo.getIdCardPicPath()==null||"".equals(sellerInfo.getIdCardPicPath())){ %> 
    											未上传图片
    										<%}else{%>
    											<img alt="身份证图片"  src="<%=sellerInfo.getIdCardPicPath() %>" style="width: 350px;height: 200">
    										<%} %>
    				</td>
    			</tr>
    			<tr>
    				<td>门店名称</td>
    				<td style="width: 500px"><%=ballGround.getGroundName() %></td>
    			</tr>
    			<tr>
    				<td>门店地址</td>
    				<td style="width: 500px"></td>
    			</tr>
    			<tr>
    				<td>所在地区</td>
    				<td style="width: 500px"><%=ballGround.getCity() %></td>
    			</tr>
    		</table>
    	<div style="height: 90px;">
    	</div>
    	</div>
    </div>
  </body>
</html>
