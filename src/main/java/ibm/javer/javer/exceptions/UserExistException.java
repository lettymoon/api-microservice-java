package ibm.javer.javer.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(){
        super("Usuário já existe!");
    }

    public UserExistException(String message){
        super(message);
    }
}
