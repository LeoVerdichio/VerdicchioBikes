/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Servicios;

import com.example.demo.Entidades.Cliente;
import com.example.demo.Entidades.Pedido;
import com.example.demo.Repositorios.ClienteRepository;
import com.example.demo.Repositorios.PedidoRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    ClienteRepository clienteRepositorio;

    @Autowired
    public ClienteServicio(ClienteRepository clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;

    }

//    public void Guardar(Cliente cliente, Pedido pedido, String descripcion, String bicicleta, Integer valor) {
//        
//        pedido.setCliente(cliente);
//        pedido.setDescripcion(descripcion);
//        pedido.setActivo(Boolean.TRUE);
//        pedido.setBicicleta(bicicleta);
//        pedido.setValor(valor);
//        
//        
//        
//
//        
//        pedidoServicio.Guardar(pedido);
//        
//        
//        
//       
//        
//
//    }
    public void guardar(Cliente cliente) {
        LocalDate date = LocalDate.now();
        cliente.setFecha(date);
        clienteRepositorio.save(cliente);

    }

//    public void Guardarpedido(Cliente cliente, Pedido pedido, String descripcion, String bicicleta, Integer valor) {
//
//        pedido.setDescripcion(descripcion);
//        pedido.setActivo(Boolean.TRUE);
//        pedido.setBicicleta(bicicleta);
//        pedido.setValor(valor);
//        pedido.setCliente(cliente);
//
//        
//
//        pedidoRepositorio.save(pedido);
//        
//
//    }
    public void validaciones(Cliente cliente) throws Exception {
        if (cliente.getNombre().isEmpty()) {
            throw new Exception("Nombre vacio");
        }
        
        if (cliente.getApellido().isEmpty()) {
            throw new Exception("Apellido vacio");
        }

        if (cliente.getDni().length()!= 8 || cliente.getDni().isEmpty() ) {
            throw new Exception("Dni Incorrecto o vacio");
        }
        
         if (cliente.getTelefono().isEmpty() || cliente.getTelefono().length()!= 10) {
            throw new Exception("Telefono vacio o incorrecto");
        }
    }

    public Cliente buscarcliente(String id) throws Exception {
        Optional<Cliente> option = clienteRepositorio.findById(id);
        if (option.isPresent()) {
            Cliente cliente = option.get();

            return cliente;
        } else {

            throw new Exception("usuario no encontrado");

        }

    }

    public List<Cliente> listarclientes() {

        return clienteRepositorio.findAll();

    }

    public List<Cliente> buscarclientedni(String dni) {
        return clienteRepositorio.buscarclientedni(dni);
    }

//    public Cliente traercliente(String id){
//        Cliente cliente=clienteRepositorio.traercliente(id);
//      return cliente;
//        
//        
//    }
}
