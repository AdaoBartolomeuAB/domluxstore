package com.domluxstore.Initial;

import com.domluxstore.domain.Administrator;
import com.domluxstore.domain.User;
import com.domluxstore.domain.enumeration.AdministratorType;
import com.domluxstore.repository.AdministratorRepository;
import com.domluxstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Impl implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AdministratorRepository administratorRepository;

    @Autowired
    public Impl(UserRepository userRepository, AdministratorRepository administratorRepository) {
        this.userRepository = userRepository;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public void run(String... args)  {

        if (userRepository.count()>0) return;

        String role = "ADMIND";

        User user = new User();
        user.setName("Adão Gaspar");
        user.setEmail("adaogaspar96@hotmail.com");
        user.setPhone("916774313");
        user.addRole(role);

        User user1 = userRepository.save(user);

        Administrator administrator = new Administrator();

        administrator.setAdministratorType(AdministratorType.SUPER_ADMIN);
        administrator.setUser(user1);

        administratorRepository.save(administrator);

        log.info("THE FRIST USER INFORMATION WAS SAVE...");
    }
}
