package org.mitraz.MITRAz.registration.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mitraz.MITRAz.model.nurse.Nurse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ConfirmationTokenNurse {

    @Id
    @GeneratedValue
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
            name = "nurse_id"
    )
    private Nurse nurse;

    public ConfirmationTokenNurse(String token, LocalDateTime createdAt,
                                  LocalDateTime expiresAt,
                                  Nurse nurse) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.nurse = nurse;
        this.confirmedAt = LocalDateTime.now();
    }

}
