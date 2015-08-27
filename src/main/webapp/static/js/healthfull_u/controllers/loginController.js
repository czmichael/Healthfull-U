healthfullUApp.controller('LoginCtrl', 
function($scope, $http) {



	$scope.login = function (username, password) {

		//alert("hi");
		alert("username: " + $scope.username + "\n password: " + $scope.password);
		
	


		var request = $http({
        	method: "post",
            url: "/api/authenticate",
            data: {
                username: username,
                password: password
            }
        });

		
		request.success(
	        function( html ) {
	            $scope.cfdump = html;
	        }
	    );



	};
	
});