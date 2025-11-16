package by.vadarod.E_Library.book.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReviewUppDto extends ReviewCreateDto{
    private long id;
}
