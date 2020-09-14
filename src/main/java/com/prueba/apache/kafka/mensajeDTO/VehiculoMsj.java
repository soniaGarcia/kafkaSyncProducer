package com.prueba.apache.kafka.mensajeDTO;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

/**
 *
 * @author sonia.cabrera
 */
@Getter
@Setter
public class VehiculoMsj {

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
    public Integer precio;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}
