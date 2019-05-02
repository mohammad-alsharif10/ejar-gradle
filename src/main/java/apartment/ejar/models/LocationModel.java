package apartment.ejar.models;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class LocationModel extends Model {

    private String arabicName;

    private String englishName;
}
