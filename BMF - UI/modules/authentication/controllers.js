'use strict';
 
var x = angular.module('Authentication');
 
x.controller('LoginController',
    ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
        // reset login status
        AuthenticationService.ClearCredentials();
        $scope.login = function () {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password, function(response) {
                if(response.success) {
                    AuthenticationService.SetCredentials($scope.username, $scope.password);
                    $location.path('/');
                } else {
                    $scope.login_error = response.message;
                    debugger;
                    $( "#login_fail" ).addClass( "isa_error" );
                    $( "#login_fail" ).append( "<i class=\"fa fa-times-circle\"></i>" );   
                }
            $scope.dataLoading = false;
                $('#login_fail').fadeIn("fast");
                $('#login_fail').fadeOut(3000).done(function(){
                    $('.fa-times-circle').remove();
                });
            });
        };
        $scope.register = function () {
            $scope.dataLoading = true;
            AuthenticationService.Register($scope.firstname, $scope.lastname, $scope.email, $scope.password,function(response){
            if(response.success) {
                $scope.register_msg = response.message;
                } else {
                    $scope.register_msg = response.message;
                }
            $scope.dataLoading = false;
            $('#register_msg').fadeIn("fast");
            $('#register_msg').fadeOut(3000);
            });
        };

        $scope.recoverPassword = function () {
            $scope.dataLoading = true;
            AuthenticationService.recoverPassword($scope.email,function(response){
            if(response.success) {
                $scope.password_msg = response.message;
                } else {
                    $scope.password_msg = response.message;
                }
            $scope.dataLoading = false;
            $('#password_msg').fadeIn("fast");
            $('#password_msg').fadeOut(3000);
            });
        };

    }]);