'use strict';
 
var m = angular.module('Flight');
 
m.controller('FlightController',
    ['$scope', '$http','$location','$rootScope',
    function ($scope, $http, $location, $rootScope) {
    	$scope.callselect2 = function(){
    	$(".from_location").select2();
    	$(".to_location").select2();
    	}
    	// call select2 
    	$scope.callselect2();

    	// Search Flight function
        $scope.searchFlights = function () {
            $scope.dataLoading = true;
            $http.get('http://localhost:8090/bmf.services/rest/Flight/FlightService/GetFlights/'+$scope.from_location+'/'+$scope.to_location).
            success(function(data) {
            	$scope.flights=data;
            });
        };
        $scope.bookFlights = function (flight) {
            $rootScope.flight = flight;
            $http.get('http://localhost:8090/bmf.services/rest/Book/BookingService/GetBooked/'+flight.id).
            success(function(data) {
                var i,x="";
                for(i in data){
                    if(i%35!=0)
                    x=x+(i%35)+" ";
                    else
                    x=x+35+" ";
                };
                $rootScope.booked=x;
                $location.path('/bookFlight');
            });

        };

    }]);

m.controller('FlightAdminController',
    ['$scope', '$http', '$rootScope','$location',
    function ($scope, $http, $rootScope, $location) {
        var user = $rootScope.globals.currentUser.username;

        if(user.toLowerCase() != "admin@bmf.com")
        {
            $location.path('/login');
        }
        // Search All Flight function
        $scope.searchAllFlights = function () {
            $scope.dataLoading = true;
            $http.get('http://localhost:8090/bmf.services/rest/Flight/FlightService/GetFlights/').
            success(function(data) {
                $scope.flights=data;
            });
        };

        $scope.addFlight = function () {
            $scope.dataLoading = true;
           
            $http.get('http://localhost:8090/bmf.services/rest/Flight/FlightService/AddFlight/'+$scope.airline_name+'/'+$scope.from_location+'/'+$scope.to_location+'/'+$scope.departure_time+'/'+$scope.arrival_time+'/'+$scope.price).
            success(function(data) {
                if(data == true)
                    $scope.flight_msg="Flight Successfully Added.";
                else
                    $scope.flight_msg="Flight Addition Failed.";
            });
        };

    }]);
m.controller('BookingController',
    ['$scope', '$http', '$rootScope','$location',
    function ($scope, $http, $rootScope, $location) {
       $scope.flight = $rootScope.flight;
       $scope.bookSeat= function(){ 
            $scope.dataLoading = true;
            $location.hash($scope.seats);
            $location.path('/payment');
            // $http.get('http://localhost:8090/bmf.services/rest/Flight/FlightService/AddFlight/'+$scope.airline_name+'/'+$scope.from_location+'/'+$scope.to_location+'/'+$scope.departure_time+'/'+$scope.arrival_time+'/'+$scope.price).
            // success(function(data) {
            //     if(data == true)
            //         $scope.flight_msg="Flight Successfully Added.";
            //     else
            //         $scope.flight_msg="Flight Addition Failed.";
            // });
            

       };
        var user = $rootScope.globals.currentUser.username;
        var mappingJSON={
        "1":"1_1","2":"1_2","3":"1_4","4":"1_5","5":"2_1","6":"2_2","7":"2_4","8":"2_5","9":"3_1","10":"3_2","11":"3_4","12":"3_5","13":"4_1","14":"4_2","15":"4_4","16":"4_5","17":"5_1","18":"5_2","19":"6_1","20":"6_2","21":"6_4","22":"6_5","23":"7_1","24":"7_2","25":"7_4","26":"7_5","27":"8_1","28":"8_2","29":"8_4","30":"8_5","31":"9_1","32":"9_2","33":"9_3","34":"9_4","35":"9_5"
        };
       var bookedSeats=$rootScope.booked+'';
       bookedSeats = bookedSeats.split(" ");
       var bookedArray = [];
       for(var i in bookedSeats)
            {
             bookedArray.push(mappingJSON[bookedSeats[i]]);
            }
        bookedArray.pop();
        var firstSeatLabel = 1;
                var $cart = $('#selected-seats'),
                    $counter = $('#counter'),
                    $total = $('#total'),
                    sc = $('#seat-map').seatCharts({
                    map: [
                        'ee_ee',
                        'ee_ee',
                        'ee_ee',
                        'ee_ee',
                        'ee___',
                        'ee_ee',
                        'ee_ee',
                        'ee_ee',
                        'eeeee',
                    ],
                    seats: {
                        f: {
                            price   : 100,
                            classes : 'first-class', //your custom CSS class
                            category: 'First Class'
                        },
                        e: {
                            price   : $scope.flight.price,
                            classes : 'economy-class', //your custom CSS class
                            category: 'Economy Class'
                        }                   
                    
                    },
                    naming : {
                        top : false,
                        getLabel : function (character, row, column) {
                            return firstSeatLabel++;
                        },
                    },
                    legend : {
                        node : $('#legend'),
                        items : [
                            [ 'e', 'available',   'Economy Class'],
                            [ 'f', 'unavailable', 'Already Booked']
                        ]                   
                    },
                    click: function () {
                        if (this.status() == 'available') {
                            //let's create a new <li> which we'll add to the cart items
                            $('<li>'+this.data().category+' Seat # '+this.settings.label+': <b>Rs.'+this.data().price+'</b>')
                                .attr('id', 'cart-item-'+this.settings.id)
                                .data('seatId', this.settings.id)
                                .appendTo($cart);
                            
                            /*
                             * Lets update the counter and total
                             *
                             * .find function will not find the current seat, because it will change its stauts only after return
                             * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.
                             */
                            $counter.text(sc.find('selected').length+1);
                            $total.text(recalculateTotal(sc)+this.data().price);
                            
                            return 'selected';
                        } else if (this.status() == 'selected') {
                            //update the counter
                            $counter.text(sc.find('selected').length-1);
                            //and total
                            $total.text(recalculateTotal(sc)-this.data().price);
                        
                            //remove the item from our cart
                            $('#cart-item-'+this.settings.id).remove();
                        
                            //seat has been vacated
                            return 'available';
                        } else if (this.status() == 'unavailable') {
                            //seat has been already booked
                            return 'unavailable';
                        } else {
                            return this.style();
                        }
                    }
                });

                //let's pretend some seats have already been booked
                sc.get(bookedArray).status('unavailable');

            function recalculateTotal(sc) {
                var total = 0;
            
                //basically find every selected seat and sum its price
                sc.find('selected').each(function () {
                    total += this.data().price;
                });
                
                return total;
            }
    }]);