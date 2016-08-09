'use strict';

// declare modules
angular.module('Authentication', []);
angular.module('Home', []);
angular.module('Flight', []);
angular.module('Payment', []);

angular.module('BasicHttpAuthExample', [
    'Authentication',
    'Home',
    'Flight',
    'Payment',
    'ngRoute',
    'ngCookies'
])
 
.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'modules/authentication/views/login.html',
            hideMenus: true
        })

        .when('/flightSearch', {
            controller: 'FlightController',
            templateUrl: 'modules/flight/views/flights.html',
            hideMenus: true
        })

        .when('/flightManage', {
            controller: 'FlightAdminController',
            templateUrl: 'modules/flight/views/flight_admin.html',
            hideMenus: true
        })

        .when('/bookFlight', {
            controller: 'BookingController',
            templateUrl: 'modules/flight/views/book.html',
            hideMenus: true
        })

        .when('/payment', {
            controller: 'PaymentController',
            templateUrl: 'modules/payment/views/payment.html',
            hideMenus: true
        })

        .when('/ticket', {
            controller: 'PaymentController',
            templateUrl: 'modules/payment/views/ticket.html',
            hideMenus: true
        })

        .when('/aboutus', {
            templateUrl: 'modules/templates/about-us.html',
            hideMenus: true
        })

        // .when('/anything', {
        //     controller: 'ActivationController',
        //     templateUrl: 'modules/activation/views/activate.html',
        //     hideMenus: true
        // })
 
        .when('/', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/home.html'
        })
 
        .otherwise({ redirectTo: '/login' });
}])
 
.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser && $location.path() !== '/aboutus') {
                $location.path('/login');
            }

        });

    }]);