package org.coolorg;

import org.coolorg.database.CustomerRepository;
import org.coolorg.database.OrderRepository;
import org.coolorg.database.ProductRepository;
import org.coolorg.model.Customer;
import org.coolorg.model.Order;
import org.coolorg.service.CustomerService;
import org.coolorg.service.OrderService;
import org.coolorg.service.ProductService;

public class Application {

    public static void main(String[] args) {

        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository, customerService, productService);

        customerService.createCustomer(new Customer(24, "Vlad"));

        System.out.println(customerService.getById(24));

        Order order1 = new Order(10, 24, 2);
        Order order2 = new Order(99, 24, 4);
        orderService.createOrder(order1);
        orderService.createOrder(order2);
        System.out.println(orderService.getTotalPriceForCustomer(24));

    }
}
