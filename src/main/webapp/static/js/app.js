'use strict';

var healthfullUApp = angular.module('healthfullUApp', []);

var userUrl = 'http://localhost:8080/rest/json/user/get';
var foodEntryUrl = 'http://localhost:8080/rest/json/food_entry/get'

healthfullUApp.controller('UserCtrl', function($scope, $http) {

	$http.get(userUrl).success(function(data) {
		$scope.users = data;
	});


	$http.get(foodEntryUrl).success(function(data) {
		$scope.foodEntries = data;
		
		
		$scope.dailyTotal = getDailyTotal($scope.foodEntries);
		
	});
});


function getDailyTotal(foodEntries) {
	var totalCalories = 0;
	for (var i = 0; i < foodEntries.length; i++) {	
		totalCalories = totalCalories + foodEntries[i].calories;
	}
	return totalCalories;
}