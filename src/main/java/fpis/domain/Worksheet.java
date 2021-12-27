package fpis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Worksheet {
    @Id
    private Integer sifra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_sifra", referencedColumnName = "sifra")
    private Worker worker;

    @OneToMany(mappedBy = "worksheet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<PresenceItem> presenceItemList;

    @OneToMany(mappedBy = "worksheet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<AbsenceItem> absenceItemList;

}
