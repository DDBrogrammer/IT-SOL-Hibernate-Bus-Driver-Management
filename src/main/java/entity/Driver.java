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
@Table(name = "driver")
public class Driver implements Serializable {

    @Id
    @Column(nullable = false)
    int id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "address",nullable = false)
    String address;

    @Column( name = "phone",nullable = false)
    String phone;

    public Driver(String name, String address, String phone, String skill) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.skill = skill;
    }

    @Column(name = "skill_level",nullable=false)
    String skill;

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }

    @OneToMany(
            mappedBy = "driver",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Roster> rosters;
}

