package com.example.delicias.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.delicias.Repository.PedidoRepository;
import com.example.delicias.models.Pedidos;


@Service
public class PedidosService {

    @Autowired
    private PedidoRepository pedidosRepository;

    public List<Pedidos> findAll() {
        return pedidosRepository.findAll();
    }

    public Pedidos findById(Long id) {
        return pedidosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
    }

    public Pedidos save(Pedidos pedido) {
        return pedidosRepository.save(pedido);
    }

    public Pedidos actualizarPedido(Pedidos pedido) {
        if (pedido.getProducto() == null) {
            throw new IllegalArgumentException("Producto no puede ser nulo");
        }
        if (pedido.getUsuario() == null) {
            throw new IllegalArgumentException("Usuario no puede ser nulo");
        }
        return pedidosRepository.save(pedido);
    }

    public void deleteById(Long id) {
        pedidosRepository.deleteById(id);
    }
}
