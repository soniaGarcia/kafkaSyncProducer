package com.prueba.apache.kafka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sonia
 */
@Controller
public class VehiculoController {

        @RequestMapping("/mantoVehiculo")
	public ModelAndView helloworld() {
		return new ModelAndView("vehiculo");
	}

}
