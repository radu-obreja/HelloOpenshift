var helloOpenshift = angular.module('hello', ['ngMaterial', 'ngMessages']);

helloOpenshift.config(function($mdThemingProvider) {
	$mdThemingProvider.disableTheming();
});

helloOpenshift.controller('LogoutController', function($scope, $window, $http) {
	

	$scope.logout = function() {
		var request = {};
		var req = {method: 'POST', url: 'api/authentication/logout', headers: {'Content-Type':'application/json'}, data: request}
		$http(req).then(
			function successCallback(response) {
				$window.location.href = '/index';
			}, 
			function errorCallback(response) {
			}
		);
	}

});
