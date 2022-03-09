package org.elsys.ip.quizgame.service;

import org.elsys.ip.quizgame.error.RoomAlreadyExistsException;
import org.elsys.ip.quizgame.error.RoomNotExistsException;
import org.elsys.ip.quizgame.model.Room;
import org.elsys.ip.quizgame.model.RoomRepository;
import org.elsys.ip.quizgame.model.User;
import org.elsys.ip.quizgame.model.UserRepository;
import org.elsys.ip.quizgame.web.model.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class RoomService {

    @Autowired
    public RoomRepository roomRepository;

    @Autowired
    public UserRepository userRepository;

    public RoomDto createRoom(RoomDto roomDto) throws RoomAlreadyExistsException {
        if (roomRepository.findByName(roomDto.getName()).isPresent()) {
            throw new RoomAlreadyExistsException("Room with name " + roomDto.getName() + " already exists.");
        }

        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(currentUser.getUsername());

        Room room = new Room();
        room.setName(roomDto.getName());
        room.setAdmin(user);

        roomRepository.save(room);
        roomDto.setId(String.valueOf(room.getId()));
        return roomDto;
    }

    public RoomDto getRoomById(String id) throws RoomNotExistsException {
        Optional<Room> roomOptional = roomRepository.findById(UUID.fromString(id));
        Room room = roomOptional.orElseThrow(() -> new RoomNotExistsException(MessageFormat.format("Room with id: ''{0}'' doesn't exists", id)));
        return convertRoom(room);
    }

    public List<RoomDto> getRooms() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
                .map(this::convertRoom).collect(Collectors.toList());
        // el -> convertRoom(el) === this::convertRoom
    }

    private RoomDto convertRoom(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(String.valueOf(room.getId()));
        roomDto.setName(room.getName());
        return roomDto;
    }
}
