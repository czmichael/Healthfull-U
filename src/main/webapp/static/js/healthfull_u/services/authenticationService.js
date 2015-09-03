healthfullUApp.factory("authenticationService", 
function($http, $q, $window) {
    var userInfo;
    var service = {};

    service.login = function(username, password) {
       var deferred = $q.defer();

       $http.post(loginUrl, {username: username, password: password}).then(
        function(response) {
            userInfo = {
                accessToken: response.data.access_token,
                username: username
            };
            $window.sessionStorage["userInfo"] = JSON.stringify(userInfo);
            deferred.resolve(userInfo);
            
        }, 
        function(response) {
            deferred.reject();
            
        });

        return deferred.promise;
    }

    return service;



});



    // $http.post(loginUrl, {username: $scope.username, password: $scope.password}).
    //     then(function(response) {
    //         console.log(JSON.stringify(response));
    //         sessionStorage.token = response.data.token;
    //         $location.path('/home')
    //     }, function(response) {
    //         console.log(JSON.stringify(response));
    //         sessionStorage.removeItem("token");
    //         $scope.error = "Invalid user or password";
    //         $scope.dataLoading = false;
            
    //     });