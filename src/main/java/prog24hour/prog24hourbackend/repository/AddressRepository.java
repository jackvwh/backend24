package prog24hour.prog24hourbackend.repository;

import prog24hour.prog24hourbackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
}
