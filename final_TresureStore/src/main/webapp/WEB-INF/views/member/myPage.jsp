<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/tresure/resources/css/common/home.css">
<link rel="stylesheet" href="/tresure/resources/css/mypage/mypageMain.css">
<link rel="stylesheet" href="/tresure/resources/css/common/font.css">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
 <script type="text/javascript" src="/tresure/resources/js/header.js"></script>
 <script type="text/javascript" src="/tresure/resources/js/mypageMain.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    
  <style>
  

     a:hover{
        text-decoration: none !important;
     }
     .sun_wrap_li > ul img{
        margin-top: 5px !important;
     }
     
     .catebox3{
      width: 1000px !important;
   }

   .inner-menu{
      width: 700px !important;
   }
   
   .catebox.cate1, .catebox.cate2, .catebox.cate3, .catebox.cate4{
      margin-right: 40px;
   }
   
   .catebox.cate7, .catebox.cate9, .catebox.cate10{
      margin-left: 30px;
   }
   
   .catebox.cate8{
      margin-left: 50px;
   }
  
  
     .market-open img{
        margin-top: 5px;
     }
     
     .search2{
        margin-top: 0px !important;
     }
     
     .nfavorites{
           padding: 5px 0px !important;
            padding-top: 10px !important;
            height: 80px !important;
     }
     
     .ntheTop{
        height: 40px !important;
     }


  </style>

</head>
<body>
   <jsp:include page="../common/header.jsp"/>
   <jsp:include page="../common/sideBar.jsp"/>
        
   <div class="main-section">
        
    <div class="content2">

        <div class="profile">
            
<!-- ?????? ?????? ????????? ????????? ?????? -->            
            <div class="profile-image">
           <c:if test="${reviewAvg > 4.5}"> 
                                 <img src="/tresure/resources/images/icon/backGray_grade3.png" height="100%" width="100%"/>
                              </c:if> 
                              <c:if test="${ 4 <= reviewAvg && reviewAvg < 4.5 }"> 
                                 <img src="/tresure/resources/images/icon/backGray_grade2.png" height="100%" width="100%"/> 
                              </c:if> 
                              <c:if test="${ 3.5 <= reviewAvg && reviewAvg < 4 }"> 
                                 <img src="/tresure/resources/images/icon/backGray_grade1.png" height="100%" width="100%"/> 
                              </c:if>
                              <c:if test="${ reviewAvg == null  || reviewAvg < 3.5 }">
                                 <img src="/tresure/resources/images/icon/backGray_grade0.png" height="100%" width="100%"/>
                              </c:if>  


            </div>
            <br>
            <a class="market-grade" data-toggle="modal" data-target="#myModal">???????????? ??????</a>
				<div class="modal fade" id="myModal" data-backdrop="static"
					data-keyboard="false">
					<div class="modal-dialog modal-xl modal-dialog-centered">

						<div class="modal-content"
							style="width: 700px; height: 580px; margin: auto;">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">?????? ??????</h4>
								<button type="button" class="close" data-dismiss="modal">
									<img src="/tresure/resources/images/icon/x-modalImage.png"
										width="35px" height="30px" style="margin-top: 5px;">
								</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body"
								style="position: relative; flex: 1 1 auto; padding: 2rem;">
								<img class="card-img-top rounded img-fluid"
									src="/tresure/resources/images/icon/??????1.png">
							</div>

						</div>
					</div>
				</div>

			</div>

