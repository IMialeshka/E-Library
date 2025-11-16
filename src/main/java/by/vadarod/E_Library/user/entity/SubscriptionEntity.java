package by.vadarod.E_Library.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "subscriptions", schema = "e_library")
public class SubscriptionEntity {
    @Id
    @SequenceGenerator(name = "seqSubs", schema = "e_library", sequenceName = "seq_subs", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSubs")
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(name = "length_month", nullable = false)
    private short lengthMonth;
    @Column(name = "length_day", nullable = false)
    private int lengthDay;
}
