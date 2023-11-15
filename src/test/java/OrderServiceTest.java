import org.coolorg.database.CustomerRepository;
import org.coolorg.database.OrderRepository;
import org.coolorg.database.ProductRepository;
import org.coolorg.model.Customer;
import org.coolorg.model.Order;
import org.coolorg.model.Product;
import org.coolorg.service.CustomerService;
import org.coolorg.service.OrderService;
import org.coolorg.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
@Mock
    CustomerRepository customerRepository;
@Mock
    ProductRepository productRepository;
    @Test
    void testGetTotalPriceByCustomer() {


        assertNotNull(orderRepository);
        assertNotNull(customerRepository);
        when(customerRepository.getCustomerById(1)).thenReturn(Optional.of(new Customer(1, "Test")));
        when(orderRepository.getOrdersByCustomer(1)).thenReturn(List.of(new Order(1, 1, 1), new Order(2, 1, 2), new Order(3, 1, 3)));
when(productRepository.getProductById(1)).thenReturn(Optional.of(new Product(1, "Test1", 5)));
        when(productRepository.getProductById(2)).thenReturn(Optional.of(new Product(2, "Test2", 2)));
        when(productRepository.getProductById(3)).thenReturn(Optional.of(new Product(3, "Test3", 5)));
        CustomerService customerService = new CustomerService(customerRepository);
        ProductService productService = new ProductService(productRepository);
        OrderService orderService = new OrderService(orderRepository, customerService, productService);

        assertEquals(12, orderService.getTotalPriceForCustomer(1));
    }
    @Test
    void testCanGetOrderByCustomer(){
        assertNotNull(orderRepository);
        when(orderRepository.getOrdersByCustomer(1)).thenReturn(List.of(new Order(1, 1,1)));
        when(customerRepository.getCustomerById(1)).thenReturn(Optional.of(new Customer(1, "Test")));

        CustomerService customerService = new CustomerService(customerRepository);
        ProductService productService = new ProductService(productRepository);
OrderService orderService = new OrderService(orderRepository, customerService, productService);
List<Order> orders = orderService.getOrdersByCustomer(1);

assertNotNull(orders);
assertEquals(1, orders.size());




    }


}
