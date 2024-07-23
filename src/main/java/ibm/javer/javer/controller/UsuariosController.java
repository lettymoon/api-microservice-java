package ibm.javer.javer.controller;

import ibm.javer.javer.repository.UsuariosRepository;
import ibm.javer.javer.service.UsuariosService;
import ibm.javer.javer.service.dto.ResponseDTO;
import ibm.javer.javer.service.dto.UsuarioByIdResponseDTO;
import ibm.javer.javer.service.dto.UsuarioRequestDTO;
import ibm.javer.javer.service.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private UsuariosService usuariosService;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable String id){
        ResponseDTO<UsuarioByIdResponseDTO> response = usuariosService.getUser(id);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String id){
        ResponseDTO<UsuarioResponseDTO> response = usuariosService.deleteUser(id);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UsuarioRequestDTO data){
        ResponseDTO<UsuarioResponseDTO> response = usuariosService.updateUser(data);

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
