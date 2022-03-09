package org.elsys.ip.quizgame.web;

import org.elsys.ip.quizgame.error.RoomAlreadyExistsException;
import org.elsys.ip.quizgame.error.RoomNotExistsException;
import org.elsys.ip.quizgame.service.RoomService;
import org.elsys.ip.quizgame.web.model.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public String getRooms(WebRequest request, Model model) {
        model.addAttribute("newRoom", new RoomDto());
        model.addAttribute("rooms", roomService.getRooms());
        return "rooms";
    }

    @PostMapping("/room")
    public String createRoom(@ModelAttribute("room") @Valid RoomDto roomDto,
                             BindingResult bindingResult, Model model) {
        try {
            roomService.createRoom(roomDto);
        } catch (RoomAlreadyExistsException e) {
            return "error";
        }
        return "room";
    }

    @GetMapping("/room")
    public String getRoom(WebRequest request, Model model, @RequestParam("id") String roomId) {
        try {
            RoomDto room = roomService.getRoomById(roomId);
            model.addAttribute("room", room);
            return "room";
        } catch (RoomNotExistsException e) {
            return "error";
        }
    }
}
