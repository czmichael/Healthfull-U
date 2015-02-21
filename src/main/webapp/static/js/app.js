'use strict';

var healthfullUApp = angular.module('healthfullUApp', []);

healthfullUApp.controller('UserCtrl', function ($scope) {
//  $scope.user = [
//    {'userId': '1',
//     'email': 'cz_michael@yahoo.com',
//     'password': '123456' 
//    }
//  ];
  
  $scope.name = 'World 14';
  
  
  
//  $scope.user = 'zhi chen'; 
//  
//  $scope.email = 'cz_michael@yahoo.com';
  
  
  
  
$scope.user = {
	 'userId': '1',
	 'email': 'cz_michael@yahoo.com',
	 'password': 'abcdefg' 
	};




});