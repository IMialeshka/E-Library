package by.vadarod.repository;

import by.vadarod.E_Library.ELibraryApplication;
import by.vadarod.E_Library.book.entity.AuthorEntity;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ELibraryApplication.class)
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void testSaveAuthor(){
        int startSize = authorRepository.findAll().size();

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("test");
        authorRepository.save(authorEntity);
        int endSize = authorRepository.findAll().size();
        assertEquals(startSize+1, endSize);
    }

    @Test
    public void testRemove(){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("test");
        AuthorEntity authorEntityAfterSaving= authorRepository.save(authorEntity);
        int startSize = authorRepository.findAll().size();
        authorRepository.deleteById(authorEntityAfterSaving.getId());
        int endSize = authorRepository.findAll().size();
        assertEquals(startSize - 1, endSize);
    }

    @Test
    public void testUpp(){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("test");
        AuthorEntity authorEntityAfterSaving= authorRepository.save(authorEntity);
        authorEntityAfterSaving.setName("test2");
        AuthorEntity authorEntityAfterUpp= authorRepository.save(authorEntity);
        assertTrue(authorEntityAfterUpp.getId() == authorEntityAfterSaving.getId() && authorEntityAfterUpp.getName().equals("test2"));
    }

    @Test
    public void testFindByIdList(){
        AuthorEntity authorEntity1 = new AuthorEntity();
        authorEntity1.setName("test");
        AuthorEntity authorEntityAfterSaving1= authorRepository.save(authorEntity1);


        AuthorEntity authorEntity2 = new AuthorEntity();
        authorEntity2.setName("test");
        AuthorEntity authorEntityAfterSaving2= authorRepository.save(authorEntity2);

        AuthorEntity authorEntity3 = new AuthorEntity();
        authorEntity3.setName("test");
        AuthorEntity authorEntityAfterSaving3= authorRepository.save(authorEntity3);

        Set<Long> findIdList = new HashSet<>();
        findIdList.add(authorEntityAfterSaving1.getId());
        findIdList.add(authorEntityAfterSaving2.getId());
        findIdList.add(authorEntityAfterSaving3.getId());
        assertEquals(3,authorRepository.findByIdIn(findIdList).size());
    }
}
