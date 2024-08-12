package ibm.javer.javer.exceptions;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(){
        super("Usuário não encontrado!");
    }

    public UserNotExistException(String message){
        super(message);
    }
}