package apartment.ejar.models;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@ApiModel
public class Paging<entity extends Model> {

    private ArrayList<entity> entities;

    private apartment.ejar.models.page page;
}
