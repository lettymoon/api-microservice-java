package ibm.javer.javer.services;

import ibm.javer.javer.domain.user.User;
import ibm.javer.javer.repositories.UserRepository;
import ibm.javer.javer.dtos.ResponseDTO;
import ibm.javer.javer.dtos.UsuarioByIdResponseDTO;
import ibm.javer.javer.dtos.UsuarioRequestDTO;
import ibm.javer.javer.dtos.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository usuariosRepository;

    // TODO: tem que ser uma transaction
    public ResponseDTO<UsuarioResponseDTO> createUser(UsuarioRequestDTO data) {
        Optional<User> existingUser = usuariosRepository.findByCpf(data.getCpf());

        if (existingUser.isPresent()) {
            return new ResponseDTO<>("Usuário já existe", null, HttpStatus.CONFLICT);
        }

        //TODO: Tratar erro de bancos
        User newUser = new User(data);
        usuariosRepository.save(newUser);
        return new ResponseDTO<>("Usuário cadastrado com sucesso", new UsuarioResponseDTO(newUser), HttpStatus.CREATED);
    }

    public ResponseDTO<UsuarioByIdResponseDTO> getUser(String id) {
        Optional<User> user = usuariosRepository.findById(id);

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
        Optional<User> user = usuariosRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        usuariosRepository.deleteById(id);
        return new ResponseDTO<>("Usuário deletado com sucesso :3", new UsuarioResponseDTO(user.get()), HttpStatus.OK);
    }

    public ResponseDTO<UsuarioResponseDTO> updateUser(UsuarioRequestDTO data) {
        Optional<User> oldUser = usuariosRepository.findByCpf(data.getCpf());

        if (!oldUser.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        User newUser = new User(data);
        newUser.setId(oldUser.get().getId());
        newUser.setSaldo_cc(oldUser.get().getSaldo_cc());
        newUser.setScore_credito(oldUser.get().getScore_credito());

        usuariosRepository.save(newUser);

        return new ResponseDTO<>("Usuário atualizado com sucesso", new UsuarioResponseDTO(newUser), HttpStatus.OK);

    }

    public ResponseDTO<Float> updateScore(String cpf){
        Optional<User> user = usuariosRepository.findByCpf(cpf);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        user.get().setScore_credito(user.get().getSaldo_cc() * (float)0.1);
        usuariosRepository.save(user.get());

        return new ResponseDTO<>("Score atual", user.get().getScore_credito(), HttpStatus.OK);
    }
}
