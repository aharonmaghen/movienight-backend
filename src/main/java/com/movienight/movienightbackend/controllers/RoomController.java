package com.movienight.movienightbackend.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movienight.movienightbackend.models.Room;
import com.movienight.movienightbackend.models.User;
import com.movienight.movienightbackend.models.RoomUser;
import com.movienight.movienightbackend.repositories.RoomRepository;
import com.movienight.movienightbackend.repositories.UserRepository;
import com.movienight.movienightbackend.repositories.RoomUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoomUserRepository roomUserRepository;
  @PersistenceContext
  private EntityManager entityManager;

  @GetMapping
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  @PostMapping
  @Transactional
  public Room createRoom(@RequestBody Room room) {
    roomRepository.save(room);
    entityManager.refresh(room);
    return room;
  }

  // @PostMapping("/users")
  // @Transactional
  // public RoomUser addUserToRoom(@RequestBody RoomUser roomUser) {
  // roomUserRepository.save(roomUser);
  // entityManager.refresh(roomUser);
  // return roomUser;
  // }

  // @PostMapping("/users")
  // @Transactional
  // public RoomUser addUserToRoom(@RequestBody Map<String, Long> requestBody) {
  // Long roomId = requestBody.get("roomId");
  // System.out.println(roomId);
  // Long userId = requestBody.get("userId");
  // System.out.println(userId);

  // RoomUserId roomUserId = new RoomUserId();
  // roomUserId.setRoomId(roomId);
  // roomUserId.setUserId(userId);

  // RoomUser roomUser = new RoomUser();
  // roomUser.setRoomUserId(roomUserId);

  // // Save the RoomUser entity
  // roomUser = roomUserRepository.save(roomUser);

  // // Optionally refresh the entity if necessary (though usually not needed for
  // // simple saving)
  // entityManager.refresh(roomUser);

  // return roomUser;
  // }

  /**
   * Endpoint to create a new RoomUser (i.e., add a user to a room)
   *
   * @param roomId the ID of the room
   * @param userId the ID of the user
   * @return ResponseEntity with the created RoomUser object
   */
  @PostMapping("/{roomId}/users/{userId}")
  public ResponseEntity<RoomUser> addUserToRoom(@PathVariable Long roomId, @PathVariable Long userId) {
    System.out.println("path variables: ");
    System.out.println("roomId = "+roomId);
    System.out.println("userId = "+userId);
    // Fetch the Room and User entities from the database
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new RuntimeException("Room not found with id " + roomId));
    System.out.println(room);

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    System.out.println(user);

    // Create a new RoomUser entity
    RoomUser roomUser = new RoomUser(room, user, LocalDateTime.now(), LocalDateTime.now());

    // Save the RoomUser entity
    roomUser = roomUserRepository.save(roomUser);

    // Optionally refresh the entity if necessary (though usually not needed for
    // simple saving)
    entityManager.refresh(roomUser);

    // Return the created RoomUser in the response
    return ResponseEntity.ok(roomUser);
  }
}