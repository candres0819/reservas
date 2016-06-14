(function () {
	'use strict';

	var myApp = angular.module('app', ['ngRoute']);

	myApp.config(function($routeProvider, $httpProvider, $provide) {
		$routeProvider.when('/', {
			templateUrl : 'home.html',
			controller : 'home',
			controllerAs: 'controller'
		}).when('/login', {
			templateUrl : 'login.html',
			controller : 'navigation',
			controllerAs: 'controller'
		}).when('/logout', {
			templateUrl : 'home.html',
			controller : 'logout',
			controllerAs: 'controller'
		}).when('/registro', {
			templateUrl : 'registro.html',
			controller : 'registro',
			controllerAs: 'controller'
		}).otherwise('/');
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	});

	myApp.controller('home', function($scope, $http) {
		$http.get('/resource/').then(function(response) {
			$scope.greeting = response.data;
			$('.navbar-nav a').on('click', function(event) {
				event.preventDefault();
				$('.target div').hide();
				$('.navbar-nav a').removeClass('active');
				var element = $(this);
				element.addClass('active');
				$(element.attr('href')).show();
			});
			$('.navbar-nav li:first-child a').addClass('active');
			$('#consultas').show();
		});
	});


	myApp.controller('navigation', function($rootScope, $scope, $http, $location, $route) {
		var self = this;
		self.tab = function(route) {
			return $route.current && route === $route.current.controller;
		};

		$scope.credentials = {};
		$scope.login = function() {
			LoginService.login($http, $scope, $rootScope, $location);
		};

		$scope.logout = function() {
			$http.post('logout', {}).success(function() {
				console.log('logout');
				$scope.credentials = {};
				$rootScope.authenticated = false;
				$location.path("/");
			}).error(function(data) {
				console.log('logout 2: ' + $scope.credentials.username);
				$scope.credentials = {};
				console.log('logout 2 ' + $scope.credentials);
				$rootScope.authenticated = false;
			});
		}
	});

	myApp.controller('registro', function($http, $scope, $rootScope, $location) {
		$scope.vm = {};
		$scope.register = function() {
			$http.post('registrar', $scope.vm.user).success(function(data) {
				$scope.credentials = {};
				$scope.credentials.username = $scope.vm.user.userName;
				$scope.credentials.password = $scope.vm.user.password;

				LoginService.login($http, $scope, $rootScope, $location);
			}).error(function(data) {
				$scope.error = true;
				$rootScope.authenticated = false;
			});
		};
	});

	var LoginService = {
		authenticate : function($http, $rootScope, credentials, callback) {
			var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
			$http.get('user', {
				headers : headers
			}).then(function(response) {
				if (response.data.name) {
					$rootScope.authenticated = true;
				} else {
					$rootScope.authenticated = false;
				}
				callback && callback($rootScope.authenticated);
			}, function() {
				$rootScope.authenticated = false;
				callback && callback(false);
			});
		},

		login : function ($http, $scope, $rootScope, $location) {
			var credentials = $scope.credentials;
			$http.post('login', $.param(credentials), {
				headers : {
					"content-type" : "application/x-www-form-urlencoded"
				}
			}).success(function(data) {
				LoginService.authenticate($http, $rootScope, credentials, function() {
					if ($rootScope.authenticated) {
						$location.path("/");
						$scope.error = false;
					} else {
						$location.path("/login");
						$scope.error = true;
					}
				});
			}).error(function(data) {
				console.log('Error Aut: ' + data.message);
				$location.path('/login');
				$scope.error = true;
				$rootScope.authenticated = false;
			});
		}
	};
})();