package com.br.productservice.controller;

import com.br.productservice.builder.OrderBuilder;
import com.br.productservice.builder.ProductBuilder;
import com.br.productservice.dto.AuthenticationDto;
import com.br.productservice.dto.LoginResponseDto;
import com.br.productservice.dto.OrderDTO;
import com.br.productservice.dto.ProductDTO;
import com.br.productservice.repository.ProductRepository;
import com.br.productservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql");
    @Container
    @ServiceConnection
    static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.13.0-rc.3-management"));

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    private String token;
    private HttpHeaders headers;
    private OrderDTO orderDTOSaved;

    @BeforeEach
    void setUp() {
        ResponseEntity<LoginResponseDto> loginResponseDtoResponseEntity = restTemplate.postForEntity("/auth/login", new AuthenticationDto("admin@adm.com", "1234"), LoginResponseDto.class);
        token = Objects.requireNonNull(loginResponseDtoResponseEntity.getBody()).token();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
    }

    @Test
    @Order(1)
    void shouldSaveOrder() {
        ResponseEntity<OrderDTO> response = restTemplate.exchange("/orders", HttpMethod.POST, new HttpEntity<>(OrderBuilder.getOneWithValidProducts(), headers), OrderDTO.class);
        OrderDTO responseBody = response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        orderDTOSaved = responseBody;
        assertNotNull(orderDTOSaved);
        assertNotNull(orderDTOSaved.getProducts());
        assertNotNull(orderDTOSaved.getProducts().stream().findFirst());
        assertTrue(orderDTOSaved.getProducts().stream().findFirst().isPresent());
        ProductDTO productDTO = orderDTOSaved.getProducts().stream().findFirst().get();
        ProductDTO productExpected = ProductBuilder.getOneDTOBrigadeiro();
        assertEquals(productExpected.getName(), productDTO.getName());
        assertEquals(productExpected.getId(), productDTO.getId());
        assertNotNull(productDTO);
        assertNotNull(orderDTOSaved.getTotalPrice());
        assertEquals(BigInteger.valueOf(200), orderDTOSaved.getTotalPrice());
    }

    @Test
    @Order(2)
    void shouldGetOrderById() {
        ResponseEntity<OrderDTO> response = restTemplate.exchange("/orders/293d9f2e-dd2f-43d0-9efc-7b5fc468d14d", HttpMethod.GET, new HttpEntity<>(headers), OrderDTO.class);
        OrderDTO responseBody = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
        assertNotNull(responseBody.getProducts());
        assertNotNull(responseBody.getProducts().stream().findFirst());
        assertTrue(responseBody.getProducts().stream().findFirst().isPresent());
        ProductDTO productDTO = responseBody.getProducts().stream().findFirst().get();
        ProductDTO productExpected = ProductBuilder.getOneDTOBrigadeiro();
        assertEquals(productExpected.getName(), productDTO.getName());
        assertEquals(productExpected.getId(), productDTO.getId());
        assertNotNull(productDTO);
        assertNotNull(responseBody.getTotalPrice());
        assertEquals(BigInteger.valueOf(1200), responseBody.getTotalPrice());
    }

    @Test
    @Order(3)
    void shouldNotGetOrderWithInvalidId() {
        ResponseEntity<OrderDTO> response = restTemplate.exchange("/orders/"+ UUID.randomUUID(), HttpMethod.GET, new HttpEntity<>(headers), OrderDTO.class);
        OrderDTO responseBody = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(responseBody);
    }

}