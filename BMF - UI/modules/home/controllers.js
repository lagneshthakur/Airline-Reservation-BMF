'use strict';
 
angular.module('Home')
 
.controller('HomeController',
    ['$scope',
    function ($scope) {
    	$scope.clearModal=function(){
    		$('.modal-backdrop').remove();
    	}
    $scope.clearModal();
    }]);