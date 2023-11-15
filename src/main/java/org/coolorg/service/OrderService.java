package org.coolorg.service;

import lombok.RequiredArgsConstructor;
import org.coolorg.database.CustomerRepository;
import org.coolorg.database.OrderRepository;
import org.coolorg.model.Order;
import org.coolorg.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    /**
     * Получить заказ по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор заказа.
     * @return {@link Optional}, содержащий заказ, если найден, или пустой {@link Optional}, если не найден.
     */
    public Optional<Order> getOrderById(int id) {
       return orderRepository.getOrderById(id);
    }

    /**
     * Получить список заказов, связанных с конкретным клиентом.
     *
     * @param customerId Уникальный идентификатор клиента.
     * @return Список заказов, связанных с клиентом.
     */
    public List<Order> getOrdersByCustomer(int customerId) throws IllegalArgumentException {
       if(customerService.getById(customerId).isEmpty()){
           throw new IllegalArgumentException("Customer does not exist");
       }
       return  orderRepository.getOrdersByCustomer(customerId);
     }

    /**
     * Рассчитать общую стоимость всех заказов для конкретного клиента.
     *
     * @param customerId Уникальный идентификатор клиента.
     * @return Общая стоимость всех заказов для клиента.o
     */
    public double getTotalPriceForCustomer(int customerId) {
        List<Order> orders = getOrdersByCustomer(customerId);
        return orders.stream().mapToDouble(order -> {
            Optional<Product> product = productService.getById(order.getProductId());
            return product.map(Product::getPrice).orElse(0.0);
        }).sum();
    }

    /**
     * Создать новый заказ и добавить его в репозиторий.
     *
     * @param order Заказ, который нужно создать и добавить.
     * @throws IllegalArgumentException Если заказ уже существует в репозитории.
     */
    public void createOrder(Order order) throws IllegalArgumentException {
        if(orderRepository.getOrderById(order.getId()).isPresent()){
            throw new IllegalArgumentException("Order already exists");
        }
        orderRepository.addOrder(order);
    }

    /**
     * Удалить заказ по его уникальному идентификатору.
     *
     * @param orderId Уникальный идентификатор заказа, который нужно удалить.
     * @throws IllegalArgumentException Если заказ с указанным идентификатором не существует в репозитории.
     */
    public void removeOrder(int orderId) {
        if(orderRepository.getOrderById(orderId).isEmpty()){
            throw new IllegalArgumentException("Örder does not exist");
        }
        orderRepository.removeOrder(orderId);

    }

}
