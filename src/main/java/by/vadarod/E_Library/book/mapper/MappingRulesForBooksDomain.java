package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.entity.AuthorEntity;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.BookFileEntity;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.repository.BookFileRepository;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingRulesForBooksDomain {

    public static Map<Long, String> booksToIdsList(List<BookEntity> bookEntities) {
        Map<Long, String> bookIds = new HashMap<>();
        for (BookEntity bookEntity : bookEntities) {
            bookIds.put(bookEntity.getId(), bookEntity.getName());
        }
        return bookIds;
    }

    public static List<BookEntity> idsToBooksList(Map<Long, String> booksIdList, BookRepository bookRepository) {
        return bookRepository.findByIdIn(booksIdList.keySet());
    }

    public static List<AuthorEntity> idsToAuthorsList(Map<Long, String> authorsIdList, AuthorRepository authorRepository) {
        return authorRepository.findByIdIn(authorsIdList.keySet());
    }

    public static Map<Long, String> authorsToIdList(List<AuthorEntity> authorEntities) {
        Map<Long, String> idList = new HashMap<>();
        for (AuthorEntity authorEntity : authorEntities) {
            idList.put(authorEntity.getId(), authorEntity.getName());
        }
        return idList;
    }

    public static List<BookFileEntity> idsToFilesList(List<Long> filesList, BookFileRepository bookFileRepository) {
        List<BookFileEntity> bookFileEntities = new ArrayList<>();
        for (Long id : filesList) {
            bookFileEntities.add(bookFileRepository.findById(id).orElse(null));
        }
        return bookFileEntities;
    }

    public static List<Long> bookFilesToIdList(List<BookFileEntity> bookFileEntities) {
        List<Long> idList = new ArrayList<>();
        for (BookFileEntity bookFileEntity : bookFileEntities) {
            idList.add(bookFileEntity.getId());
        }
        return idList;
    }

    public static List<ReviewEntity> idsToReviewList(Long idBook, ReviewRepository reviewRepository) {
        return reviewRepository.findByBookId(idBook);
    }

    public static BookEntity idToBook(Long id, BookRepository bookRepository) {
         return bookRepository.findById(id).orElse(null);
    }

    public static UserEntity idToUser(Long id, UserRepository userRepository) {
        return userRepository.findById(id).orElse(null);
    }

}
