package ibm.javer.javer.controller;

import ibm.javer.javer.entity.Usuario;
import ibm.javer.javer.repository.UsuariosRepository;
import ibm.javer.javer.service.dto.UsuarioRequestDTO;
import ibm.javer.javer.service.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuariosController {
    @Autowired
    private UsuariosRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveUser(@RequestBody UsuarioRequestDTO data){
        Usuario userData = new Usuario(data);
        repository.save(userData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UsuarioResponseDTO> gettAll(){

        List<UsuarioResponseDTO> usuarioList = repository.findAll().stream().map(UsuarioResponseDTO::new).toList();
        return usuarioList;
    }
}
