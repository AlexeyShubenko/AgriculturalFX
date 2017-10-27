package com.agricultural.domains.main;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 15.03.2017.
 */
@Data
@Entity
@Table(name = "workplace")
@ToString
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String workPlaceName;

    @OneToMany(mappedBy = "workplace", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employee = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkPlaceName() {
        return workPlaceName;
    }

    public void setWorkPlaceName(String workPlaceName) {
        this.workPlaceName = workPlaceName;
    }

    @Override
    public String toString() {
        return "Workplace{" +
                "id=" + id +
                ", workPlaceName='" + workPlaceName + '\'' +
                '}';
    }
}
