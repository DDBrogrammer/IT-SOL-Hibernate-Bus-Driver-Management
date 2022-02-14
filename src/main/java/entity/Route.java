package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route")
public class Route implements Serializable {
    @Id
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private double distance;
    @Column(name="total_bus_stop",nullable = false)
    private int totalBusStop;

    public Route(double distance, int totalBusStop) {
        this.distance = distance;
        this.totalBusStop = totalBusStop;
    }
    @OneToMany(
            mappedBy = "route",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Roster> rosters ;

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", distance=" + distance +
                ", totalBusStop=" + totalBusStop +
                '}';
    }
}
