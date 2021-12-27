package fpis.domain;

import fpis.identity.AbsenceItemIdentity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "absence_item")
@IdClass(AbsenceItemIdentity.class)
@NoArgsConstructor
@Getter
@Setter
public class AbsenceItem {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worksheet_sifra", referencedColumnName = "sifra")
    private Worksheet worksheet;

    @Id
    private Integer redniBroj;
    private Integer brojOdluke;
    private Integer vrstaOdsustva;
    private Date datumOd;
    private Date datumDo;
}
