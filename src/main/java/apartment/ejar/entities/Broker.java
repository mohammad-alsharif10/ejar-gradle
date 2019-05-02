package apartment.ejar.entities;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brokers")
@ApiModel
public class Broker extends BaseEntity {

    @Id
    @Column(name = "BROKER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brokerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "IMAGE_ID")
    private Integer imageId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "broker_role",
            joinColumns = {@JoinColumn(name = "BROKER_ID", referencedColumnName = "BROKER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")})
    private List<Role> roles;
}
