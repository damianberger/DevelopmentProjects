package pl.ujbtrinity.devplatform.entity;

import lombok.Data;
import pl.ujbtrinity.devplatform.model.InvitationStatus;

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

    @Column(nullable = false)
    @ManyToOne(cascade = CascadeType.DETACH)
    private User invitedUser;

    @Column(nullable = false)
    @ManyToOne(cascade = CascadeType.DETACH)
    private Project project;

    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

}
