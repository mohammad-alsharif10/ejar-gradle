package apartment.ejar.feign;


import apartment.ejar.models.LocationModel;
import apartment.ejar.models.PageAndSize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "location-services", url = "http://localhost:8080/locations")
public interface LocationFeign {

    @RequestMapping(method = RequestMethod.POST, path = "/")
    LocationModel insert(@RequestBody LocationModel location);

    @RequestMapping(method = RequestMethod.GET, path = "/")
    Resource<PageAndSize> getPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @RequestMapping(method = RequestMethod.GET, path = "/")
    Resources<LocationModel> getLocations(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Resource<LocationModel> getLocationById(@PathVariable("id") Integer id);


}
