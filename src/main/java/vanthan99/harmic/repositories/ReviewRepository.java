package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.Review;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
