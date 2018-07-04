helloOpenshift.controller('MovieController', function($scope, $http) {
	
	$scope.roles = roles;
	$scope.user = user;

	$scope.movies = [];
	
	$scope.getList = function() {
		var request = {};
		var req = {method: 'POST', url: 'api/movie/list', headers: {'Content-Type':'application/json'}, data: request}
		$http(req).then(
			function successCallback(response) {
				$scope.movies = response.data.movies;				
			}, 
			function errorCallback(response) {
			}
		);
	}
	
	$scope.getList();
	
});

