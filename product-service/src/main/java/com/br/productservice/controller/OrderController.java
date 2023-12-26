package com.br.productservice.controller;

import com.br.productservice.dto.OrderDTO;
import com.br.productservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "Order controller"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Create order"
    )
    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update order"
    )
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable UUID id, @RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(
            summary = "List all order"
    )
    @GetMapping
    public ResponseEntity<Page<OrderDTO>> list(Pageable pageable){
        Page<OrderDTO> listProducts = orderService.listOrders(pageable);
        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @Operation(
            summary = "Get order by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable UUID id){
        OrderDTO order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete order by id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        orderService.deleteOrders(id);
        return ResponseEntity.ok().build();
    }
}
