package apartment.ejar.util;

import apartment.ejar.controllers.jwtController.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class Uploads {


    public String saveProfileImage(MultipartFile file, String name) throws IOException {
        new File(Constants.profilePath + name).mkdir();
        String imagePath = Constants.profilePath + name + "\\" + file.getOriginalFilename();
        File convertFile = new File(imagePath);
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return imagePath;
    }

    public String uploadApartmentImages(MultipartFile file, String name, Integer apartmentId) throws IOException {
        new File(Constants.apartmentsPath + name + apartmentId.toString()).mkdir();
        String imagePath = Constants.apartmentsPath + name + apartmentId.toString() + "\\" +
                LoginResponse.getLoginResponse().getBroker().getUsername()
                + file.getOriginalFilename();
        File convertFile = new File(imagePath);
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return imagePath;
    }

//      String extension = FilenameUtils.getExtension(file.getOriginalFilename());
}
