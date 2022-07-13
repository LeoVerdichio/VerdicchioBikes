/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.demo.Repositorios;

import com.example.demo.Entidades.Pedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

  @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :id")
    public List<Pedido> pedidoscliente(@Param("id")String id);  
    
     @Query("SELECT p FROM Pedido p WHERE p.activo = :estado")
     public List<Pedido> pedidosestado(@Param("estado")Boolean activo);
     
     @Query("SELECT p.cliente.id FROM Pedido p WHERE p.id = :idc")
     public String traercliente(@Param("idc")String id);
     
     @Query("SELECT SUM(p.valor) FROM Pedido p WHERE p.fechapedido = current_date()")
     public String resumendia();
}
