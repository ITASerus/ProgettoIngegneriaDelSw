package it.travelapp.travelapp.repository;

import it.travelapp.travelapp.model.UserNEW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserNEWRepository extends JpaRepository<UserNEW, Long> {
    Optional<UserNEW> findByEmail(String email);

    Optional<UserNEW> findByUsernameOrEmail(String username, String email);

    List<UserNEW> findByIdIn(List<Long> userIds);

    Optional<UserNEW> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}