package by.vadarod.E_Library.book.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BookFileUppDto extends BookFileCreateDto{
    private long id;
}
