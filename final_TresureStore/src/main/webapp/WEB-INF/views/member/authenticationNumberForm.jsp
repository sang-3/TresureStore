<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증번호 입력</title>
	<link rel="stylesheet" href="/tresure/resources/css/font.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script type="text/javascript" src="/tresure/resources/js/header.js"></script>
	<style>
	body{
	    margin: 0;
	    padding: 0px;
	    box-sizing: border-box;
	    background-color: lightgray;
	}
	.main-area{
	    width: 40%;
	    height: 300px;
	    margin: auto;
	    background-color: rgb(255, 255, 255);
	    box-shadow: -1px 2px 10px -3px black;
        border-radius: 5px;
        margin-bottom : 20px;
    }
    .bottom-margin{
	    margin-bottom: 50px;
	    color : red;
	}
    .number-form{
        position: relative;
        z-index: 2;
        font-family: 'Noto Sans KR', sans-serif;
        background-color: rgb(255, 255, 255);
        display: flex;
        flex-wrap: wrap;
        flex-direction: column; /*수직 정렬*/
        align-items: center;
        justify-content: center;
       


	}
    .int-area{
        width: 400px;
        position: relative;
        margin-top: 60px;
	}
    .int-area:first-child{
        margin-top: 0;
    }
    .int-area .inputType1{
		
        width: 100%;
        padding: 20px 10px 10px;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #999;
        font-size : 18px;
        outline: none;
    }
    .int-area label{
    
        position: absolute;
        left: 10px;
        top: 15px;
        font-size: 18px;
        color:#999;
        transition: all 0.5s ease;
    }
    .int-area label.warning{
        color : red !important;
        animation: warning 0.3s ease;
        animation-iteration-count: 3;
    }
    @keyframes warning {
        0% { transform: translateX(-8px); }
        25% { transform: translateX(8px); }
        50% { transform: translateX(-8px); }
        75% { transform: translateX(8px); }
    }
    
    .int-area input:focus{
        transition:all 0.2s ease-in-out;
        border-bottom: 2px solid rgb(0, 0, 0);
    }
    
    .int-area input:focus + label,
    .int-area input:valid + label{
        top: -2px;
        font-size: 13px;
        color: blue;
        
    }
    .submitButton{
        margin-top: 40px;
        background-color: black;
        border: none;
        color: white;
        width: 200px;
        height: 40px;
        border-radius: 10px;
    }
    .submitButton:hover {
        background-color: rgb(54, 54, 54);
        cursor: pointer;
    }

</style>

</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="main-section">
		<br><br><br><br><br>
	    <div class="main-area">
	    	<br>
	    	<h1 class="bottom-margin" align="center">인증 번호 입력</h1>
			<form class="number-form" action="${pageContext.request.contextPath}/loginJoin/loginStrart" method="post">
	        	<div class="int-area">
                    <input class="inputType1" type="text" id="InputNumber" autocomplete="InputNumber" required>
                    <label for="InputNumber">인증번호 입력</label>
                    <input type="hidden" id="userName" name="userName" value="${userName }" >
	        		<input type="hidden" id="birth" name="birth" value="${birth }" >
	        		<input type="hidden" id="phone" name="phone" value="${phone }" >
	            </div>
    
                <button type="button" class="submitButton">인증 받기</button>
	       </form> 

		</div>
		<br><br><br>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>