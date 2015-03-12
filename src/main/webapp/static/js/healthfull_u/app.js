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

var baseUrl = 'http://localhost:8080';


var userUrl = baseUrl + '/rest/json/user/get';
var foodEntryUrl = baseUrl + '/rest/json/food_entry/get';
var emailCreateUrl = baseUrl + '/rest/json/email/create';

