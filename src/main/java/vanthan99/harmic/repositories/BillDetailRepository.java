package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.BillDetail;

import java.util.UUID;

public interface BillDetailRepository extends JpaRepository<BillDetail, UUID> {

}
