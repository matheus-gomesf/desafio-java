package com.br.productservice.controller;

import com.br.productservice.dto.OrderDTO;
import com.br.productservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable UUID id, @RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> list(Pageable pageable){
        Page<OrderDTO> listProducts = orderService.listOrders(pageable);
        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable UUID id){
        OrderDTO order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        orderService.deleteOrders(id);
        return ResponseEntity.ok().build();
    }
}
