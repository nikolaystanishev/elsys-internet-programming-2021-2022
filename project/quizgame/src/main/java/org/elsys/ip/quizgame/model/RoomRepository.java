package org.elsys.ip.quizgame.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends CrudRepository<Room, UUID> {
    Optional<Room> findByName(String name);
}
