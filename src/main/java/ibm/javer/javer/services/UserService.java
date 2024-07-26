package ibm.javer.javer.services;

import ibm.javer.javer.domain.user.User;
import ibm.javer.javer.repositories.UserRepository;
import ibm.javer.javer.dtos.ResponseDTO;
import ibm.javer.javer.dtos.UserByIdResponseDTO;
import ibm.javer.javer.dtos.UserRequestDTO;
import ibm.javer.javer.dtos.UserResponseDTO;
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
    public ResponseDTO<UserResponseDTO> createUser(UserRequestDTO data) {
        Optional<User> existingUser = usuariosRepository.findByCpf(data.getCpf());

        if (existingUser.isPresent()) {
            return new ResponseDTO<>("Usuário já existe", null, HttpStatus.CONFLICT);
        }

        //TODO: Tratar erro de bancos
        User newUser = new User(data);
        usuariosRepository.save(newUser);
        return new ResponseDTO<>("Usuário cadastrado com sucesso", new UserResponseDTO(newUser), HttpStatus.CREATED);
    }

    public ResponseDTO<UserByIdResponseDTO> getUser(String id) {
        Optional<User> user = usuariosRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não encontrado", null, HttpStatus.NOT_FOUND);
        }

        return new ResponseDTO<>("Usuário encontrado com sucesso", new UserByIdResponseDTO(user.get()), HttpStatus.OK);
    }

    public ResponseDTO<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> usuariosList = usuariosRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
        if (usuariosList.isEmpty()){
            return new ResponseDTO<>("Nenhum usuário encontrado", usuariosList, HttpStatus.OK);
        }
        return new ResponseDTO<>("Usuários encontrados com sucesso", usuariosList, HttpStatus.OK);
    }

    public ResponseDTO<UserResponseDTO> deleteUser(String id) {
        Optional<User> user = usuariosRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        usuariosRepository.deleteById(id);
        return new ResponseDTO<>("Usuário deletado com sucesso :3", new UserResponseDTO(user.get()), HttpStatus.OK);
    }

    public ResponseDTO<UserResponseDTO> updateUser(UserRequestDTO data) {
        Optional<User> oldUser = usuariosRepository.findByCpf(data.getCpf());

        if (!oldUser.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        User newUser = new User(data);
        newUser.setId(oldUser.get().getId());
        newUser.setSaldo_cc(oldUser.get().getSaldo_cc());
        newUser.setScore_credito(oldUser.get().getScore_credito());

        usuariosRepository.save(newUser);

        return new ResponseDTO<>("Usuário atualizado com sucesso", new UserResponseDTO(newUser), HttpStatus.OK);

    }

    public ResponseDTO<Double> updateScore(String cpf){
        Optional<User> user = usuariosRepository.findByCpf(cpf);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        user.get().setScore_credito(user.get().getSaldo_cc() * 0.1);
        usuariosRepository.save(user.get());

        return new ResponseDTO<>("Score atual", user.get().getScore_credito(), HttpStatus.OK);
    }
}
