package fpis.domain;

import fpis.identity.PresenceItemIdentity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "presence_item")
@IdClass(PresenceItemIdentity.class)
@NoArgsConstructor
@Getter
@Setter
public class PresenceItem {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worksheet_sifra", referencedColumnName = "sifra")
    private Worksheet worksheet;

    @Id
    private Integer redniBroj;
    private Integer vrstaPrisustva;
    private String opis;
    private Date datum;
}
