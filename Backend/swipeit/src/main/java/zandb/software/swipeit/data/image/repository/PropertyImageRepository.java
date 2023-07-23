package zandb.software.swipeit.data.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zandb.software.swipeit.data.image.PropertyImage;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {

}
