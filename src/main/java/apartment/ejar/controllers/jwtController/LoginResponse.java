package apartment.ejar.controllers.jwtController;


import apartment.ejar.entities.Broker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class LoginResponse {
    private static LoginResponse loginResponse;
    private String jwt;
    private String accessType = "Bearer ";
    private Boolean status;
    private String statusMessage;
    private Broker broker;

    private LoginResponse() {
    }

    public static LoginResponse getLoginResponse() {
        if (loginResponse == null) {
            synchronized (LoginResponse.class) {
                if (loginResponse == null) {
                    loginResponse = new LoginResponse();
                }
            }
        }
        return loginResponse;
    }

}
