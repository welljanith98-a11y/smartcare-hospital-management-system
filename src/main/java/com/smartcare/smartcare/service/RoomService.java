package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Room;
import com.smartcare.smartcare.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Integer id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Integer id, Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found with id: " + id));

        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setRoomCategory(roomDetails.getRoomCategory());
        room.setAvailabilityStatus(roomDetails.getAvailabilityStatus());

        return roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found with id: " + id);
        }

        roomRepository.deleteById(id);
    }
}