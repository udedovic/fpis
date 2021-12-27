package fpis.identity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbsenceItemIdentity implements Serializable {
    private Integer worksheet;
    private Integer redniBroj;
}
