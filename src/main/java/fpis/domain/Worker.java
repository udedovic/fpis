package fpis.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Worker {
    @Id
    private Integer sifra;
    private String imePrezime;
    private String jmbg;
    private String radnoMesto;
}