<!-- ????????? & ???????????? & ???????????? & ??????????????? & ??????????????? ?????? ?????? -->
        <div class="info">
            <div class="info-table">
                <div class="market-name">?????? ${loginUser.userNo} ??????</div>
                <div class="info-list">
                    <div class="market-open">
                        <img src="/tresure/resources/images/icon/????????????.png" width="20" height="15" alt="??????????????? ?????????" style="margin-top: 5px;">
                        &nbsp;???????????????<div class="market-opendate"><span>${marketOpen }</span>??????</div>
                    </div>

                    <div class="follower">
                        <img src="/tresure/resources/images/icon/?????????.png" width="20" height="15" alt="????????? ?????????" style="margin-top: 5px;">
                        &nbsp;?????????<div class="market-follower"><span>${followCount }</span> ???</div>
                    </div>

                    <div class="sell-product">
                        <img src="/tresure/resources/images/icon/?????????.png" width="20" height="15" alt="???????????? ?????????" style="margin-top: 5px;"> 
                        &nbsp;????????????<div class="market-sell"> <span>${sellCount }</span> ???</div>
                    </div>

                    <div class="report">
                        <img src="/tresure/resources/images/icon/?????????.png" width="20" height="15" alt="?????? ?????????" style="margin-top: 5px;">
                        &nbsp;??????<div class="market-report"> <span>${reportCount }</span>???</div>
                    </div>
                    <br><br>
                </div>
                <br><br><br>

                <div class="button-area1">
                    <a href="${pageContext.request.contextPath }/follow/followList" class= "following-list">&nbsp;&nbsp;&nbsp;&nbsp;????????? ??????</a>        
                </div>
                
                <div class="button-area2">
                   <br><br>

                    <a class="Withdrawal" href="${pageContext.request.contextPath }/delete">????????????</a>

                </div>
            </div>
        </div>
    </div> 
    
    
    <br><br>
    
<!-- ?????? & ???????????? & ????????? & ???????????? ????????? -->
    <div class="list-form" style="margin: auto;padding-left: 50px;">
    <fieldset id="mForm">
       <div class="list-content">
          <div class="list-a">
             <button class="market-product"  type="button" onclick="show(this);" id="product" name="show"><span class="rproduct">??????</span></button>
            <button class="market-review" type="button" onclick="show(this);" id="review"name="show"><span class="rrview">????????????</span></button>
            <button class="market-heart" type="button"onclick="show(this);" id="heart"name="show"><span class="rheart">???</span></button>
            <button class="market-tracsac" onclick="tracsac();"><span class="rtransac">????????????</span></button>
          </div>
          <br><br>
		</div>
       
       
       

<!-- ?????? ?????? ?????? ???, ????????? ?????? -->
				<div id="productshow" class="box">
					<div class="displayList"
						style="flex-wrap: wrap; display: flex; margin: auto; padding-top: 23px;">
						<c:forEach var="s" items="${sellList}" begin="0"
							end="${fn:length(sellList)}" step="1" varStatus="status">
							<div class="item col-3">
								<div class="item" onclick="sellDetail(${s.sellNo})">
									<div id="itemSolid" class="slist-items">
										<c:if test="${s.imgSrc != null}">
											<c:if test="${s.crawl.equals('Y')}">
												<img src="${s.imgSrc}" width="100%" height="150px;"
													class="rounded float-start" alt="">
											</c:if>
											<c:if test="${s.crawl.equals('N')}">
												<img src="${pageContext.request.contextPath}${s.imgSrc}"
													width="100%" height="150px;" class="rounded float-start"
													alt="">
											</c:if>
											 <c:if test="${s.sellStatus eq 'C' }">
	                              <div class="over-img">
	                      		  </div>
	                      		  <div class="text-c" style="color: white;
								    margin-left: 71px;
								    margin-top: -93px;
								    margin-bottom: 75px;">
	                      		  <h3>????????????</h3>
	                      		  </div>
	                             
	                              </c:if>
										</c:if>
										<div class="price-time">
											<span>&nbsp;${s.sellTitle}</span><br> <br>
											<div class="price-time2">
												<br> &nbsp;<img
													src="/tresure/resources/images/icon/heart.png" width="15px"
													height="15px" style="margin-top: 2px;">&nbsp;${s.heartNum}
												&nbsp;&nbsp;&nbsp;${s.timeago} <br> <span
													style="font-size: 33px; color: black;">${s.price}???</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
       
       
       

