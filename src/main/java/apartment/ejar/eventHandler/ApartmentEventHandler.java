package apartment.ejar.eventHandler;

import apartment.ejar.entities.Apartment;
import apartment.ejar.repositories.BrokerRepository;
import apartment.ejar.repositories.LocationRepository;
import apartment.ejar.util.Auditing;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@RepositoryEventHandler(Apartment.class)
public class ApartmentEventHandler {

    private Auditing auditing;
    private BrokerRepository brokerRepository;
    private LocationRepository locationRepository;


    @HandleBeforeCreate
    public void handleBeforeCreate(Apartment apartment) {
        auditing.createdBy(apartment);
        auditing.createdOn(apartment);
        apartment.setLocationId(locationRepository.findByArabicNameIn(apartment.getAddress()).getLocationId());
        apartment.setBrokerId(brokerRepository.findByUsername(apartment.getCreatedBy()).get().getBrokerId());
    }

    @HandleAfterCreate
    public void handleAfterCreate(Apartment apartment) {

    }
}
