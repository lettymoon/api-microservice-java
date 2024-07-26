package ibm.javer.javer.services;

import ibm.javer.javer.domain.user.User;
import ibm.javer.javer.repositories.UserRepository;
import ibm.javer.javer.dtos.ResponseDTO;
import ibm.javer.javer.dtos.UserAllDataResponseDTO;
import ibm.javer.javer.dtos.UserRequestDTO;
import ibm.javer.javer.dtos.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetUserById_UserExists() {
        String userId = UUID.randomUUID().toString();
        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        ResponseDTO<UserAllDataResponseDTO> response = userService.getUserById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getData());
        assertEquals(userId, response.getData().getId());
    }

    @Test
    public void testGetUserById_UserDoesNotExist() {
        String userId = UUID.randomUUID().toString();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseDTO<UserAllDataResponseDTO> response = userService.getUserById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
        assertNull(response.getData());
    }

    @Test
    public void testCreateUser_UserAlreadyExists() {
        String cpf = "12345678910";
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setCpf(cpf);
        when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(new User()));

        ResponseDTO<UserAllDataResponseDTO> response = userService.createUser(userRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatus());
        assertNull(response.getData());
    }

    @Test
    public void testCreateUser_NewUser() {
        String cpf = "12345678910";
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setCpf(cpf);
        when(userRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        User newUser = new User(userRequest);
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        ResponseDTO<UserAllDataResponseDTO> response = userService.createUser(userRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertNotNull(response.getData());
        assertEquals(cpf, response.getData().getCpf());
    }

    @Test
    public void testDeleteUser_UserExists() {
        String userId = UUID.randomUUID().toString();
        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        ResponseDTO<UserAllDataResponseDTO> response = userService.deleteUser(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getData());
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUser_UserDoesNotExist() {
        String userId = UUID.randomUUID().toString();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseDTO<UserAllDataResponseDTO> response = userService.deleteUser(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
        assertNull(response.getData());
        verify(userRepository, never()).deleteById(userId);
    }

    @Test
    public void testGetAllUsers_NoUsersFound() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseDTO<List<UserResponseDTO>> response = userService.getAllUsers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getData());
        assertTrue(response.getData().isEmpty());
    }

    @Test
    public void testGetAllUsers_UsersFound() {
        String userId = UUID.randomUUID().toString();
        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findAll()).thenReturn(List.of(mockUser));

        ResponseDTO<List<UserResponseDTO>> response = userService.getAllUsers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getData());
        assertFalse(response.getData().isEmpty());
    }

    @Test
    public void testUpdateUser_UserExists() {
        String cpf = "12345678910";
        String userId = UUID.randomUUID().toString();
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setCpf(cpf);

        User oldUser = new User();
        oldUser.setId(userId);
        oldUser.setCpf(cpf);
        oldUser.setSaldo_cc(1000.0);
        oldUser.setScore_credito(100.0);

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(any(User.class))).thenReturn(oldUser);

        ResponseDTO<UserAllDataResponseDTO> response = userService.updateUser(userRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getData());
        assertEquals(userId, response.getData().getId());
        assertEquals(cpf, response.getData().getCpf());
    }

    @Test
    public void testUpdateUser_UserDoesNotExist() {
        String cpf = "12345678910";
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setCpf(cpf);

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        ResponseDTO<UserAllDataResponseDTO> response = userService.updateUser(userRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
        assertNull(response.getData());
    }

    @Test
    public void testUpdateScore_UserExists() {
        String cpf = "12345678910";
        User user = new User();
        user.setCpf(cpf);
        user.setSaldo_cc(1000.0);

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseDTO<Double> response = userService.updateScore(cpf);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getData());
        assertEquals(100.0, response.getData());
    }

    @Test
    public void testUpdateScore_UserDoesNotExist() {
        String cpf = "12345678910";

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        ResponseDTO<Double> response = userService.updateScore(cpf);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
        assertNull(response.getData());
    }
}
