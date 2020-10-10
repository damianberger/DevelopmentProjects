package pl.ujbtrinity.devplatform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "frameworks")
@Getter
@Setter
@NoArgsConstructor
public class Framework {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "frameworks")
    private Set<User> users;

    @ManyToMany(mappedBy = "frameworksUsed")
    private Set<Project> projects;

    @Override
    public String toString() {
        return "Framework{" +
                "name='" + name + '\'' +
                '}';
    }
}
