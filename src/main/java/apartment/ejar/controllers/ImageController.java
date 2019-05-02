package apartment.ejar.controllers;


import apartment.ejar.feign.ImageFeign;
import apartment.ejar.models.ImageModel;
import apartment.ejar.models.Paging;
import apartment.ejar.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private ImageFeign imageFeign;

    @RequestMapping(path = "/all", produces = "application/hal+json", method = RequestMethod.GET)
    public Paging images(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return new Paging<>(new ArrayList<>(imageFeign.getImages(Constants.jwt, page, size).getContent()),
                imageFeign.getPage(Constants.jwt, page, size).getContent().getPage());
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST, produces = "application/json")
    public ImageModel save(@RequestBody ImageModel image) {
        return imageFeign.insert(Constants.jwt, image);
    }
}
