'use strict';


var healthfullUApp = angular.module('healthfullUApp', [
	'ngRoute'
]);



healthfullUApp.config(['$routeProvider',
	function($routeProvider) {
    	$routeProvider.
      		when('/home', {
        		templateUrl: 'daily_view.html',
        		controller: 'UserCtrl'
      		}).
      		when('/food_entry_detail', {
        		templateUrl: 'food_entry_detail.html',
        		controller: 'UserCtrl'
      		}).
      		otherwise({
        		redirectTo: '/home'
      		});
  }
]);






var userUrl = 'http://localhost:8080/rest/json/user/get';
var foodEntryUrl = 'http://localhost:8080/rest/json/food_entry/get'
var emailCreateUrl = 'http://localhost:8080/rest/json/email/create'

healthfullUApp.controller('UserCtrl', function($scope, $http) {

	$http.get(userUrl).success(function(data) {
		$scope.users = data;
	});


	$http.get(foodEntryUrl).success(function(data) {
		$scope.foodEntries = data;
		$scope.foodEntryAggregate = aggregateByMealType($scope.foodEntries);
		$scope.dailyTotal = getDailyTotal($scope.foodEntries);
		// alert($scope.foodEntryAggregate.breakfast.length);
		
	});
});



healthfullUApp.controller('FoodCtrl', function($scope, $http) {

	$scope.getFoodInfo = function (date) {
		var requestURL = foodEntryUrl + '?date=' + date;
		$scope.foodEntryDate = date;
		// alert(requestURL);

		$http.get(requestURL).success(function(data) {
		
			$scope.foodEntries = data;
			$scope.foodEntryAggregate = aggregateByMealType($scope.foodEntries);
			$scope.dailyTotal = getDailyTotal($scope.foodEntries);
			// alert($scope.foodEntryAggregate.breakfast.length);
		});
	};
});





healthfullUApp.controller('EmailCtrl', function($scope, $http) {

	$scope.email = {
		to: '',
		subject: '',
		body: ''
	};

	$scope.sendEmail = function () {

    


//		alert($scope.email.body);

		$http.post(emailCreateUrl, $scope.email).
	  		success(function(data, status, headers, config) {
	  			alert("success, data: " + data + " status: " + status);
	  		}).
	  		error(function(data, status, headers, config) {
	    		alert("fail, data: " + data + "  status: " + status);
	  		});
	};
});







/**
 * Aggregate foodEntry by meal types
 * @param {Array} array of FoodEntry to aggregate by meal types
 * @return {Object} an object of foodEntries aggregated by meal types
 */
function aggregateByMealType(foodEntries) {
	var foodEntryAggregate = new Object();
	foodEntryAggregate.breakfast = new Array();
	foodEntryAggregate.lunch = new Array();
	foodEntryAggregate.dinner = new Array();
	foodEntryAggregate.other = new Array();

	for (var i = 0; i < foodEntries.length; i++) {	
		if (foodEntries[i].meal.toLowerCase() == "breakfast") {
			foodEntryAggregate.breakfast.push(foodEntries[i]);
			continue;
		}
		if (foodEntries[i].meal.toLowerCase() == "lunch") {
			foodEntryAggregate.lunch.push(foodEntries[i]);
			continue;
		}
		if (foodEntries[i].meal.toLowerCase() == "dinner") {
			foodEntryAggregate.dinner.push(foodEntries[i]);
			continue;
		}
		if (foodEntries[i].meal.toLowerCase() == "other") {
			foodEntryAggregate.other.push(foodEntries[i]);
			continue;
		}
	}
	return foodEntryAggregate;
}








/**
 * Calculate the total nutrients of the day
 * @param {Array} array of FoodEntry for the day
 * @return {Object} an object of summed nutrients
 */
function getDailyTotal(foodEntries) {
	var foodEntryTotal = new Object();
	var totalCalories = 0, totalFat = 0, totalSatFat = 0, totalTransFat = 0;
	var totalPolyFat = 0, totalMonoFat = 0, totalCholesterol = 0;
	var totalSodium = 0, totalCarb = 0, totalFiber = 0, totalSugar = 0;
	var totalProtein = 0, totalVA = 0, totalVC = 0, totalIron = 0, totalPotassium = 0;
	for (var i = 0; i < foodEntries.length; i++) {	
		totalCalories = totalCalories + foodEntries[i].calories;
		totalFat = totalFat + foodEntries[i].fat;
		totalCholesterol = totalCholesterol + foodEntries[i].cholesterol;
		totalSatFat = totalSatFat + foodEntries[i].saturated_fat;
		totalTransFat = totalTransFat + foodEntries[i].trans_fat;
		totalPolyFat = totalPolyFat + foodEntries[i].polyunsaturated_fat;
		totalMonoFat = totalMonoFat + foodEntries[i].monounsaturated_fat;
		totalSodium = totalSodium + foodEntries[i].sodium;
		totalCarb = totalCarb + foodEntries[i].carbohydrates;
		totalFiber = totalFiber + foodEntries[i].fiber;
		totalSugar = totalSugar + foodEntries[i].sugar;
		totalProtein = totalProtein + foodEntries[i].protein;
		totalVA = totalVA + foodEntries[i].vitamin_a;
		totalVC = totalVC + foodEntries[i].vitamin_c;
		totalIron = totalIron + foodEntries[i].iron;
		totalPotassium = totalPotassium + foodEntries[i].potassium;
		
		
	}

	foodEntryTotal.calories = totalCalories.toFixed(0);
	foodEntryTotal.fat = totalFat.toFixed(0);
	foodEntryTotal.cholesterol = totalCholesterol.toFixed(0);
	foodEntryTotal.saturated_fat = totalSatFat.toFixed(0);
	foodEntryTotal.trans_fat = totalTransFat.toFixed(0);
	foodEntryTotal.polyunsaturated_fat = totalPolyFat.toFixed(0);
	foodEntryTotal.monounsaturated_fat = totalMonoFat.toFixed(0);
	foodEntryTotal.sodium = totalSodium.toFixed(0);
	foodEntryTotal.carbohydrates = totalCarb.toFixed(0);
	foodEntryTotal.fiber = totalFiber.toFixed(0);
	foodEntryTotal.sugar = totalSugar.toFixed(0);
	foodEntryTotal.protein = totalProtein.toFixed(0);
	foodEntryTotal.vitamin_a = totalVA.toFixed(0);
	foodEntryTotal.vitamin_c = totalVC.toFixed(0);
	foodEntryTotal.iron = totalIron.toFixed(0);
	foodEntryTotal.potassium = totalPotassium.toFixed(0);

	return foodEntryTotal;
}



$(function() {
    $('#compose-button').on('click', function(e) {



        $('#compose-form').show();
        $("html, body").animate({ scrollTop: $(document).height() });
    });

	$('#compose-cancel').on('click', function(e) {
        $('#compose-form').hide();
    });

});






