package apartment.ejar.controllers;


import apartment.ejar.feign.LocationFeign;
import apartment.ejar.models.LocationModel;
import apartment.ejar.models.Paging;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/location")
public class LocationController {

    private LocationFeign locationFeign;

    public LocationController(LocationFeign locationFeign) {
        this.locationFeign = locationFeign;
    }


    @RequestMapping(path = "/all", produces = "application/hal+json", method = RequestMethod.GET)
    public Paging locations(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return new Paging<>(new ArrayList<>(locationFeign.getLocations(page, size).getContent()),
                locationFeign.getPage(page, size).getContent().getPage());
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST, produces = "application/json")
    public LocationModel save(@RequestBody LocationModel location) {
        return locationFeign.insert(location);
    }


}

