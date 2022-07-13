/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.demo.Repositorios;

import com.example.demo.Entidades.Cliente;
import com.example.demo.Entidades.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>  {

      @Query("SELECT c FROM Cliente c WHERE c.dni = :dnitxt")
   public List<Cliente> buscarclientedni(@Param("dnitxt")String dni);  
}
