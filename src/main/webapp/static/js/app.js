'use strict';

var healthfullUApp = angular.module('healthfullUApp', []);

var dev_url = 'http://localhost:8080/rest/json/user/get';

healthfullUApp.controller('UserCtrl', function($scope, $http) {

	$http.get(dev_url).success(function(data) {
		$scope.users = data;
	});

	$scope.name = 'World 1';
});