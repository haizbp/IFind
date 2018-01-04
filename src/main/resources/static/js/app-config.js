	$(function() {
			$("#getForm").submit(function() {
				$("#resultGet").html('Processing...');
				var key = $("#keyGet").val();
				$.ajax({
					url : "/v1/config/get?key=" + key,
					success : function(result) {
						$("#resultGet").html(JSON.stringify(result.data.back));
					},
					error : function() {
						$("#resultGet").html("Failed!!");
					}
				});
				
				return false;
			});
			
			$("#insertForm").submit(function() {
				$("#resultInt").html('Processing...');
				var key = $("#keyInt").val();
				var value = $("#valueInt").val();
				$.ajax({
					url : "/v1/config?key=" + key + "&value=" + value,
					success : function(result) {
						$("#resultInt").html("Successful!!");
					},
					error : function() {
						$("#resultInt").html("Failed!!");
					}
				});
				
				return false;
			});
		});
