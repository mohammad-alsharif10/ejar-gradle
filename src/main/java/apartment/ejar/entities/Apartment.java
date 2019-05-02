package apartment.ejar.entities;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apartments")
@ApiModel
public class Apartment extends BaseEntity {

    @Id
    @Column(name = "APARTMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "AREA")
    private Float area;

    @Column(name = "LATITUDE")
    private Float latitude;

    @Column(name = "LONGITUDE")
    private Float longitude;

    @Column(name = "MAX_RENTER")
    private Integer maximumRentersNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MONTHLY_RENT")
    private Integer monthlyRent;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "LOCATION_ID")
    private Integer locationId;

    @Column(name = "BROKER_ID")
    private Integer brokerId;


}
