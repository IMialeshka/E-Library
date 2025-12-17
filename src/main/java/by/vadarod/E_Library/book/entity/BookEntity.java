package by.vadarod.E_Library.book.entity;


import by.vadarod.E_Library.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "books", schema = "e_library")
public class BookEntity {
    @Id
    @SequenceGenerator(name = "seqBook", schema = "e_library", sequenceName = "seq_book", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBook")
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_author", schema = "e_library", joinColumns =  @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<AuthorEntity> authors;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<BookFileEntity> bookFiles;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favorites")
    private List<UserEntity> users;

    @OneToMany(fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewEntityList;

    private double rating;
}
