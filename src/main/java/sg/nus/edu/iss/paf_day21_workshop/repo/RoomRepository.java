package sg.nus.edu.iss.paf_day21_workshop.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.paf_day21_workshop.model.Room;

@Repository
public class RoomRepository {
    
    @Autowired
    private JdbcTemplate template;

    private String countSql = "select count(*) from room"; // COUNT() function returns the number of rows that matches a specified criterion
    private String findAllSql = "select * from room";
    private String findByIdSql = "select * from room where id = ?";
    private String deleteByIdSql = "delete from room where id = ?";
    private String insertSql = "insert into room (room_type, price) values (?, ?)";
    private String updateSql = "update room set price = ? where id = ?";

    public int countRooms() {
        Integer result = template.queryForObject(countSql, Integer.class); // returns an Integer number
        if (result == null) result = 0;

        return result;
    }

    public List<Room> getAllRooms() {
        return template.query(findAllSql, BeanPropertyRowMapper.newInstance(Room.class));
        // otherwise, can use queryForRowSet
    }

    public Room getRoomById(int id) {
        return template.queryForObject(findByIdSql, BeanPropertyRowMapper.newInstance(Room.class), id);
    }

    public boolean saveNewRoom(Room room) {

        Boolean saved = false;
        // it will return a false if insertion fails to execute

        template.execute(insertSql,
            new PreparedStatementCallback<Boolean>() {

                @Override
                @Nullable
                public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    ps.setString(1, room.getRoomType());
                    ps.setInt(2, room.getPrice());
                    return ps.execute();
                }
                
            });
            // a statement to show that the action has been executed

        return saved;
    }

    public int updatePriceOfRoom(Room room) {

        int updated = 0;
        updated = template.update(updateSql, room.getPrice(), room.getId());
        // returns the number of rows affected

        return updated;
    }

    public int deleteById(int id) {

        int deleted = 0;

        deleted = template.update(deleteByIdSql, id);
        // returns the number of rows affected

        return deleted;
    }
}