<!-- ???????????? ?????? ?????? ???, ????????? ?????? -->       
       <div class="box box2" id="reviewshow" style="display:none; border: 4px solid #ffe8cb; border-radius: 12px;">
       <c:forEach var="r" items="${reviewList}" begin="0" end="${fn:length(reviewList)}" step="1" varStatus="status">
       <div class="review-table">
    <table style="width: 800px;margin-left: 140px;">
    
        <tr>
       
        <td class="review-profile"> 
           <c:if test="${r.ravg> 4.5}"> 
                                 <img src="/tresure/resources/images/icon/grade_3.png" height="100%" width="100%"/>
                              </c:if> 
                              <c:if test="${ 4 <= r.ravg && r.ravg < 4.5 }"> 
                                 <img src="/tresure/resources/images/icon/grade2.png" height="100%" width="100%"/> 
                              </c:if> 
                              <c:if test="${ 3.5 <= r.ravg && r.ravg < 4 }"> 
                                 <img src="/tresure/resources/images/icon/grade1.png" height="100%" width="100%"/> 
                              </c:if>
                              <c:if test="${ r.ravg== null  ||r.ravg < 3.5 }">
                                 <img src="/tresure/resources/images/icon/grade0.png" height="100%" width="100%"/>
                              </c:if>  


           </td>
        
       
        <td><h3>${r.userNo }??????</h3></td>
      
     
        <td>${r.revContent }</td>
     
      
        <td>${r.createDate }</td>
       
       
    </tr>

    </table>

</div>
</c:forEach>
       
       
       
       </div>
       
       
       
       
<!-- ????????? ?????? ?????? ???, ????????? ?????? -->
       <div class="box box3" id="heartshow" style="display:none; padding-left: 30px; padding-top: 20px;">
           <div class="allCheck">
          <input type="checkbox" name="allCheck" id="allCheck" /><label for="allCheck">?????? ??????</label> 
         </div>
         <div class="delBtn">
          <button type="button" class="selectDelete_btn">?????? ??????</button> 
         </div>
         
         <br>
         <br>
         <br>
        
        <c:forEach items="${heartList}" var="h" begin="0" end="${fn:length(heartList)}" step="1" varStatus="status">
        
           <div class="list-box">
               <div class="checkBox">
                 <input type="checkbox" name="chBox" class="chBox" data-heartNum="${h.heartNo}" />
               </div>
              
               <div class="thumb" onclick="sellDetail(${h.sellNo})">
                 <img src="${h.imgSrc}"  width="78px" height="78px" style="margin-top: -15px;"/>
               </div>
               
               <div class="gdsInfo" onclick="sellDetail(${h.sellNo})">
                 <p style="margin-top: 18px;">
                   <span>${h.sellTitle}</span><br><br>
                   <span style="float: left;color: #bdb7b7;font-size: 21px;">${h.createDate }</span>
                   <span style="font-size:30px; float:right; margin-right:100px;">${h.price }???</span><br>
                 </p>
               </div>  
          </div> 
        </c:forEach>
       </div>
   </fieldset>
    </div> 

</div>
<script>
      function sellDetail(sellNo){
         location.href = "${pageContext.request.contextPath}/sell/sellDetail/"+sellNo;
      }
   </script>
   
   <script>
      function tracsac(){
         location.href = "${pageContext.request.contextPath}/member/tracsac";
      }
   </script>
<script>

$("#allCheck").click(function(){
    var chk = $("#allCheck").prop("checked");
    if(chk) {
     $(".chBox").prop("checked", true);
    } else {
     $(".chBox").prop("checked", false);
    }
 })
  </script>
 
 
 
 <script>
$(".chBox").click(function(){
    $("#allCheck").prop("checked", false);
    });
    
    </script>
   
     <script>
     $(".selectDelete_btn").click(function(){
   var confirm_val = true;
   
   if(confirm_val) {
    var checkArr = [];
    
    
    $("input[class='chBox']:checked").each(function(){
     checkArr.push($(this).attr("data-heartNum"));
    })
    
    
    console.log(checkArr);
    
    
    $.ajax({
     url : '${pageContext.request.contextPath}/deleteHeart',
     type : 'post',
     data : { chbox : checkArr },
     success : function(result){
    	if(result==1){
    		Swal.fire({
                icon: 'success',
                title: '?????????????????????.'                  
            });	
    		setTimeout(function() {
            	  location.reload();
          	}, 1500);
    	}
     },
     error:function(){
        console.log("??????")
     }
   
    });
   } 
  });
  
  
  </script>



    
     <jsp:include page="../common/footer.jsp"/>
      
</body>
</html>