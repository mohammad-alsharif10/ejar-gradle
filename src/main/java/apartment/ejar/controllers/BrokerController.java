package apartment.ejar.controllers;


import apartment.ejar.entities.Broker;
import apartment.ejar.feign.BrokerFeign;
import apartment.ejar.models.Paging;
import apartment.ejar.services.BrokerService;
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
@RequestMapping("/broker")
public class BrokerController {

    private BrokerFeign brokerFeign;
    private BrokerService brokerService;

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = "application/json")
    public Paging brokers(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return new Paging<>(new ArrayList<>(brokerFeign.getBrokers(Constants.jwt, page, size).getContent()),
                brokerFeign.getPage(Constants.jwt, page, size).getContent().getPage());
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Broker save(@RequestBody Broker broker) {
        return brokerFeign.insert(broker);
    }

    @RequestMapping(value = "/upload-image", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object>
    uploadApartmentImages(@RequestParam(required = true, value = "image") MultipartFile images)
            throws IOException {
        brokerService.uploadApartmentImages(images);
        return ResponseEntity.status(HttpStatus.OK).body("images uploaded");
    }

}
