var app = angular.module('vehiculos', ["ngResource"]);

app.controller('VehiculosController', ['$scope', '$http',

    function ($scope, $http) {
        $scope.getVehiculos = function () {
            $http.get('/vehiculos').success(function (data) {
                $scope.vehiculos = data;
            });
        }

        $scope.addVehiculo = function () {
            $http.post('/vehiculos/add',
                    {
                        puertas: $scope.puertas,
                        ventanas: $scope.ventanas,
                        llantas: $scope.llantas,
                        pasajeros: $scope.pasajeros,
                        asientos: $scope.asientos,
                        descripcion: $scope.descripcion,
                        color: $scope.color,
                        marca: $scope.marca,
                        precio: $scope.precio
                    }
            ).success(function (data) {
                alert('Vehiculo creado correctamente');
                $scope.getVehiculos();
            }).error(function (data) {
                 alert('Se ha producido un error');
            });
        }


    }]);


