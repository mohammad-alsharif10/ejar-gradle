package apartment.ejar.services;

import apartment.ejar.controllers.jwtController.LoginResponse;
import apartment.ejar.feign.ImageFeign;
import apartment.ejar.models.ImageModel;
import apartment.ejar.security.EjarDetailsService;
import apartment.ejar.util.Constants;
import apartment.ejar.util.Uploads;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class BrokerService {


    private Uploads uploads;
    private ImageFeign imageFeign;
    private EjarDetailsService ejarDetailsService;

    public void uploadApartmentImages(MultipartFile image) throws IOException {
        String userName = ejarDetailsService.loadUserByJwtTokenAndDatabase(LoginResponse.getLoginResponse().getJwt()).get().getUsername();
        String imagePath = uploads.saveProfileImage(image, userName);
        ImageModel savedImage = new ImageModel();
        savedImage.setPath(imagePath);
        imageFeign.insert(Constants.jwt, savedImage);
    }
}
