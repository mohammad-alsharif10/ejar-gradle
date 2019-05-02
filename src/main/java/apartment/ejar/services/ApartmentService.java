package apartment.ejar.services;

import apartment.ejar.feign.ApartmentFeign;
import apartment.ejar.feign.ImageFeign;
import apartment.ejar.models.ApartmentModel;
import apartment.ejar.models.ImageModel;
import apartment.ejar.util.Constants;
import apartment.ejar.util.Uploads;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ApartmentService {

    private Uploads uploads;
    private ApartmentFeign apartmentFeign;
    private ImageFeign imageFeign;


    public void uploadApartmentImages(MultipartFile[] images, Integer apartmentId) {
        List<MultipartFile> imagesList = new ArrayList<>(Arrays.asList(images));
        ApartmentModel apartmentModel = apartmentFeign.getApartmentById(Constants.jwt, apartmentId).getContent();
        imagesList.parallelStream().forEach(image -> {
            try {
                String imagePath = uploads.uploadApartmentImages
                        (image, apartmentModel.getCreatedBy(), apartmentId);
                ImageModel savedImage = new ImageModel();
                savedImage.setApartmentId(apartmentId);
                savedImage.setPath(imagePath);
                imageFeign.insert(Constants.jwt, savedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
