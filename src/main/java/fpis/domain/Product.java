package fpis.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    private Integer sifra;
    private BigDecimal duzina;
    private BigDecimal sirina;
    private BigDecimal visina;
    private BigDecimal debljinaStakla;
}
