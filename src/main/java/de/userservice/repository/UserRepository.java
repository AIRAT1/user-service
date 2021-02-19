package de.userservice.repository;

import de.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User saveUser(User user);

    User findByUserId(Long userId);
}
