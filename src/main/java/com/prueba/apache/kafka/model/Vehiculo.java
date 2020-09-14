package com.prueba.apache.kafka.model;

import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author sonia.cabrera
 */
@Document(collection = "vehiculo")
public class Vehiculo {

    @Id
    public ObjectId id;

    public Integer puertas;
    public Integer ventanas;
    public Integer llantas;
    public Integer pasajeros;
    public Integer asientos;
    public String descripcion;
    public String color;
    public String marca;
    public String codigo;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}
