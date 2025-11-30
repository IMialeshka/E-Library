package by.vadarod.E_Library.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "roles", schema = "e_library")
public class RoleEntity {
    @Id
    @SequenceGenerator(name = "seqRole", schema = "e_library", sequenceName = "seq_role", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRole")
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<UserEntity> userEntityList;

}
