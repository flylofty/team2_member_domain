<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/common.js" type="module"></script>
<script src="js/macgyver.js" type="module"></script>
<link href="style/header-Footer.css" rel="stylesheet" type="text/css" />
<link href="style/culture-place.css" rel="stylesheet" type="text/css" />
<link href="style/common.css" rel="stylesheet" type="text/css" />
<link href="style/management-page.css" rel="stylesheet" type="text/css" />
<link href="style/culture-gathering.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery.twbsPagination.js"></script>
<!-- jQuery -->
<script src="/js/jquery-3.6.0.min.js"></script>
<!-- Bootstrap -->
<!-- <script src="/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<style>
.center {
  text-align: center;
}
.pagination {
  display: inline-block;
}

.pagination li {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}

.pagination li.active {
  background-color: #F05400;
  color: white;
}
.pagination li:hover:not(.active) {background-color: #ddd;}
</style>

<script type="text/javascript">
	/////onload/////
	$(function(){
		showNotice(1);
	});
	
	function showNotice(num){
		$.ajax(
			{
				type: "get",
				url: "myFlightList.flight",
				dataType: "json",
				data:{
					page : num
				},
				success: function(data){
					const flightList = data.flightList;
					console.log(flightList);
					
					if(flightList.length > 0){
						$('#flightBox').show();
					} else{
						$('#flightBox').hide();
						return;
					}
					
					$('#flightTable').empty();
					
					for(const flight of flightList){
						const name = flight.firstNm + " " + flight.lastNm
						let tempHtml = `<tr>
											<td>\${flight.rsvNo}</td>
											<td>\${flight.rsvCreateDate}</td>
											<td>\${flight.departPlace}</td>
											<td>\${flight.infoDeparTime}</td>
											<td>\${flight.arrivePlace}</td>
											<td>\${flight.infoArriveTime}</td>
											<td>\${name}</td>
											<td>\${flight.birth}</td>
											<td>\${flight.price}</td>
										</tr>`
						$('#flightTable').append(tempHtml);
					}
					
					// ????????? ?????? ??????
					$('#pages').empty();	//????????? ??????
					const pageInfo = data.pageInfo;
					// ?????? ?????????
					const start = Number(pageInfo.start)-1;
					if (pageInfo.prev == 1){
						let tempHtml = `<li onclick = "showNotice(\${start})">&laquo;</li>`
						$('#pages').append(tempHtml);
					}
					// ????????? ?????? ?????????
					for(let i = Number(pageInfo.start); i <= Number(pageInfo.end); i++) {
						let tempHtml = `<li onclick = "showNotice(\${i})">\${i}</li>`;
					if (num == i) {
							tempHtml = `<li class="active" onclick = "showNotice(${i})">\${i}</li>`;
						}
						
						$('#pages').append(tempHtml);
					}
					// ?????? ?????????
					const end = Number(pageInfo.end) + 1;
					if (pageInfo.next == 1){
						let tempHtml = `<li onclick = "showNotice(\${end})">&raquo;</li>`
						$('#pages').append(tempHtml);
					}
					// ????????? ?????? ???
				},
				error:function (request, status, error){
					console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error)
				}
			}
		)		
	}
</script>
</head>
<body>
	<!-- header -->
	<%
	pageContext.include("/WEB-INF/views/include/header.jsp");
	%>

	<!-- main -->
	<div class="culture-container">
		<div class="information-frame">
			<span class="culture-sub-heading">?????? ??????</span>
			<ul class="page-category">
				<a href="${request.getContextPath}myFlight.do">
					<li class="sub-heading">??????</li>
				</a>
				</a>
				<a href="${request.getContextPath}myMeeting.do">
					<li>??????</li>
				</a>
			</ul>
			<table class="culture-table" id = "flightBox">
				<thead>
					<tr>
						<td>????????????</td>
						<td>????????????</td>
						<td>????????????</td>
						<td>????????????</td>
						<td>????????????</td>
						<td>????????????</td>
						<td>????????????</td>
						<td>????????????</td>
						<td>??????</td>
					</tr>
				</thead>
				<tbody id ="flightTable">
					<td>??????</td>
				</tbody>
			</table>
			<ul class="paging-btn" id = "pages">
			</ul>
			
		</div>
		
		
	<div class = "pagination center">
         <ul class="paging-btn" id = "pages"></ul>
    </div>
		
		
	</div>
	<footer>
		<!-- footer -->
		<%
		pageContext.include("/WEB-INF/views/include/footer.jsp");
		%>
	</footer>

</body>
</html>