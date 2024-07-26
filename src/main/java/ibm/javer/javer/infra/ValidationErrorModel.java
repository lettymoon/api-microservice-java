package ibm.javer.javer.infra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorModel {
    private String field;
    private String message;

    public ValidationErrorModel(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
