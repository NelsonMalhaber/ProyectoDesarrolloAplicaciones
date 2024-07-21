package com.uss.restaurante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/reservas")
    public String getReservas(Model model) {
        return "reserva";
    }
    @GetMapping("/pagos")
	public String getPagos(Model model) {
		return "pago";
	}
    @GetMapping("/usuarios")
    public String getUsuarios(Model model) {
		return "usuario";
	}
	@GetMapping("/clientes")
    public String getClientes(Model model) {
		return "clientes";
	}
	
    @GetMapping("/mesas")
	public String getMesas(Model model) {
		return "mesa";
	}
    @GetMapping("/trabajadores")
    public String getTrabajadores(Model model) {
        return "trabajador";
    }

}

