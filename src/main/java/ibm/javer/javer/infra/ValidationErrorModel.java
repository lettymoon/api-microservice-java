package ibm.javer.javer.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationErrorModel {
    private String field;
    private String message;
}
