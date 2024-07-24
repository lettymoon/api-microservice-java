package ibm.javer.javer.service;

import ibm.javer.javer.entity.Usuario;
import ibm.javer.javer.repository.UsuariosRepository;
import ibm.javer.javer.service.dto.ResponseDTO;
import ibm.javer.javer.service.dto.UsuarioByIdResponseDTO;
import ibm.javer.javer.service.dto.UsuarioRequestDTO;
import ibm.javer.javer.service.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    @Autowired
    UsuariosRepository usuariosRepository;

    // TODO: tem que ser uma transaction
    public ResponseDTO<UsuarioResponseDTO> createUser(UsuarioRequestDTO data) {
        Optional<Usuario> existingUser = usuariosRepository.findByCpf(data.getCpf());

        if (existingUser.isPresent()) {
            return new ResponseDTO<>("Usuário já existe", null, HttpStatus.CONFLICT);
        }

        //TODO: Tratar erro de bancos
        Usuario newUser = new Usuario(data);
        usuariosRepository.save(newUser);
        return new ResponseDTO<>("Usuário cadastrado com sucesso", new UsuarioResponseDTO(newUser), HttpStatus.CREATED);
    }

    public ResponseDTO<UsuarioByIdResponseDTO> getUser(String id) {
        Optional<Usuario> user = usuariosRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não encontrado", null, HttpStatus.NOT_FOUND);
        }

        return new ResponseDTO<>("Usuário encontrado com sucesso", new UsuarioByIdResponseDTO(user.get()), HttpStatus.OK);
    }

    public ResponseDTO<List<UsuarioResponseDTO>> getAllUsers(){
        List<UsuarioResponseDTO> usuariosList = usuariosRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
        if (usuariosList.isEmpty()){
            return new ResponseDTO<>("Nenhum usuário encontrado", usuariosList, HttpStatus.OK);
        }
        return new ResponseDTO<>("Usuários encontrados com sucesso", usuariosList, HttpStatus.OK);
    }

    public ResponseDTO<UsuarioResponseDTO> deleteUser(String id) {
        Optional<Usuario> user = usuariosRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        usuariosRepository.deleteById(id);
        return new ResponseDTO<>("Usuário deletado com sucesso :3", new UsuarioResponseDTO(user.get()), HttpStatus.OK);
    }

    public ResponseDTO<UsuarioResponseDTO> updateUser(UsuarioRequestDTO data) {
        Optional<Usuario> oldUser = usuariosRepository.findByCpf(data.getCpf());

        if (!oldUser.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        Usuario newUser = new Usuario(data);
        newUser.setId(oldUser.get().getId());
        newUser.setSaldo_cc(oldUser.get().getSaldo_cc());
        newUser.setScore_credito(oldUser.get().getScore_credito());

        usuariosRepository.save(newUser);

        return new ResponseDTO<>("Usuário atualizado com sucesso", new UsuarioResponseDTO(newUser), HttpStatus.OK);

    }

    public ResponseDTO<Float> updateScore(String cpf){
        Optional<Usuario> user = usuariosRepository.findByCpf(cpf);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        user.get().setScore_credito(user.get().getSaldo_cc() * (float)0.1);
        usuariosRepository.save(user.get());

        return new ResponseDTO<>("Score atual", user.get().getScore_credito(), HttpStatus.OK);
    }
}
