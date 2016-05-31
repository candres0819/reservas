(function () {
	'use strict';

	var myApp = angular.module('app', ['ngRoute']);

	myApp.config(function($routeProvider, $httpProvider) {
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
		});
	});

	myApp.controller('navigation', function($rootScope, $scope, $http, $location, $route) {
		var self = this;
		self.tab = function(route) {
			return $route.current && route === $route.current.controller;
		};

		var authenticate = function(credentials, callback) {
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
		};

		authenticate();
		$scope.credentials = {};
		$scope.login = function() {
			$http.post('login', $.param($scope.credentials), {
				headers : {
					"content-type" : "application/x-www-form-urlencoded"
				}
			}).success(function(data) {
				authenticate($scope.credentials, function() {
					if ($rootScope.authenticated) {
						$location.path("/");
						$scope.error = false;
					} else {
						$location.path("/login");
						$scope.error = true;
					}
				});
			}).error(function(data) {
				$location.path('/login');
				$scope.error = true;
				$rootScope.authenticated = false;
			});
		}

		$scope.logout = function() {
			$http.post('logout', {}).success(function() {
				$rootScope.authenticated = false;
				$location.path("/");
			}).error(function(data) {
				$rootScope.authenticated = false;
			});
		}
	});

	myApp.controller('registro', function($rootScope, $scope, $http, LoginService) {
		$scope.vm = {};
		$scope.parametros = {};
		$scope.register = function() {
			$http.post('registrar', $scope.vm.user).success(function(data) {
				console.log('data: ' + data);
				$scope.credentials = {};
				$scope.credentials.username = $scope.vm.user.userName;
				$scope.credentials.password = $scope.vm.user.password;

				LoginService.login($scope.credentials);
			}).error(function(data) {
				console.log(data);
				$scope.error = true;
				$rootScope.authenticated = false;
			});
		}
	});

	myApp.service('LoginService', function($http, $scope, $rootScope, $location) {
		var authenticate = function(credentials, callback) {
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
		};

		this.login = function (credentials) {
			$http.post('login', $.param(credentials), {
				headers : {
					"content-type" : "application/x-www-form-urlencoded"
				}
			}).success(function(data) {
				authenticate(credentials, function() {
					if ($rootScope.authenticated) {
						$location.path("/");
						$scope.error = false;
					} else {
						$location.path("/login");
						$scope.error = true;
					}
				});
			}).error(function(data) {
				$location.path('/login');
				$scope.error = true;
				$rootScope.authenticated = false;
			});
		}
	});
})();