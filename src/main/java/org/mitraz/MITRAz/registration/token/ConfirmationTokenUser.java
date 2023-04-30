package org.mitraz.MITRAz.registration.token;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ConfirmationTokenUser {


    @SequenceGenerator(
            name="confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "confirmation_token_sequence"
	)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
   private User user;


    public ConfirmationTokenUser(String token, LocalDateTime createdAt,
                                 LocalDateTime expiresAt, User user) {

        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
