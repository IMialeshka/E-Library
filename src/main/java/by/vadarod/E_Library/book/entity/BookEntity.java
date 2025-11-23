package by.vadarod.E_Library.book.entity;


import by.vadarod.E_Library.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "books", schema = "e_library")
public class BookEntity {
    @Id
    @SequenceGenerator(name = "seqBook", schema = "e_library", sequenceName = "seq_book", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBook")
    @Setter
    @Getter
    private long id;
    @Setter
    @Getter
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_author", schema = "e_library", joinColumns =  @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<AuthorEntity> authors;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    @Getter
    private Genre genre;

    @Setter
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<BookFileEntity> bookFiles;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favorites")
    @Setter
    @Getter
    private List<UserEntity> users;

    @OneToMany(fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter
    @Getter
    private List<ReviewEntity> reviewEntityList;

    public List<AuthorEntity> getAuthors() {
        if (authors != null) {
            authors.forEach(
                    authorEntity -> authorEntity.setBooks(null)
            );
        }
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        if (authors != null) {
            authors.forEach(
                    authorEntity -> authorEntity.setBooks(null)
            );
        }
        this.authors = authors;
    }

    @Setter
    @Getter
    private double rating;
}
