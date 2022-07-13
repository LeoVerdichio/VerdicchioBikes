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
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

    ClienteServicio clienteServicio;
    PedidoServicio pedidoServicio;

    @Autowired
    public ClienteControlador(ClienteServicio clienteServicio, PedidoServicio pedidoServicio) {
        this.clienteServicio = clienteServicio;
        this.pedidoServicio = pedidoServicio;
    }

    @GetMapping("/crear")
    public String crearcliente(ModelMap model) {

        model.addAttribute("cliente", new Cliente());

        return "cliente/crearcliente";
    }

    @PostMapping("/crear")
    public String procesarcliente(@ModelAttribute Cliente cliente,ModelMap model) {
        try {
            clienteServicio.validaciones(cliente);
              clienteServicio.guardar(cliente);
//            Pedido pedido = new Pedido();
//            clienteServicio.Guardar(cliente, pedido, descripcion, bicicleta, valor);
            //clienteServicio.Guardarpedido(cliente, pedido, descripcion, bicicleta, valor);

        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            return "cliente/crearcliente";
        }

        return "redirect:/cliente/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String editarid(@PathVariable String id,ModelMap model){
        
        try {
            Cliente cliente = clienteServicio.buscarcliente(id);
            model.addAttribute("cliente", cliente);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }
        
        return "cliente/crearcliente";
    }
    
    @PostMapping("/guardarpedido")
    public String guardarpedido(@ModelAttribute Cliente cliente, @RequestParam(value = "descripcion") String descripcion, @RequestParam(value = "bicicleta") String bicicleta, @RequestParam(value = "valor") Integer valor, ModelMap model) {
        try {
//         clienteServicio.validaciones(cliente);
            Pedido pedido = new Pedido();
            pedidoServicio.Guardarpedido(cliente, pedido, descripcion, bicicleta, valor);

        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }

        return "redirect:/pedido/listarpedidos";
    }

    @GetMapping("/buscar/{id}")
    public String buscarcliente(@PathVariable String id, ModelMap model) {

        try {
            Cliente cliente = clienteServicio.buscarcliente(id);
            model.addAttribute("cliente", cliente);
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
        }

        return "cliente/guardarpedido";
    }

    @GetMapping("/listar")
    public String listarclientes(ModelMap model,@RequestParam(value = "txtbuscar",required = false) String buscar) {
        if (buscar!=null) {
           List<Cliente> clientes=clienteServicio.buscarclientedni(buscar);
           model.addAttribute("clientes", clientes);
        }else{
        List<Cliente> clientes = clienteServicio.listarclientes();
        model.addAttribute("clientes", clientes);
        }

        return "cliente/listarclientes";
    }

    @GetMapping("/pedidos/{id}")
    public String pedidoscliente(@PathVariable("id") String id, ModelMap model) {
        System.out.println(id);
        List<Pedido> pedidos = pedidoServicio.listarpedidodecliente(id);
        model.addAttribute("pedidos", pedidos);
        return "pedido/listarpedidos";
    }

}
