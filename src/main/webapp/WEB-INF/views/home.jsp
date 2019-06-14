<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="resources/css/bootstrap.css">
	<title>Region List</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
<%@ include file="include/header.jsp" %>

<c:set var="path" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
function search(){
	var regionNm = $("#regionNm").val();
	regionNm = encodeURIComponent(regionNm);
	
	var parameter = {};
	parameter.region_nm = regionNm;
	
	$.ajax({
		method : "POST",
		url : "regionSearch.do",
		data : JSON.stringify(parameter),
		dataType:"text",
		contentType: "application/json",
		success:function(resultData)
		{			
			showResult(resultData);
		},
		error:function(request,status,error)
		{
			alert("error: "+error);
		}
	});
}

function sortLimit(){
	var cntVal = $("#cnt").val();
		
	var parameter = {};
	parameter.cnt = cntVal;
	
	$.ajax({
		method : "POST",
		url : "limitSort.do",
		dataType : 'text',
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(parameter),
		success : function(result) {
			if(result!='')
				alert(result);
		},
		error:function(request,status,error){
			alert("error: "+error);
		}
	});
}

function sortRate(){
	
	$.ajax({
		method : "GET",
		url : "rateSort.do",
		dataType : 'text',
		contentType: "application/json; charset=utf-8",
		success : function(result) {
			alert(result);
		},
		error:function(request,status,error){
			alert("error: "+error);
		}
	});
}

function update(){
	var regionCd = "RG105";
	var regionNm = "경상남도2";
	var target = "경상남도 소재 중소기업으로서 경상남도지사가 추천한 자";
	var usage = "운전 및 시설2";
	var limit = "7억원 이내";
	var rate = "2.0%";
	var institute = "경상남도";
	var mgmt = "창원지점";
	var reception = "전 영업점점";
		
	var parameter = {};
	parameter.region_cd = regionCd;
	parameter.region_nm = regionNm;
	parameter.target = target;
	parameter.usage = usage;
	parameter.limit = limit;
	parameter.rate = rate;
	parameter.institute = institute;
	parameter.mgmt = mgmt;
	parameter.reception = reception;
	
	$.ajax({
		method : "PUT",
		url : "update.do",
		dataType : 'text',
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(parameter),
		success : function(result) {
			alert('수정 되었습니다.')
			showResult(result);
		},
		error:function(request,status,error){
			alert("error: "+error);
		}
	});
}

function showResult(obj)
{
	var list = JSON.parse(obj);
	var div = document.querySelector('#ajaxTable');
	
	var html = "";
	for(var i=0; i<list.length; i++)
	{
		html +='<tr>';		
		html +='<td>' + list[i].region_nm + '</td>';
		html +='<td>' + list[i].target + '</td>';
		html +='<td>' + list[i].usage + '</td>';
		html +='<td>' + list[i].limit + '</td>';
		html +='<td>' + list[i].rate + '</td>';
		html +='<td>' + list[i].institute + '</td>';
		html +='<td>' + list[i].mgmt + '</td>';
		html +='<td>' + list[i].reception + '</td>';
		html +='</tr>';
	}

	div.innerHTML = html;	
}
</script>
</head>
<body>
<%@ include file="include/menu.jsp" %>

<script type="text/javascript">
if('${message}'!='')
{
	var msg = '${message}';
	alert(msg);
	location.href = '${path}/';
}
</script>

<br>
<div class="container">
	<div class="form-group row float-left ml-1">
		<div class="col-xs-3">
			<input class="form-control" type="number" size="20" name="cnt" id="cnt" placeholder="지원한도 정렬 기관 수">
		</div>
		<div class="col-xs-1">
			<button class="btn btn-primary mr-3" type="button" onclick="sortLimit()">정렬</button>			
		</div>
		<div class="col-xs-2">
			<button class="btn btn-primary mr-3" type="button" onclick="sortRate()">이차보전 최저 기관</button>
		</div>
		<div class="col-xs-4">
			<button class="btn btn-primary" type="button" id="btn_update" onclick="update()">사전설정 값 수정(경상남도)</button>
		</div>
	</div>
		
	<div class="form-group row float-right mr-1">		
		<div class="col-xs-4">
			<input class="form-control" type="text" size="10" name="regionNm" placeholder="찾을 기관 명" id="regionNm" onkeyup="search()">
		</div>
		<div class="col-xs-2">
			<button class="btn btn-warning" type="button" onclick="search()">검색</button>
		</div>
	</div>

	<form id="boardForm" name="boardForm" method="GET">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th width="10%" style="text-align: center;">지자체명</th>
					<th width="35%" style="text-align: center;">지원대상</th>
					<th width="7%"  style="text-align: center;">용도</th>
					<th width="10%" style="text-align: center;">지원한도</th>
					<th width="5%"  style="text-align: center;">이차보전</th>
					<th width="10%" style="text-align: center;">추천기관</th>
					<th width="10%" style="text-align: center;">관리점</th>
					<th width="13%" style="text-align: center;">취급점</th>
				</tr>
			</thead>
			<tbody id="ajaxTable">
				<c:forEach var="result" items="${list}" varStatus="status">
					<tr>
						<td><c:out value="${result.region_nm}"/></td>
						<td><c:out value="${result.target}"/></td>
						<td><c:out value="${result.usage}"/></td>
						<td><c:out value="${result.limit}"/></td>
						<td><c:out value="${result.rate}"/></td>
						<td><c:out value="${result.institute}"/></td>
						<td><c:out value="${result.mgmt}"/></td>
						<td><c:out value="${result.reception}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</form>
</div>

</body>
</html>