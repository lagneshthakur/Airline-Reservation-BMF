'use strict';
 
var m = angular.module('Payment');
 
m.controller('PaymentController',
    ['$scope', '$http','$location','$rootScope',
    function ($scope, $http, $location, $rootScope) {
        $scope.username=$rootScope.globals.currentUser.username;
        $scope.seats = $location.hash();
        var seatsobj=$scope.seats.split(",")
        var len = seatsobj.length;
        $scope.total=len*$scope.flight.price;
        $scope.bookSeats=function(flight, seats, username){
            $http.get('http://localhost:8090/bmf.services/rest/Book/BookingService/Book/'+$scope.username+'/'+flight.id+'/'+seats).
            success(function(data) {
                $scope.success="Seats Booked"
            });
            $location.path('/ticket');
        }
    }]);