package fpis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Integer sifraProizvoda;
    private BigDecimal duzinaProizvoda;
    private BigDecimal sirinaProizvoda;
    private BigDecimal visinaProizvoda;
    private BigDecimal  debljinaStakla;
}
