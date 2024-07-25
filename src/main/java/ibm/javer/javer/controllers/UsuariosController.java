package ibm.javer.javer.controllers;

import ibm.javer.javer.repositories.UserRepository;
import ibm.javer.javer.services.UserService;
import ibm.javer.javer.dtos.ResponseDTO;
import ibm.javer.javer.dtos.UsuarioByIdResponseDTO;
import ibm.javer.javer.dtos.UsuarioRequestDTO;
import ibm.javer.javer.dtos.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuariosController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService usuariosService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UsuarioResponseDTO>>> gettAllUsers(){
        ResponseDTO<List<UsuarioResponseDTO>> response = usuariosService.getAllUsers();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UsuarioRequestDTO data){
        ResponseDTO<UsuarioResponseDTO> response = usuariosService.createUser(data);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable String id){
        ResponseDTO<UsuarioByIdResponseDTO> response = usuariosService.getUser(id);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String id){
        ResponseDTO<UsuarioResponseDTO> response = usuariosService.deleteUser(id);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UsuarioRequestDTO data){
        ResponseDTO<UsuarioResponseDTO> response = usuariosService.updateUser(data);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/{cpf}/score-credito")
    public ResponseEntity<ResponseDTO> updateScore(@PathVariable String cpf){
        ResponseDTO<Float> response = usuariosService.updateScore(cpf);

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
