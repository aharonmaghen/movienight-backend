package com.movienight.movienightbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movienight.movienightbackend.models.RoomMovie;
import com.movienight.movienightbackend.models.compositeKeys.RoomMovieId;

public interface RoomMovieRepository extends JpaRepository<RoomMovie, RoomMovieId> {
  
}