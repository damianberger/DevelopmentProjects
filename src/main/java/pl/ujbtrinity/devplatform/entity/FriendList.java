package pl.ujbtrinity.devplatform.entity;

import lombok.Data;
import pl.ujbtrinity.devplatform.model.FriendStatus;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long invitingUser;
    private String invitingUsername;

    private Long invitedUser;
    private String invitedUsername;

    private LocalDateTime invited;

    private LocalDateTime accepted;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FriendStatus status;
}
