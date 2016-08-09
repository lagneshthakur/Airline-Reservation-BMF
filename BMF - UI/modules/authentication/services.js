'use strict';
 
angular.module('Authentication')
 
.factory('AuthenticationService',
    ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout',
    function (Base64, $http, $cookieStore, $rootScope, $timeout) {
        var service = {};
        
        service.Login = function (username, password, callback) {


            $http.get('http://localhost:8090/bmf.services/rest/Login/LoginService/GetUsers/'+username+'/'+password).
            success(function(data) {  
             var boolpassword = (data.Password.toLowerCase() === 'true');
             var booluser = (data.User.toLowerCase() === 'true');
             var is_active = (data.is_active.toLowerCase() === 'true');
             var response = { success: boolpassword, user: booluser };
             if(response.user)
            {

                 if(!response.success)
                 {
                    response.message = 'Invalid password';  
                 }
                 else if(!is_active)
                 {
                    response.message = 'Account not activated';
                    response.success = false;

                 }
                 else
                    response.message = 'Success';  
            }
            else
            {
                response.success = false;
                response.message = 'User not registered';     
            }
            callback(response);
        });
        };

        service.Register = function(firstname,lastname,email,password,callback){
            // Call Register Service
            $http.get('http://localhost:8090/bmf.services/rest/Register/RegisterService/RegisterUsers/'+firstname+'/'+lastname+'/'+email+'/'+password).
            success(function(data) {  
                var registered = (data.User.toLowerCase() === 'registered')
                var exists = (data.User.toLowerCase() === 'exists')
                var response = {success: registered, alreadyexists: exists};
                if(response.alreadyexists)
                {
                    response.message = 'User is already registered.';
                }
                else if(!response.success)
                {
                    response.message = 'Registration Failed';
                }
                else
                    response.message = 'Successfuly Registered';
                callback(response);
        });
        };

        service.recoverPassword = function(email,callback){
            // Call Register Service
            $http.get('http://localhost:8090/bmf.services/rest/Login/LoginService/PasswordRecovery/'+email).
             success(function(data) {  
                debugger;
             var booluser = (data.User.toLowerCase() === 'true');
             var response = { success: booluser};
             if(response.success)
            {
                    response.message = 'Successfuly Sent Mail';
            }
            else
            {
                response.message = 'User not registered';     
            }
            callback(response);
        });
        };
 
        service.SetCredentials = function (username, password) {
            var authdata = Base64.encode(username + ':' + password);
 
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };
 
            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        };
 
        service.ClearCredentials = function () {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic ';
        };
 
        return service;
    }])
 
.factory('Base64', function () {
    /* jshint ignore:start */
 
    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
 
    return {
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
 
            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
 
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
 
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
 
                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);
 
            return output;
        },
 
        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;
 
            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
 
            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));
 
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
 
                output = output + String.fromCharCode(chr1);
 
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
 
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
 
            } while (i < input.length);
 
            return output;
        }
    };
 
    /* jshint ignore:end */
});