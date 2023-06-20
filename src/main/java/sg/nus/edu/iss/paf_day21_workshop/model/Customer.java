package sg.nus.edu.iss.paf_day21_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, and @Setter on all non-final fields, and @RequiredArgsConstructor!
@NoArgsConstructor // Generates constructors that take no arguments
@AllArgsConstructor // Generates constructors that takes one argument for every field
public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;

}
