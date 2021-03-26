package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
