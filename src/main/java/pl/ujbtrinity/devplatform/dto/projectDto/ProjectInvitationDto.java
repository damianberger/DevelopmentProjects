package pl.ujbtrinity.devplatform.dto.projectDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInvitationDto {
    private Long invitationId;
    private String invitingUser;
    private String invitedUser;
    private LocalDateTime invitationDateTime;
}
