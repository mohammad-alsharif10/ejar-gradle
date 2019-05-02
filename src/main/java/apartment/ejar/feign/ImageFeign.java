package apartment.ejar.feign;


import apartment.ejar.models.ImageModel;
import apartment.ejar.models.PageAndSize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "image-services", url = "http://localhost:8080/images")
public interface ImageFeign {


    @RequestMapping(method = RequestMethod.POST, path = "/")
    ImageModel insert(@RequestHeader("Authorization") String jwt, @RequestBody ImageModel image);

    @RequestMapping(method = RequestMethod.GET, path = "/")
    Resource<PageAndSize> getPage(@RequestHeader("Authorization") String jwt, @RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @RequestMapping(method = RequestMethod.GET, path = "/")
    Resources<ImageModel> getImages(@RequestHeader("Authorization") String jwt, @RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Resource<ImageModel> getImageById(@RequestHeader("Authorization") String jwt, @PathVariable("id") Integer id);
}
