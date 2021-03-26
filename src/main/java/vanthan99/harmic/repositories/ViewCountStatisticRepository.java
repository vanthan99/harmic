package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.ViewCountStatistic;

import java.util.UUID;

public interface ViewCountStatisticRepository extends JpaRepository<ViewCountStatistic, UUID> {
}
