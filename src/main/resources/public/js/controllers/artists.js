helloOpenshift.controller('ArtistController', function($scope, $http) {

	$scope.roles = roles;
	$scope.user = user;

	$scope.artists = [];
	
	$scope.getList = function() {
		var request = {};
		var req = {method: 'POST', url: 'api/artist/list', headers: {'Content-Type':'application/json'}, data: request}
		$http(req).then(
			function successCallback(response) {
				$scope.artists = response.data.artists;
			}, 
			function errorCallback(response) {
			}
		);
	}

	$scope.getList();
	
});
