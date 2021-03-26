package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.Bill;

import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
}
