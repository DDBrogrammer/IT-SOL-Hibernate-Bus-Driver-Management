package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@Entity
@XmlRootElement
@NoArgsConstructor
@Table(name = "roster")
public class Roster implements Serializable {

    @Id
    @ManyToOne(targetEntity = Driver.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    Driver driver;

    @Id
    @ManyToOne(targetEntity = Route.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    Route route;

   @Column(name="total_route", nullable = false )
   int totalRoute;

    public Roster(Driver driver, Route route, int totalRoute) {
        this.driver = driver;
        this.route = route;
        this.totalRoute = totalRoute;
    }
}
