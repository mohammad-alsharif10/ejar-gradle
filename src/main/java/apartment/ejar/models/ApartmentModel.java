package apartment.ejar.models;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel
public class ApartmentModel extends Model {

    private Float area;

    private Float latitude;

    private Float longitude;

    private Integer maximumRentersNumber;

    private String address;

    private Integer monthlyRent;

    private Integer status;

    private Integer locationId;

    private Integer brokerId;

      private String createdBy;

      private Date createdOn;

}
