package mercenarios.mercenarios.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "mercenary_skill", joinColumns = @JoinColumn(name = "merc_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @JsonBackReference
    private List<Mercenary> mercenaries;

    public Skill() {
    }

    public Skill(String name, List<Mercenary> mercenaries) {
        this.name = name;
        this.mercenaries = mercenaries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonBackReference
    public List<Mercenary> getMercenaries() {
        return mercenaries;
    }

    public void setMercenaries(List<Mercenary> mercenaries) {
        this.mercenaries = mercenaries;
    }
}
