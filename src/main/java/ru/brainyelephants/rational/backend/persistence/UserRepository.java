package ru.brainyelephants.rational.backend.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brainyelephants.rational.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
