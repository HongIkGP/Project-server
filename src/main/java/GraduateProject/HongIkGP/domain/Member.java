package GraduateProject.HongIkGP.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name ="member_id")
    private Long id;

    @Column(name ="user_id")
    private String userId;

    private String password;

    private String name;

    private String role;
}
