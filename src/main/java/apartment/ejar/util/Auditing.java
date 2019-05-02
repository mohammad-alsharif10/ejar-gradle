package apartment.ejar.util;

import apartment.ejar.controllers.jwtController.LoginResponse;
import apartment.ejar.entities.BaseEntity;
import apartment.ejar.security.EjarDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Service
public class Auditing {

    private EjarDetailsService ejarDetailsService;

    public void createdOn(BaseEntity baseEntity) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(  localDateTime.getHour());
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        baseEntity.setCreatedOn(date);
    }

    public void createdBy(BaseEntity baseEntity) {
        baseEntity.setCreatedBy(ejarDetailsService.loadUserByJwtTokenAndDatabase
                (LoginResponse.getLoginResponse().getJwt()).get().getUsername());
    }
}
