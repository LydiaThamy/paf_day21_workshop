package sg.nus.edu.iss.paf_day21_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private Integer id;
    private String roomType;
    private Integer price;

}
