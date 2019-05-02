package apartment.ejar.controllers;

import apartment.ejar.feign.ApartmentFeign;
import apartment.ejar.models.ApartmentModel;
import apartment.ejar.models.Paging;
import apartment.ejar.services.ApartmentService;
import apartment.ejar.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    private ApartmentFeign apartmentFeign;
    private ApartmentService apartmentService;

    @RequestMapping(path = "/all", produces = "application/hal+json", method = RequestMethod.GET)
    public Paging apartments(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return new Paging<>(new ArrayList<>(apartmentFeign.getApartments(Constants.jwt, page, size).getContent()),
                apartmentFeign.getPage(Constants.jwt, page, size).getContent().getPage());
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ApartmentModel save(@RequestBody ApartmentModel apartment) {
        return apartmentFeign.insert(Constants.jwt, apartment);
    }

    @RequestMapping(value = "/upload-images/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object>
    uploadApartmentImages(@RequestParam(required = true, value = "images") MultipartFile[] images, @PathVariable("id") Integer id)
            throws IOException {
        apartmentService.uploadApartmentImages(images, id);
        return ResponseEntity.status(HttpStatus.OK).body("images uploaded");
    }
}
