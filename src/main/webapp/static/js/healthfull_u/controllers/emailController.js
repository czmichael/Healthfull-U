healthfullUApp.controller('EmailCtrl', 
function($scope, $http) {

	$scope.email = {
		to: '',
		subject: '',
		body: ''
	};

	$scope.sendEmail = function () {

		
		if ($scope.composeForm.$valid) {
			$http.post(emailCreateUrl, $scope.email)
				.success(function(data, status, headers, config) {
		  			alert("success, data: " + data + " status: " + status);
		  		})
		  		.error(function(data, status, headers, config) {
		    		alert("fail, data: " + data + "  status: " + status);
		  		});		
		}
	
	};
});