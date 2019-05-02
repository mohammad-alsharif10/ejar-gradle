package apartment.ejar.services.jwtService;

import apartment.ejar.controllers.jwtController.LoginResponse;
import apartment.ejar.entities.Broker;
import apartment.ejar.entities.BrokerRoles;
import apartment.ejar.entities.Role;
import apartment.ejar.repositories.BrokerRepository;
import apartment.ejar.repositories.BrokerRolesRepository;
import apartment.ejar.repositories.RoleRepository;
import apartment.ejar.security.JwtProvider;
import apartment.ejar.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EjarService {

    private BrokerRepository brokerRepository;

    private AuthenticationManager authenticationManager;

    private BrokerRolesRepository brokerRolesRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private JwtProvider jwtProvider;

    private LoginResponse loginResponse;

    public Optional<String> signin(String username, String password) {
        Optional<String> token = Optional.empty();
        Optional<Broker> broker = brokerRepository.findByUsername(username);
        if (broker.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = Optional.of(jwtProvider.createToken(username, broker.get().getRoles()));
            } catch (AuthenticationException e) {
                System.out.println("Log in failed for user {}" + username);
            }
        }
        return token;
    }

    public List<Broker> getAll() {
        return brokerRepository.findAll();
    }

    public boolean isUserPresent(String username) {
        return brokerRepository.findByUsername(username).isPresent();
    }

    public void setRoles(Broker broker) {
        List<Role> roles = broker.getRoles();
        roles.forEach(role -> {
            BrokerRoles brokerRoles = new BrokerRoles();
            brokerRoles.setBrokerId(broker.getBrokerId());
            brokerRoles.setRoleId(role.getId());
            brokerRolesRepository.save(brokerRoles);
        });
    }

    public LoginResponse handleLoginResponse(Optional<Broker> broker, LoginResponse loginResponse) {
        if (loginResponse.getJwt().equalsIgnoreCase("wrong password")) {
            loginResponse.setJwt(null);
            Constants.jwt=null;
            loginResponse.setStatus(false);
            loginResponse.setStatusMessage("invalid user name or password");
            return loginResponse;
        }
        if (!broker.isPresent()) {
            loginResponse.setJwt(null);
            Constants.jwt=null;
            loginResponse.setStatus(false);
            loginResponse.setStatusMessage("account is not found");
            return loginResponse;
        } else {
            loginResponse.setStatus(true);
            loginResponse.setStatusMessage("account is active");
            loginResponse.setBroker(broker.get());
            return loginResponse;
        }
    }
}
