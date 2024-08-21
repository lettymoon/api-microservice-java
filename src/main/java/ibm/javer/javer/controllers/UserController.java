package ibm.javer.javer.controllers;

import ibm.javer.javer.domain.user.User;
import ibm.javer.javer.repositories.UserRepository;
import ibm.javer.javer.services.UserService;
import ibm.javer.javer.dtos.ResponseDTO;
import ibm.javer.javer.dtos.UserAllDataResponseDTO;
import ibm.javer.javer.dtos.UserRequestDTO;
import ibm.javer.javer.dtos.UserResponseDTO;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService usersService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers(){
        ResponseDTO<List<UserResponseDTO>> response = usersService.getAllUsers();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<UserAllDataResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO userRequest){
        ResponseDTO<UserAllDataResponseDTO> response = usersService.createUser(userRequest);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable String id){
        ResponseDTO<UserAllDataResponseDTO> response = usersService.getUserById(id);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String id){
        ResponseDTO<UserAllDataResponseDTO> response = usersService.deleteUser(id);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserRequestDTO data){
        ResponseDTO<UserAllDataResponseDTO> response = usersService.updateUser(data);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/{cpf}/score-credito")
    public ResponseEntity<ResponseDTO> updateScore(@PathVariable String cpf){
        ResponseDTO<Double> response = usersService.updateScore(cpf);

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
