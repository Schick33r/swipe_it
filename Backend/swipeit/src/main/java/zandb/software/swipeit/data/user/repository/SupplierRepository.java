package zandb.software.swipeit.data.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zandb.software.swipeit.data.user.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
