package com.unir.products.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "club")
    private String club;

    @Column(name = "position")
    private String position;

    @Column(name = "overall")
    private int overall;

    @Column(name = "pace")
    private int pace;

    @Column(name = "shooting")
    private int shooting;

    @Column(name = "passing")
    private int passing;

    @Column(name = "dribbling")
    private int dribbling;

    @Column(name = "defending")
    private int defending;

    @Column(name = "physicality")
    private int physicality;

    @Column(name = "image")
    private String image;

    public void update(PlayerDto playerDto) {
        this.name = playerDto.getName();
        this.club = playerDto.getClub();
        this.position = playerDto.getPosition();
        this.overall = playerDto.getOverall();
        this.pace = playerDto.getPace();
        this.shooting = playerDto.getShooting();
        this.passing = playerDto.getPassing();
        this.dribbling = playerDto.getDribbling();
        this.physicality = playerDto.getPhysicality();
        this.image = playerDto.getImage();


    }
}
