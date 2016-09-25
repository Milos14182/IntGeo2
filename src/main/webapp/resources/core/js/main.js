function removeUserImage() {
	$("input:file").val("");
}

function deleteUserImage(userId) {
	$.ajax({
		type : "GET",
		url : contextPath + '/admin/user/deleteUserImage/' + userId,
		success : function(data) {
			$("div").remove(".userImageCreate");
		}
	});
}

function acceptNode(contextPath, string, id) {
	$.ajax({
		type : "GET",
		url : contextPath + '/admin/accept/' + string + '/' + id,
		success : function() {
			$("#" + string + id).remove();
		}
	});
}

function rejectNode(contextPath, string, id) {
	$.ajax({
		type : "GET",
		url : contextPath + '/admin/reject/' + string + '/' + id,
		success : function() {
			$("#" + string + id).remove();
		}
	});
}