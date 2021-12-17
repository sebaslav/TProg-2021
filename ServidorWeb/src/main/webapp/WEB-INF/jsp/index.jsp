<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp" />
<title>Index</title>
<style>
	body{
		background-image: url("img/gymWallpaper.jpeg"); no-repeat center center fixed; 
	  -webkit-background-size: cover;
	  -moz-background-size: cover;
	  -o-background-size: cover;
	  background-size: cover;
	}
	main{
		background-color: transparent;
		border: 0px;
	}
</style>
</head>
<body>
	<!-- HEADER Y COSTADER -->
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	
	
	<main class="container">
		<section class="container pt-5 pb-5 text-center text-sm-start">
			<!-- 	<section class="py-5 text-center text-sm-start"> -->
<!-- 			<div class="container"> -->
				<div class="d-sm-flex my-5">
					<div class="d-flex flex-column">
						<div class="p-2">
							<h1
								style="font-size:10vw; text-align: lead; color: white; font-weight: bold; text-shadow: 3px 5px 5px #000000;">
								Entrenamos.uy</h1>
						</div>
					</div>
				</div>
<!-- 			</div> -->
		</section>
	</main>
	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>