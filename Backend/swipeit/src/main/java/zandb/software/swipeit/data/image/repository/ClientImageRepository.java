package zandb.software.swipeit.data.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zandb.software.swipeit.data.image.ClientImage;

@Repository
public interface ClientImageRepository extends JpaRepository<ClientImage, Long> {
}
