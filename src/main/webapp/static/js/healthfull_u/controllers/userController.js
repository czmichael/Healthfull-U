healthfullUApp.controller('UserCtrl', function($scope, $http) {

	$http.get(userUrl).success(function(data) {
		$scope.users = data;
	});
});