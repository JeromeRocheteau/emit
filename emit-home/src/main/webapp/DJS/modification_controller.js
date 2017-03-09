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
		
		
		
		$scope.primekey = null;
		$scope.info = null;
		$scope.old_primekey = null;
		$scope.old_info = null;
		

		$scope.show_data = function(primekey1, info1) {
			$scope.old_info = info1;
			$scope.old_primekey = primekey1;
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
				var indata =[];
					item1 = {};
					item1[prime_key] = $scope.old_primekey;
					item1[info] = $scope.old_info;
					indata.push(item1);
					item2 = {};
					item2[prime_key] = $scope.primekey;
					item2[info] = $scope.info;
					indata.push(item2);
					
				$http.post(adresse+"/update", indata)
						.then(function(response) {
							$scope.setAlert("Info", "Observer created");
							$scope.afficher_table();
						});
					//console.log(angular.toJson(indata));
				$scope.clear();
			}
		}
		$scope.clear = function() {
			$scope.primekey = null;
			$scope.info = null;
		}
	});