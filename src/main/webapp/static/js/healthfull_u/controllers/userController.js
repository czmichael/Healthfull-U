healthfullUApp.controller('UserCtrl', 
function($scope, $http) {

	$http.get(userUrl).success(function(data) {
		$scope.user = data[0];
	});
});