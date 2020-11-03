package pl.ujbtrinity.devplatform.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ProjectInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User invitingUser;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User invitedUser;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Project project;

    private LocalDateTime created;
}
