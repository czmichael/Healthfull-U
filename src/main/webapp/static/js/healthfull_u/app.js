'use strict';

var healthfullUApp = angular.module('healthfullUApp', [
	'ngRoute', 
//    'ngCookies',
    'cgBusy'
])
.config(['$routeProvider', 
  function($routeProvider) {
    	$routeProvider.
      		when('/home', {
        		templateUrl: 'daily_view.html',
        		controller: 'FoodCtrl'
      		}).
      		when('/food_entry_detail', {
        		templateUrl: 'food_entry_detail.html',
        		controller: 'UserCtrl'
      		}).
      		when('/login', {
      			templateUrl: 'login.html',
      			controller: 'LoginCtrl'    
      		}).
            when('/logout', {
                /*
                 * Leave the template to be empty (need a space character in between the double quotes)
                 * so the location will be set by the controller.
                 */
                template: " ",
                controller: 'LogoutCtrl'
            }).
      		otherwise({
        		redirectTo: '/home'
      		});
  	}
])
.run(['$rootScope', '$location', '$http', '$window',
    function ($rootScope, $location, $http, $window) {

        // keep user logged in after page refresh by checking if "userInfo" is saved in sessionStorage
        if ($window.sessionStorage["userInfo"]) {
            $rootScope.userInfo = JSON.parse($window.sessionStorage["userInfo"]);
        }
  
        // Listen for 'locationChangeStart' event broadcast on rootScope
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.userInfo) {
                $location.path('/login');
            }
        });
}]);

var baseUrl = 'http://localhost:8080';
//var baseUrl = 'http://healthfull-u.appspot.com';


var userUrl = baseUrl + '/rest/json/user/get';
var foodEntryUrl = baseUrl + '/rest/json/food_entry/get';
var emailCreateUrl = baseUrl + '/rest/json/email/create';
var loginUrl = baseUrl + '/rest/api/login';
var logoutUrl = baseUrl + '/rest/api/logout';


jQuery(window).load(function () {
  $('#compose-button').show();
});