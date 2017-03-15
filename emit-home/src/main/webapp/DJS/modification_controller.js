/**
 * 
 */
	var app = angular.module('app', []);

	app.controller('myCtrl', function($scope, $http) {
		
		$scope.primekey = null;
		$scope.info = null;
		$scope.old_primekey = sessionStorage.getItem("primarykey");
		$scope.old_info = sessionStorage.getItem("info");
		$scope.show_current = function(){
			$scope.old_primekey = $scope.primekey;
			$scope.old_info = $scope.info;
		}
		
		$scope.create = function() {
			if ($scope.name === null) {
				alert("The field 'name' is empty");
			} else if ($scope.old_name === null) {
				alert("The field 'name' is empty");
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
							alert("Element updated");
						});
				$scope.show_current();
				$scope.clear();
			}
		}
		$scope.clear = function() {
			$scope.primekey = null;
			$scope.info = null;
		}
	});