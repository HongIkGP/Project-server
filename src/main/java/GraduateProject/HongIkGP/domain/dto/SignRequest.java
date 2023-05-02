package GraduateProject.HongIkGP.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignRequest {

    private Long id;

    private String email;

    private String password;

    private String name;

}
