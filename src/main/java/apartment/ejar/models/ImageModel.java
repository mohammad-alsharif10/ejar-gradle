package apartment.ejar.models;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class ImageModel extends Model {

    private String path;

    private Integer apartmentId;
}
