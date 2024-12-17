package com.movienight.movienightbackend.models;

import java.time.LocalDateTime;

import com.movienight.movienightbackend.models.compositeKeys.RoomUserId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Represents a relationship between a room and a user.
 */
@Entity
@Table(name = "room_users")
public class RoomUser {
  @EmbeddedId
  private RoomUserId roomUserId;

  @ManyToOne
  @MapsId("roomId")
  @JoinColumn(name = "room_id")
  private Room room;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;

  /**
   * Default constructor for RoomUser required by JPA.
   */
  public RoomUser() {
  }

  /**
   * Constructs a new RoomUser instance.
   *
   * @param room      The room associated with the room-user relationship.
   * @param user      The user associated with the room-user relationship.
   * @param updatedAt The last updated timestamp of the room-user relationship.
   * @param createdAt The creation timestamp of the room-user relationship.
   */
  public RoomUser(Room room, User user, LocalDateTime updatedAt, LocalDateTime createdAt) {
    this.roomUserId = new RoomUserId(room.getId(), user.getId());
    this.room = room;
    this.user = user;
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
  }

  public RoomUserId getRoomUserId() {
    return roomUserId;
  }

  public void setRoomUserId(RoomUserId roomUserId) {
    this.roomUserId = roomUserId;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "RoomUser{roomUserId=" + this.roomUserId +
        ", updatedAt=" + this.updatedAt +
        ", createdAt=" + this.createdAt + "}";
  }

  public boolean equals(Object roomUser) {
    if (roomUser == this) {
      return true;
    } else if (!(roomUser instanceof RoomUser)) {
      return false;
    } else {
      RoomUser that = (RoomUser) roomUser;
      return this.roomUserId.equals(that.getRoomUserId()) &&
          this.updatedAt.equals(that.getUpdatedAt()) &&
          this.createdAt.equals(that.getCreatedAt());
    }
  }

  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= this.roomUserId.hashCode();
    h$ *= 1000003;
    h$ ^= this.updatedAt.hashCode();
    h$ *= 1000003;
    h$ ^= this.createdAt.hashCode();
    return h$;
  }
}