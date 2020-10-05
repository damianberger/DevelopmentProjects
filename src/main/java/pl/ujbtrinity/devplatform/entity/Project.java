package pl.ujbtrinity.devplatform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany(mappedBy = "projects")
    private Set<Technology> technologiesUsed;
    @ManyToMany(mappedBy = "projects")
    private Set<Framework> frameworksUsed;
    @ManyToMany(mappedBy = "projects")
    private Set<User> users;

}
