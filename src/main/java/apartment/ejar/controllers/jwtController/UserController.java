package apartment.ejar.controllers.jwtController;


import apartment.ejar.entities.Broker;
import apartment.ejar.entities.Role;
import apartment.ejar.feign.BrokerFeign;
import apartment.ejar.repositories.BrokerRepository;
import apartment.ejar.services.jwtService.EjarService;
import apartment.ejar.util.Auditing;
import apartment.ejar.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private BrokerFeign brokerFeign;
    private EjarService ejarService;
    private Auditing auditing;
    private BrokerRepository brokerRepository;


    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody Broker broker) {
        List<Role> roles = broker.getRoles();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        broker.setPassword(passwordEncoder.encode(broker.getPassword()));
        auditing.createdOn(broker);
        broker.setRoles(null);
        broker.setBrokerId(brokerFeign.insert(broker).getBrokerId());
        broker.setRoles(roles);
        ejarService.setRoles(broker);
        return ResponseEntity.status(HttpStatus.CREATED).body(broker);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginDto loginDto) {
        LoginResponse loginResponse = LoginResponse.getLoginResponse();
        Optional<Broker> user = brokerRepository.findByUsername(loginDto.getUsername());
        String jwt = ejarService.signin(loginDto.getUsername(), loginDto.getPassword()).orElse("wrong password");
        loginResponse.setJwt(jwt);
        Constants.jwt = Constants.bearer + jwt;
        return ejarService.handleLoginResponse(user, loginResponse);
    }
}
