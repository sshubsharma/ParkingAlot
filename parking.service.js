angular.module('parkingApp').factory('ParkingLot', ParkingLot);

ParkingLot.$inject = [ '$resource' ];

function ParkingLot($resource) {
	var resourceUrl = 'api/parkingLot/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}