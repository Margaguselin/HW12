import org.coolorg.database.CustomerRepository;
import org.coolorg.model.Customer;
import org.coolorg.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Test
    void testCanGetCustomerById() {
        assertNotNull(customerRepository);
        when(customerRepository.getCustomerById(1)).thenReturn(Optional.of(new Customer(1, "Test")));
        CustomerService customerService = new CustomerService(customerRepository);
        Customer customer = customerService.getById(1).get();
        assertEquals(1, customer.getId());
        assertEquals("Test", customer.getName());
    }

    @Test
    void testCanNotAddCustomerThatAlreadyExists() {
        assertNotNull(customerRepository);
        when(customerRepository.getCustomerById(1)).thenReturn(Optional.of(new Customer(1, "Test")));
        CustomerService customerService = new CustomerService(customerRepository);
        assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(new Customer(1, "Test")));
    }

    @Test
    void testCanNotDeleteCustomerThatDoesNotExist() {
        assertNotNull(customerRepository);
        when(customerRepository.getCustomerById(1)).thenReturn(Optional.empty());
        CustomerService customerService = new CustomerService(customerRepository);
        assertThrows(IllegalArgumentException.class, () -> customerService.removeCustomer(1));
    }

    @Test
    void testCanDeleteCustomerById() {
        assertNotNull(customerRepository);
        when(customerRepository.getCustomerById(1)).thenReturn(Optional.of(new Customer(1, "Test")));
        CustomerService customerService = new CustomerService(customerRepository);
        customerService.removeCustomer(1);
        verify(customerRepository, times(1)).removeCustomer(1);
    }

    @Test
    void testCanAddCustomer() {
        assertNotNull(customerRepository);
        when(customerRepository.getCustomerById(1)).thenReturn(Optional.empty());
        CustomerService customerService = new CustomerService(customerRepository);
        Customer customer = new Customer(1, "Test");
        customerService.createCustomer(customer);
        verify(customerRepository, times(1)).addCustomer(customer);
    }
}
