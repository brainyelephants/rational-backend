package ru.brainyelephants.rational.backend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.brainyelephants.rational.backend.exceptions.BadRequestException;
import ru.brainyelephants.rational.backend.exceptions.NotFoundException;
import ru.brainyelephants.rational.backend.persistence.UserRepository;
import ru.brainyelephants.rational.model.dto.PageDTO;
import ru.brainyelephants.rational.model.dto.UserDTO;
import ru.brainyelephants.rational.model.entities.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public UserDTO postUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() != null) throw new BadRequestException();
        User user = userRepository.save(modelMapper.map(userDTO, User.class));
        return modelMapper.map(user, UserDTO.class);
    }

    @GetMapping(path = "")
    public PageDTO<UserDTO> getUsers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "code") String sortBy,
            @RequestParam(name = "reverseOrder", defaultValue = "false") boolean reverseOrder
    ) {
        Sort sorting = (reverseOrder) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pagination = PageRequest.of(pageNumber, pageSize, sorting);
        Page<User> page = userRepository.findAll(pagination);
        return new PageDTO<>(page, UserDTO.class);
    }

    @GetMapping(path = "/{id}")
    public UserDTO getUser(
            @PathVariable(name = "id") long id
    ) {
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(user, UserDTO.class);
    }

    @PutMapping(path = "/{id}")
    public UserDTO updateUser(
            @PathVariable(name = "id") long id,
            @RequestBody UserDTO userDTO
    ) {
        if (userDTO.getId() != id) throw new BadRequestException();
        userRepository.findById(id).orElseThrow(NotFoundException::new);
        User user = userRepository.save(modelMapper.map(userDTO, User.class));
        return modelMapper.map(user, UserDTO.class);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(
            @PathVariable(name = "id") long id
    ) {
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        userRepository.deleteById(user.getId());
    }
}
