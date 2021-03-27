package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.Banner;

import java.util.UUID;

public interface BannerRepository extends JpaRepository<Banner, UUID> {
}
