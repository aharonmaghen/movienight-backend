package com.movienight.movienightbackend.models.compositeKeys;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

/**
 * Represents a composite key of room_id and movie_id for the RoomMovie entity.
 */
@Embeddable
public class RoomMovieId implements Serializable {
  private Long roomId;
  private Long movieId;

  /**
   * Default constructor for RoomMovieId required by JPA.
   */
  public RoomMovieId() {
  }

  public Long getRoomId() {
    return roomId;
  }

  public Long getMovieId() {
    return movieId;
  }

  public String toString() {
    return "RoomMovieId{roomId=" + this.roomId +
        ", movieId=" + this.movieId + "}";
  }

  public boolean equals(Object room) {
    if (room == this) {
      return true;
    } else if (!(room instanceof RoomMovieId)) {
      return false;
    } else {
      RoomMovieId that = (RoomMovieId) room;
      return this.roomId.equals(that.getRoomId()) &&
          this.movieId.equals(that.getMovieId());
    }
  }

  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= this.roomId.hashCode();
    h$ *= 1000003;
    h$ ^= this.movieId.hashCode();
    return h$;
  }
}
