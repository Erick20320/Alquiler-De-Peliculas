package net.javaguides.springboot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
