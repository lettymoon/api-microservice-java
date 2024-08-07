package ibm.javer.javer.services;

import ibm.javer.javer.domain.user.User;
import ibm.javer.javer.exceptions.UserExistException;
import ibm.javer.javer.repositories.UserRepository;
import ibm.javer.javer.dtos.ResponseDTO;
import ibm.javer.javer.dtos.UserAllDataResponseDTO;
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
    UserRepository userRepository;

    public ResponseDTO<UserAllDataResponseDTO> createUser(UserRequestDTO userRequest) {
        Optional<User> existUser = userRepository.findByCpf(userRequest.getCpf());

        if(existUser.isPresent()){
            throw new UserExistException();
        }

        User newUser = new User(userRequest);
        userRepository.save(newUser);
        return new ResponseDTO<>("Usuário cadastrado com sucesso", null, HttpStatus.OK);
    }

    public ResponseDTO<UserAllDataResponseDTO> getUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não encontrado", null, HttpStatus.NOT_FOUND);
        }

        return new ResponseDTO<>("Usuário encontrado com sucesso", new UserAllDataResponseDTO(user.get()), HttpStatus.OK);
    }

    public ResponseDTO<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> usuariosList = userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
        if (usuariosList.isEmpty()){
            return new ResponseDTO<>("Nenhum usuário encontrado", usuariosList, HttpStatus.OK);
        }
        return new ResponseDTO<>("Usuários encontrados com sucesso", usuariosList, HttpStatus.OK);
    }

    public ResponseDTO<UserAllDataResponseDTO> deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
        return new ResponseDTO<>("Usuário deletado com sucesso :3", new UserAllDataResponseDTO(user.get()), HttpStatus.OK);
    }

    public ResponseDTO<UserAllDataResponseDTO> updateUser(UserRequestDTO data) {
        Optional<User> oldUser = userRepository.findByCpf(data.getCpf());

        if (!oldUser.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        User newUser = new User(data);
        newUser.setId(oldUser.get().getId());
        newUser.setSaldo_cc(oldUser.get().getSaldo_cc());
        newUser.setScore_credito(oldUser.get().getScore_credito());

        userRepository.save(newUser);

        return new ResponseDTO<>("Usuário atualizado com sucesso", new UserAllDataResponseDTO(newUser), HttpStatus.OK);

    }

    public ResponseDTO<Double> updateScore(String cpf){
        Optional<User> user = userRepository.findByCpf(cpf);

        if (!user.isPresent()) {
            return new ResponseDTO<>("Usuário não existe", null, HttpStatus.NOT_FOUND);
        }

        user.get().setScore_credito(user.get().getSaldo_cc() * 0.1);
        userRepository.save(user.get());

        return new ResponseDTO<>("Score atual", user.get().getScore_credito(), HttpStatus.OK);
    }
}
