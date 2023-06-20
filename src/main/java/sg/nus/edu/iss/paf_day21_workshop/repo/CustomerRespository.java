package sg.nus.edu.iss.paf_day21_workshop.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.paf_day21_workshop.model.Customer;

@Repository
public class CustomerRespository {

    @Autowired
    private JdbcTemplate template;

    // 1st function
    private final String findAllSql = "select * from customer";

    // 2nd function
    private final String findAllLimitOffsetSql = "select * from customer limit ? offset ?";

    // 3rd function
    private final String findSqlById = "select * from customer where id = ?";
    // ? is a placeholder for a value to be replaced

    // 1st function
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        final SqlRowSet rs = template.queryForRowSet(findAllSql);

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
        // returns a "read-only" copy of the list
        // it ensures that the data will not be modified during serialisation of data
        // (i.e. when sending it over to the client)
    }

    // 2nd function
    public List<Customer> getAllWithLimitOffset(int limit, int offset) {
        List<Customer> customerList = new ArrayList<>();

        SqlRowSet rs = template.queryForRowSet(findAllLimitOffsetSql, limit, offset);
        // limit and offset will replace the ? in the query statement

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customerList.add(customer);
        }

        return Collections.unmodifiableList(customerList);
    }

    public Customer getCustomerById(int id) {
        Customer customer = new Customer();

        customer = template.queryForObject(findSqlById,
                BeanPropertyRowMapper
                        // maps a row to an object (i.e. casting it to a Customer object)
                        .newInstance(
                        // creates a new instance of the bean property row mapper
                                Customer.class),
                                // it will assist to map the row to the compiled class (i.e. Customer.class)
                id);

        return customer;
    }
}
