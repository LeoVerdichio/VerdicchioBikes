/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controladores;

import com.example.demo.Entidades.Cliente;
import com.example.demo.Entidades.Pedido;
import com.example.demo.Servicios.ClienteServicio;
import com.example.demo.Servicios.PedidoServicio;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pedido")
public class PedidoControlador {

    PedidoServicio pedidoServicio;
    ClienteServicio clienteServicio;

    @Autowired
    public PedidoControlador(PedidoServicio pedidoServicio, ClienteServicio clienteServicio) {
        this.pedidoServicio = pedidoServicio;
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("/listarpedidos")
    public String listarpedidosalta(ModelMap model) {
        List<Pedido> listapedidos = pedidoServicio.listarpedidosalta();
        model.addAttribute("pedidos", listapedidos);
        return "pedido/listarpedidos";
    }

    @GetMapping("/listarpedidosbajac")
    public String listarpedidosbaja(ModelMap model) {
        List<Pedido> listapedidos = pedidoServicio.listarpedidosbaja();
        model.addAttribute("pedidos", listapedidos);
        return "pedido/listarpedidobaja";
    }

    @GetMapping("/baja/{id}")
    public String bajapedido(@PathVariable String id, ModelMap model) {
        try {
            pedidoServicio.baja(id);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }

        return "redirect:/pedido/listarpedidos";
    }

    @GetMapping("/alta/{id}")
    public String altapedido(@PathVariable String id, ModelMap model) {
        try {
            pedidoServicio.alta(id);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }

        return "redirect:/pedido/listarpedidosbajac";
    }

    @GetMapping("/editar/{id}")
    public String buscarcliente(@PathVariable String id, ModelMap model) {

        try {
            Pedido pedido = pedidoServicio.buscarporid(id);
//            Cliente cliente=pedido.getCliente();
//            System.out.println(cliente.getNombre());
            model.addAttribute("pedido", pedido);
//            model.addAttribute("cliente", cliente);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }

        return "cliente/editarpedido";
    }

    @PostMapping("/editarpedido")
    public String editarpedido(@ModelAttribute Pedido pedido, ModelMap model) {
        try {
//         clienteServicio.validaciones(cliente);
            String id = pedido.getId();
            System.out.println(id);
            Cliente cliente = pedidoServicio.traercliente(id);
            pedido.setCliente(cliente);
            pedido.setActivo(Boolean.TRUE);
              LocalDate date = LocalDate.now();
              pedido.setFechapedido(date);
            pedidoServicio.Guardar(pedido);

        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }

        return "redirect:/pedido/listarpedidos";
    }
    
    @GetMapping("/resumen")
    public String resumendeldia(ModelMap model){     
      String resumen=pedidoServicio.resumen();
      model.put("resumen", resumen);
        return "pedido/resumen";
        
        
    }
    
    @GetMapping("/ver/{id}")
    public String verpedido(@PathVariable String id,ModelMap model){
        try {
            Pedido pedido=pedidoServicio.buscarporid(id);
            model.addAttribute("pedido", pedido);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        
        return "pedido/visualizar";
    }
}
