healthfullUApp.controller('LoginCtrl', 
function($scope, $http, $location, authenticationService) {

	$scope.login = function() {
		var loginService = authenticationService.login($scope.username, $scope.password);

		loginService.then(
			function(userInfo) {
		    	$location.path('/home');
		  	}, function() {
		    	$scope.error = "Invalid user or password";
		  	}
		);
	};
});