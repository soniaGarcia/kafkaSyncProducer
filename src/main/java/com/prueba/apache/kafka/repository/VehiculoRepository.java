package com.prueba.apache.kafka.repository;

import com.prueba.apache.kafka.model.Vehiculo;
import com.prueba.apache.kafka.model.QVehiculo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sonia.cabrera
 */
public interface VehiculoRepository 
        extends CrudRepository<Vehiculo, String>, QuerydslPredicateExecutor<Vehiculo>, QuerydslBinderCustomizer<QVehiculo>{
    
    @Override
	default public void customize(QuerydslBindings bindings, QVehiculo root) {

	}
}
