'use strict';

var healthfullUApp = angular.module('healthfullUApp', [
	'ngRoute', 
//  'ngCookies',
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
      		otherwise({
        		redirectTo: '/home'
      		});
  	}
]);
// .run(['$rootScope', '$location', '$http', 
//     function ($rootScope, $location,  $http) {
//         keep user logged in after page refresh
//         $rootScope.globals = $cookieStore.get('globals') || {};
//         if ($rootScope.globals.currentUser) {
//             $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
//         }
  
//         $rootScope.$on('$locationChangeStart', function (event, next, current) {
//             // redirect to login page if not logged in
//             if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
//                 $location.path('/login');
//             }
//         });
// }]);

var baseUrl = 'http://localhost:8080';
//var baseUrl = 'http://healthfull-u.appspot.com';


var userUrl = baseUrl + '/rest/json/user/get';
var foodEntryUrl = baseUrl + '/rest/json/food_entry/get';
var emailCreateUrl = baseUrl + '/rest/json/email/create';
var loginUrl = baseUrl + '/rest/api/login';

jQuery(window).load(function () {
  $('#compose-button').show();
});