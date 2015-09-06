healthfullUApp.factory("authenticationService", 
function($http, $q, $window, $rootScope) {
    var userInfo;
    var service = {};

    // ======= Login functionality ======
    service.login = function(username, password) {
       var deferred = $q.defer();

       $http.post(
            loginUrl, 
            {
                username: username, 
                password: password
            }
        ).then(function(response) {
            userInfo = {
                username: username,
                accessToken: response.data.access_token
            };
            /*
             * add user info from the session 
             */
            $rootScope.userInfo = userInfo;
            $window.sessionStorage["userInfo"] = JSON.stringify(userInfo);
            deferred.resolve(userInfo);
        }, function(response) {
            deferred.reject();
        });

        return deferred.promise;
    };

    // ======= Logout functionality ======
    service.logout = function(username, password) {     
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: logoutUrl,
            headers: {
                'access_token': $rootScope.userInfo.accessToken   
            }
        }).then(function(response) {
            /*
             * remove user info from the session 
             */
            $window.sessionStorage["userInfo"] = null;
            $rootScope.userInfo = null;
            deferred.resolve();
        }, function(error) {
            deferred.reject(error);
        });

        return deferred.promise;
    };

    return service;
});