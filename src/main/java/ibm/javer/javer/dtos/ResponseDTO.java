package ibm.javer.javer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO<L> {
    String message;
    L data;
    @JsonIgnore
    HttpStatus status;
}
