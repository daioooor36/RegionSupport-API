<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/css/bootstrap.css">
<title>SCVRead</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<%@ include file="include/header.jsp" %>
<%@ include file="include/menu.jsp" %>

<!--
<c:forEach var="region" items="${regionList}">
{"region":"${region.region_nm}",<br>
"target":"${region.target}",<br>
"usage":"${region.usage}",<br>
"limit":"${region.limit}",<br>
"rate":"${region.rate}",<br>
"institute":"${region.institute}",<br>
"mgmt":"${region.mgmt}",<br>
"reception":"${region.reception}"},<br><br>
</c:forEach>
-->

<script type="text/javascript">
   $.ajax({
		type : "GET",
		url : "${path}/list_raw_json.do",
		success : function(data){
			var str="<div class='container'>";
			str+="<table class='table' style='text-align:center; border: 1px solid #dddddd'>";
			str+="<thead>";
			str+="<tr><th style='background-color: #fafafa; text-align: center;'>결과</th></tr>";
			str+="</thead>";
			str+="<tbody>";			
			
			$(data.list).each(function(i,value){
				str+='<tr>';
				str+='<td align="left">';
				str+='{"region":"'+data.list[i].region_nm+'",<br>';
				str+='"target":"'+data.list[i].target+'",<br>';
				str+='"usage":"'+data.list[i].usage+'",<br>';
				str+='"limit":"'+data.list[i].limit+'",<br>';
				str+='"rate":"'+data.list[i].rate+'",<br>';
				str+='"institute":"'+data.list[i].institute+'",<br>';
				str+='"mgmt":"'+data.list[i].mgmt+'",<br>';
				str+='"reception":"'+data.list[i].reception+'"}<br>';
				str+='</td>';
				str+='</tr>';
			});
			str+="</tbody></table></div>";
			jQuery("#showData").html(str);
		}
	});
</script>
</head>
<body>
	<div id="showData">	
	</div>
</body>
</html>