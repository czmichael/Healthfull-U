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




		

		// $http.post(loginUrl, {username: $scope.username, password: $scope.password}).
		// 	then(function(response) {
	 //    		console.log(JSON.stringify(response));
	 //    		sessionStorage.token = response.data.token;
	 //    		$location.path('/home')
	 //  		}, function(response) {
	 //  			console.log(JSON.stringify(response));
	 //    		// Erase the token if the user fails to log in
		// 		sessionStorage.removeItem("token");
	 //    		// Handle login errors here
	 //    		$scope.error = "Invalid user or password";
  //               $scope.dataLoading = false;
	    		
	 //  		});


	};





	
});