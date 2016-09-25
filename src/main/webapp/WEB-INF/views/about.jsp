<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">User details<span class="text-muted">See for yourself.</span></h2>
          <p class="lead">Some text</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
        </div>
    </div>
	<div class="row featurette">
      	<div class="col-md-7">
        	<p class="lead">${msg}</p>
      	</div>
    </div>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery.min.js" />" rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />" rel="stylesheet"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="<c:url value="/resources/js/holder.min.js" />" rel="stylesheet"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />" rel="stylesheet"></script>
</body>
</html>