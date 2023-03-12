package api.pojo_classes.tech_global;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CreateAStudent {

    private String firstName;
    private String lastName;
    private String email;
    private String dob;
}
