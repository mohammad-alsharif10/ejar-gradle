package apartment.ejar.models;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class BrokerModel extends Model {

    private String username;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private Integer imageId;

}
