<%@ page language="java" contentType="text/html; charset=Windows-1250"
	pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>

<script src="<c:url value="resources/js/greetingsSocket.js" />"></script>
	<div>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
			<br /> <br />
		</div>
		<div id="calculationDiv">
			<label>Number One:</label><input type="text" id="num1" /><br /> <label>Number
				Two:</label><input type="text" id="num2" /><br /> <br />
			<button id="sendNum" onclick="sendNum();">Send to Add</button>
			<p id="calResponse"></p>
		</div>
	</div>
</body>