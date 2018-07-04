helloOpenshift.controller('LoginController', function($scope, $window, $http) {
	
	$scope.hoUsername = '';
	$scope.hoPassword = '';
	
	$scope.user = {};
	$scope.roles = roles;

	$scope.authenticate = function() {
		var request = {};
		var headers = {
			'Content-Type':'application/json',
			'username':$scope.hoUsername,
			'password':$scope.hoPassword
		}
		var req = {method: 'POST', url: 'api/authentication/authenticate', headers: headers, data: request}
		$http(req).then(
			function successCallback(response) {
				$scope.user = response.data.user;
				$window.location.href = '/home';
			}, 
			function errorCallback(response) {
			}
		);
	}

});
