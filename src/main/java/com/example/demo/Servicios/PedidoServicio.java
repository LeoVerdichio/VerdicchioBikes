/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.demo.Servicios;

import com.example.demo.Entidades.Cliente;
import com.example.demo.Entidades.Pedido;
import com.example.demo.Repositorios.PedidoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServicio {

    PedidoRepository pedidoRepositorio;
    ClienteServicio clienteServicio;

    @Autowired
     public PedidoServicio(PedidoRepository pedidoRepositorio, ClienteServicio clienteServicio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.clienteServicio = clienteServicio;
    }
    
    
    
    public void Guardar(Pedido pedido){
      pedidoRepositorio.save(pedido);
        
    }

    
    
    
    public Pedido buscarporid(String id) throws Exception{
       Optional<Pedido> option = pedidoRepositorio.findById(id);
        if (option.isPresent()) {
            Pedido pedido = option.get();
            return pedido;
        } else {
            throw new Exception("pedido no encontrado");
        }
    }
    
    
    public List<Pedido> listarpedidosalta(){
        return pedidoRepositorio.pedidosestado(Boolean.TRUE);
    }
    
    public List<Pedido> listarpedidosbaja(){
        return pedidoRepositorio.pedidosestado(Boolean.FALSE);
    }
    
    public List<Pedido> listarpedidodecliente(String id){
        return pedidoRepositorio.pedidoscliente(id);
        
        
    }
    
   
    public void baja(String id) throws Exception{
        Optional<Pedido> option=pedidoRepositorio.findById(id);
        if (option.isPresent()) {
            Pedido pedido = option.get();
            pedido.setActivo(Boolean.FALSE);
            Guardar(pedido);
        } else {
            throw new Exception("usuario no encontrado");
        }
    }
    
    public void alta(String id) throws Exception{
        Optional<Pedido> option=pedidoRepositorio.findById(id);
        if (option.isPresent()) {
            Pedido pedido = option.get();
            pedido.setActivo(Boolean.TRUE);
            Guardar(pedido);
        } else {
            throw new Exception("usuario no encontrado");
        }
    }
        
    public Cliente traercliente(String id) throws Exception{
        String idcliente=pedidoRepositorio.traercliente(id);
        Cliente cliente=clienteServicio.buscarcliente(idcliente);      
        return cliente;
    }
    
     public void Guardarpedido(Cliente cliente, Pedido pedido, String descripcion, String bicicleta, Integer valor) {

        pedido.setDescripcion(descripcion);
        pedido.setActivo(Boolean.TRUE);
        pedido.setBicicleta(bicicleta);
        pedido.setValor(valor);
        pedido.setCliente(cliente);
        LocalDate date = LocalDate.now();
        pedido.setFechapedido(date);

        

        pedidoRepositorio.save(pedido);
     }
    
     public String resumen(){
         String resumen=pedidoRepositorio.resumendia();
         return resumen;
     }
}
