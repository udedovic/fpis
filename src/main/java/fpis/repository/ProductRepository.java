package fpis.repository;

import fpis.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(nativeQuery = true, value = "select max(sifra) from product")
    Integer getNewId();
}
