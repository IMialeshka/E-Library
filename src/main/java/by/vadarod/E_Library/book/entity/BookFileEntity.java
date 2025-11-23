package by.vadarod.E_Library.book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "book_files", schema = "e_library")
public class BookFileEntity {
    @Id
    @SequenceGenerator(name = "seqFile", schema = "e_library", sequenceName = "seq_file", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqFile")
    private long id;

    @Column(name = "file_name",nullable = false)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity book;

}
