package me.suveen.portfolio;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.backend.persistence.repositories.RoleRepository;
import me.suveen.portfolio.backend.service.UserService;
import me.suveen.portfolio.enums.RolesEnum;
import me.suveen.portfolio.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PortfolioApplication implements CommandLineRunner {

    /**
     * The application logger
     **/
    private static final Logger LOG = LoggerFactory.getLogger(PortfolioApplication.class);
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    public static void main(String[] args) {

        SpringApplication.run(PortfolioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String username = "suveenkumarchowdary";
        String email = "suveenkumar.vundavalli@gmail.com";
        User user = UserUtils.createBasicUser(username, email);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, roleRepository.save(new Role(RolesEnum.USER))));
        LOG.debug("Creating User with username {}", user.getUsername());
        userService.createUser(user, userRoles);
        LOG.info("User {} created", user.getUsername());

    }
}
