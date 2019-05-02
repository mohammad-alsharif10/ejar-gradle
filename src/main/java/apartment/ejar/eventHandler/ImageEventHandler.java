package apartment.ejar.eventHandler;

import apartment.ejar.entities.Image;
import apartment.ejar.util.Auditing;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
@RepositoryEventHandler(Image.class)
public class ImageEventHandler {

    private Auditing auditing;

    @HandleBeforeCreate
    public void handleBeforeCreate(Image image) {
        auditing.createdBy(image);
        auditing.createdOn(image);
    }
}
