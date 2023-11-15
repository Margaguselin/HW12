package org.coolorg.service;

import lombok.RequiredArgsConstructor;
import org.coolorg.database.CustomerRepository;
import org.coolorg.model.Customer;

import java.util.Optional;
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    /**
     * Получить клиента по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор клиента.
     * @return {@link Optional}, содержащий клиента, если найден, или пустой {@link Optional}, если не найден.
     */
    public Optional<Customer> getById(int id) {
        return repository.getCustomerById(id);
    }

    /**
     * Создать нового клиента и добавить его в репозиторий.
     *
     * @param customer Клиент, которого нужно создать и добавить.
     * @throws IllegalArgumentException Если клиент с таким идентификатором уже существует в репозитории.
     */
    public void createCustomer(Customer customer)throws IllegalArgumentException {
        if (repository.getCustomerById(customer.getId()).isPresent()){
            throw new IllegalArgumentException("Customer with the same id already exists");
        }
        repository.addCustomer(customer);
    }

    /**
     * Удалить клиента по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор клиента, которого нужно удалить.
     * @throws IllegalArgumentException Если клиент с указанным идентификатором не существует в репозитории.
     */
    public void removeCustomer(int id) throws IllegalArgumentException {
        if (repository.getCustomerById(id).isEmpty()){
            throw new IllegalArgumentException("Customer does not exist");
        }
        repository.removeCustomer(id);
    }
}
