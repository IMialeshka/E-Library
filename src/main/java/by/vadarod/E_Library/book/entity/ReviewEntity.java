package by.vadarod.E_Library.book.entity;


import by.vadarod.E_Library.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "reviews", schema = "e_library")
public class ReviewEntity {
    @Id
    @SequenceGenerator(name = "seqReview", schema = "e_library", sequenceName = "seq_review", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqReview")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private short rating;

    @Column(length = 2000)
    private String text;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookEntity book;

}
