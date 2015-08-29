healthfullUApp.controller('LoginCtrl', 
function($scope, $http, $location) {

	$scope.login = function() {

		$http.post(loginUrl, {username: $scope.username, password: $scope.password}).
			then(function(response) {
	    		sessionStorage.token = response.data.token;
	    		
	    		$location.path('/home')
	  		}, function(response) {
	    		// Erase the token if the user fails to log in
				sessionStorage.removeItem("token");
	    		// Handle login errors here
	    		$scope.message = 'Error: Invalid user or password';
	    		
	  		});


	};
	
});