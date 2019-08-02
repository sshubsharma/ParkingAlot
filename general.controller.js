angular.module("parkingApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'ParkingApp' ];

function GeneralController($scope, ParkingLot) {
	
	$scope.parkingLot = ParkingLot.query();

	$scope.parkingLot = {};
	
	$scope.buttonText="Submit";
	
	$scope.saveParkingLot = function() {
		if ($scope.parkingLot.id !== undefined) {
			ParkingLot.update($scope.parkingLot, function() {
				$scope.parkingLots = ParkingLot.query();
				$scope.parkingLot = {};
				$scope.buttonText="Submit";
			});
		} else {
			ParkingLot.save($scope.parkingLot, function() {
				$scope.parkingLot = ParkingLot.query();
				$scope.parkingLot = {};
			});
		}
	}

	$scope.updateParkingInit = function(parkingLot) {
		$scope.buttonText="Update";
		$scope.parkingLot = parkingLot;
	}

	$scope.deleteParking = function(parkingLot) {
		employee.$delete({id: parkingLot.id}, function() {
			$scope.parkingLotw = ParkingLot.query();
		});
	}
	
	

	
}