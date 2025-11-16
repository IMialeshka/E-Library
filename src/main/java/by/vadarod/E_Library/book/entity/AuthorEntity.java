package by.vadarod.E_Library.book.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "authors", schema = "e_library")
public class AuthorEntity {
    @Id
    @SequenceGenerator(name = "seqAuthor", schema = "e_library", sequenceName = "seq_author", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAuthor")
    private long id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    private List<BookEntity> books;

}
