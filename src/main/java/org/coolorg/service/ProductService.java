package org.coolorg.service;

import lombok.RequiredArgsConstructor;
import org.coolorg.database.ProductRepository;
import org.coolorg.model.Product;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Получить продукт по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор продукта.
     * @return {@link Optional}, содержащий продукт, если найден, или пустой {@link Optional}, если не найден.
     */
    public Optional<Product> getById(final int id) {

        return productRepository.getProductById(id);
    }

    /**
     * Создать новый продукт и добавить его в репозиторий.
     *
     * @param product Продукт, который нужно создать и добавить.
     * @throws IllegalArgumentException Если продукт с таким идентификатором уже существует в репозитории.
     */
    public void createProduct(final Product product)throws IllegalArgumentException {
        if (productRepository.getProductById(product.getId()).isPresent()){
            throw new IllegalArgumentException("Product already exists");
        }
        productRepository.addProduct(product);

    }

    /**
     * Удалить продукт по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор продукта, который нужно удалить.
     * @throws IllegalArgumentException Если продукт с указанным идентификатором не существует в репозитории.
     */
    public void removeProduct(final int id) throws  IllegalArgumentException {
        if(productRepository.getProductById(id).isEmpty()){
            throw new IllegalArgumentException("Product does not exist");
        }
        productRepository.removeProduct(id);

    }
}
