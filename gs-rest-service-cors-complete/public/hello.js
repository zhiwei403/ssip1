    
var camListApp = angular.module('camListApp', ['ui.bootstrap', 'angularUtils.directives.dirPagination'])

    camListApp.filter('unique', function() {
        return function(input, key) {
            var unique = {};
            var uniqueList = [];
            for(var i = 0; i < input.length; i++){
                if(typeof unique[input[i][key]] == "undefined"){
                    unique[input[i][key]] = "";
                    uniqueList.push(input[i]);
                }
            }
            return uniqueList;
        };
    });


    camListApp.controller("Hello", function($scope, $http, $uibModal, $q){
    	
    	/*$scope.del = function (record) {
    		 $http.delete('/camera/' + record.filename).then(function(){
    			 $scope.records.splice($scope.records.indexOf(record), 1);
         
    		});
              };*/
            
    	$scope.del = function (record) {
            if (confirm('Do you really want to delete?')){
            	 $http['delete']('/camera/list/' + record.filename).then(function() {
            		 $scope.records.splice($scope.records.indexOf(record), 1);
              });
            }
          };
       
    		 
    		/* $scope.del = function(record) {
    			 var index = $scope.records.indexOf(record);
    			 $scope.records.splice(index , 1);
    		 
    			 };*/
         
    
    	    $scope.open = function (imageView){
    	    	    
    		var modalInstance = $uibModal.open({
    			
    		templateUrl: 'popup.html',
    		controller: 'ModalInstanceCtrl',
    		resolve: {
    	        records : function () {
    	          return imageView;;
    	        }
    		}
    		});
    		}
    	    
    	   
    	    $http.get('http://localhost:8082/camera/list').then(function(response) {
    	   	 console.log(response);
    	   	$scope.records= response.data; 
    	      });  
    	    $http.get('http://localhost:8082/location/list2').then(function(response){
                $scope.locations = response.data;
            });
  });
    
   
    angular.module('camListApp').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, records) {

    	  $scope.records = records;
    	 

    	  $scope.cancel = function () {
    	    $uibModalInstance.dismiss('cancel');
    	  };
    	});
   
  
  
    