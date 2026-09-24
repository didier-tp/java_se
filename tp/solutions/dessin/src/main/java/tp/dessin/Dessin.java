package tp.dessin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dessin {
    private Long id;
    private String titre;
    private String path;
    private String description;

    public Dessin(Long id, String titre, String path, String description) {
        this.id = id;
        this.titre = titre;
        this.path = path;
        this.description = description;
    }

}
