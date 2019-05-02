package apartment.ejar.models;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel
class page {
    private Integer size;
    private Integer totalElements;
    private Integer totalPages;
    private Integer number;

}
