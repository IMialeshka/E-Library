package by.vadarod.E_Library.jwt.entity;

import by.vadarod.E_Library.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "refresh_token", schema = "jwt_sch")
public class RefreshToken {
    @Id
    @SequenceGenerator(name = "seqToken", schema = "jwt_sch", sequenceName = "jwt_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqToken")
    private long id;

    private String token;

    @OneToOne
    @JoinColumn
    UserEntity user;

    @Column(name = "date_time_token")
    private LocalDateTime dateTimeToken;
}
