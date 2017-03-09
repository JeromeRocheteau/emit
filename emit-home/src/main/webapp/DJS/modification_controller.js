/**
 * 
 */
	var app = angular.module('app', []);

	app.controller('myCtrl', function($scope, $http) {

		$scope.afficher_table = function() {
			$http.get(adresse+"/list").then(
					function(response) {
						$scope.observerlist = response.data;
					});
		}

		$scope.afficher_table();

		$scope.uri = null;
		$scope.name = null;
		$scope.old_uri = null;
		$scope.old_name = null;

		$scope.show_data = function(name, uri) {
			$scope.old_name = name;
			$scope.old_uri = uri;
		}

		$scope.alert = {};
		$scope.setAlert = function(type, message) {
			$scope.alert.type = type;
			$scope.alert.message = message;
			$('#alert').modal('show');
		}

		$scope.create = function() {
			if ($scope.name === null) {
				$scope.setAlert("Error", "The field 'name' is empty");
			} else if ($scope.old_name === null) {
				$scope.setAlert("Error", "The field 'name' is empty");
			} else {
				var indata = [ {
					uri : $scope.old_uri,
					name : $scope.old_name
				}, {
					uri : $scope.uri,
					name : $scope.name
				} ];
				$http.post(adresse+"/update", indata)
						.then(function(response) {
							$scope.setAlert("Info", "Observer created");
							$scope.afficher_table();
						});
				$scope.clear();
			}
		}
		$scope.clear = function() {
			$scope.uri = null;
			$scope.name = null;
		}
	});