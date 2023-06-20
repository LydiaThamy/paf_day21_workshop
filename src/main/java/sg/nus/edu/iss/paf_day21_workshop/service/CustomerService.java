package sg.nus.edu.iss.paf_day21_workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.paf_day21_workshop.model.Customer;
import sg.nus.edu.iss.paf_day21_workshop.repo.CustomerRespository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRespository customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.getAllCustomers();
    }

    public List<Customer> getAllWithLimitOffset(int limit, int offset) {
        return customerRepo.getAllWithLimitOffset(limit, offset);
    }

    public Customer getCustomerById(int id) {
        return customerRepo.getCustomerById(id);
    }
}
