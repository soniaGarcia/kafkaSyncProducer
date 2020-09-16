package com.prueba.apache.kafka.rest;

import com.prueba.apache.kafka.helper.ProductorKafka;
import com.prueba.apache.kafka.mensajeDTO.VehiculoMsj;
import com.prueba.apache.kafka.model.Vehiculo;
import com.prueba.apache.kafka.repository.VehiculoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sonia
 */
@RestController
@RequestMapping(value = "/vehiculos")
public class VehiculosRest {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    private ProductorKafka productorKafka;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehiculo> getVehiculos() {
        Iterable<Vehiculo> iterator = vehiculoRepository.findAll();
        List<Vehiculo> result = new ArrayList<>();
        iterator.forEach(result::add);
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addVehiculo(@RequestBody @Valid VehiculoMsj vehiculo) {

        Vehiculo persistVehiculo = new Vehiculo();
        persistVehiculo.asientos = vehiculo.asientos;
        persistVehiculo.color = vehiculo.color;
        persistVehiculo.descripcion = vehiculo.descripcion;
        persistVehiculo.llantas = vehiculo.llantas;
        persistVehiculo.marca = vehiculo.marca;
        persistVehiculo.codigo = vehiculo.marca.substring(0, 2) + "-" + vehiculo.color.substring(0, 2);
        persistVehiculo.pasajeros = vehiculo.pasajeros;
        persistVehiculo.puertas = vehiculo.puertas;
        persistVehiculo.ventanas = vehiculo.ventanas;
        vehiculo.codigo = persistVehiculo.codigo;
        persistVehiculo = vehiculoRepository.save(persistVehiculo);
        vehiculo.id = persistVehiculo.id;
        try {
            productorKafka.sendCustomMessage(vehiculo);
        } catch (ExecutionException | InterruptedException ex) {
            vehiculoRepository.delete(persistVehiculo);
        }
    }
}
