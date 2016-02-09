'use strict';

/**
 * @ngdoc function
 * @name publicApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the publicApp
 */
angular.module('publicApp')
.controller('MainCtrl', function ($scope, backend, Auth, $sessionStorage, $rootScope) {
	$.material.init();
	$scope.user = $sessionStorage.user;

	if($scope.user == null) {
		$scope.user = Auth.login();
	}
		
	backend.plannings.list().then(function(data) {
		$scope.plannings = data;
		$sessionStorage.plannings = data;
		$scope.$apply();
		$("#home-spinner").remove();
	});

	$scope.closeHomeInfo = function() {
		$("#home-info").fadeOut(300, function() { $(this).remove(); });
	}

});