$(function() {
	$("#submitsg").submit(function() {
		$("#resultsg").html('Processing...');
		var url = $("#urlsg").val();
		$.ajax({
			url : "/v1/lnkProcessing?url=" + url,
			success : function(result) {
				$("#resultsg").html("Successful!!");
			},
			error : function() {
				$("#resultsg").html("Failed!!");
			}
		});

		return false;
	});

});
