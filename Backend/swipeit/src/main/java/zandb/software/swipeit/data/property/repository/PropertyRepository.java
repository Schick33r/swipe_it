package zandb.software.swipeit.data.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zandb.software.swipeit.data.property.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
