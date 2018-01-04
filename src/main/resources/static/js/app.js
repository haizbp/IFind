	$(function() {
			$("#submitsg").submit(function() {
				$("#resultsg").html('Processing...');
				var url = $("#urlsg").val();
				$.ajax({
					url : "/v1/crawl?url=" + url,
					success : function(result) {
						$("#resultsg").html("Successful!!");
					},
					error : function() {
						$("#resultsg").html("Failed!!");
					}
				});
				
				return false;
			});
			
			$("#submitpage").submit(function() {
				$("#resultpage").html('Processing...');
				var url = $("#urlpage").val();
				var from = $("#fromPage").val();
				var to = $("#toPage").val();
				$.ajax({
					url : "/v1/crawl/page?url=" + url + "&from=" + from + "&to=" + to,
					success : function(result) {
						$("#resultpage").html("Successful!!");
					},
					error : function() {
						$("#resultpage").html("Failed!!");
					}
				});
				
				return false;
			});
		});
