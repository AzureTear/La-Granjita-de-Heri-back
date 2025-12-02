package com.example.La_Granjita_de_Heri.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.La_Granjita_de_Heri.model.Carrito;
import com.example.La_Granjita_de_Heri.model.CarritoItem;
import com.example.La_Granjita_de_Heri.model.Producto;
import com.example.La_Granjita_de_Heri.repository.CarritoItemRepository;
import com.example.La_Granjita_de_Heri.repository.CarritoRepository;
import com.example.La_Granjita_de_Heri.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoItemRepository itemRepository;

    public Carrito crearCarrito() {
        return carritoRepository.save(new Carrito());
    }

    public Carrito obtenerCarrito(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito agregarProducto(Integer carritoId, Integer productoId) {
        Carrito carrito = obtenerCarrito(carritoId);
        Producto producto = productoRepository.findById(productoId).orElse(null);

        if (carrito == null || producto == null) return null;

        // Ver si ya existe el producto en el carrito
        for (CarritoItem item : carrito.getItems()) {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(item.getCantidad() + 1);
                itemRepository.save(item);
                return carritoRepository.save(carrito);
            }
        }

        // Si no existe, agregar item nuevo
        CarritoItem nuevo = new CarritoItem();
        nuevo.setProducto(producto);
        nuevo.setCantidad(1);
        itemRepository.save(nuevo);

        carrito.getItems().add(nuevo);
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCantidad(Integer carritoId, Integer itemId, Integer cantidad) {
        Carrito carrito = obtenerCarrito(carritoId);
        if (carrito == null) return null;

        CarritoItem item = itemRepository.findById(itemId).orElse(null);
        if (item == null) return null;

        item.setCantidad(cantidad);
        itemRepository.save(item);

        return carritoRepository.save(carrito);
    }

    public Carrito eliminarItem(Integer carritoId, Integer itemId) {
        Carrito carrito = obtenerCarrito(carritoId);
        if (carrito == null) return null;

        carrito.getItems().removeIf(i -> i.getId().equals(itemId));
        itemRepository.deleteById(itemId);

        return carritoRepository.save(carrito);
    }

    public void vaciarCarrito(Integer id) {
        carritoRepository.deleteById(id);
    }
}

