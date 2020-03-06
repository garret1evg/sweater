package ua.training.chmutov.sweater.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.chmutov.sweater.domain.User;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
