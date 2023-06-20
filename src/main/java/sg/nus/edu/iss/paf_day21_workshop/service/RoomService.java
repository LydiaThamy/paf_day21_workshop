package sg.nus.edu.iss.paf_day21_workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.paf_day21_workshop.model.Room;
import sg.nus.edu.iss.paf_day21_workshop.repo.RoomRepository;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepo;

    public int countRooms() {
        return roomRepo.countRooms();
    }

    public List<Room> getAllRooms() {
        return roomRepo.getAllRooms();
    }

    public Room getRoomById(int id) {
        return roomRepo.getRoomById(id);
    }

    public Boolean saveNewRoom(Room room) {
        return roomRepo.saveNewRoom(room);
    }

    public int updatePriceOfRoom(Room room) {
        return roomRepo.updatePriceOfRoom(room);
    }

    public int deleteById(int id) {
        return roomRepo.deleteById(id);
    }
}
