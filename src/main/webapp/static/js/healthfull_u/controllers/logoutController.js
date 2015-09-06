healthfullUApp.controller('LogoutCtrl', 
function($scope, $http, $location, authenticationService) {
	
	var logoutService = authenticationService.logout();

	logoutService.then(
		function() {
	    	$location.path('/login');
	  	}, function() {
	    	$scope.error = "log out failed.";
	  	}
	);
});