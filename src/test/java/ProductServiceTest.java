import org.coolorg.database.ProductRepository;
import org.coolorg.model.Product;
import org.coolorg.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Test
    void testCanNotCreateProductThatAlreadyExists() {
        assertNotNull(productRepository);
        when(productRepository.getProductById(1)).thenReturn(Optional.of(new Product(1, "product1", 2)));
        ProductService productService = new ProductService(productRepository);
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(new Product(1, "product1", 2)));
    }
    @Test
    void testCanCreateProduct() {
        assertNotNull(productRepository);
        when(productRepository.getProductById(1)).thenReturn(Optional.empty());
        ProductService productService = new ProductService(productRepository);
        Product product = new Product(1, "product1", 2);
        productService.createProduct(product);
        verify(productRepository, times(1)).addProduct(product);


    }
}

