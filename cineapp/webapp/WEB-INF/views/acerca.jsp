<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<meta name="description" content="">
	<meta name="author" content="">	
	<title>CineSite | Bienvenido</title>
	<spring:url value="/resources" var="urlPublic" />
		
	<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">	
	<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	
	</head>

<body>

	<jsp:include page="includes/menu.jsp"></jsp:include>
	
	<div class="container theme-showcase" role="main">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">ACERCA DE ESTA APLICACION</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-3">
						<p class="text-center">
							<img class="img-rounded" src="${urlPublic}/images/acerca.png" alt="Generic placeholder image" height="220">
						</p>
					</div>
					<div class="col-sm-9">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Spring MVC y Spring Data JPA - Desarrollo Web con Spring 5</h3>
							</div>
							<div class="panel-body">
								
								<div class="alert alert-danger" role="alert">
								
								<p>ESTA APLICACION DE UN CATALOGO DE PELICULAS FUE DESARROLLADA UTILIZANDO EL IDE ECLIPSE SPRING STS 3 </p><br>
								<p>Spring MVC 5, Spring Data JPA 2.2.3 y Spring Security 5</p><br>
								<p>Base de datos MySQL </p><br>
								ES PARA PODER VER LA FUNCIONALIDAD COMPLETA DE UNA APLICACION DESARROLLADA CON ESTE FRAMEWORKS,
								QUE ES CAPAS DE CREAR, ACTUALIZAR Y ELIMINAR REGISTROS.</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="includes/footer.jsp"></jsp:include>		

	</div> <!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>