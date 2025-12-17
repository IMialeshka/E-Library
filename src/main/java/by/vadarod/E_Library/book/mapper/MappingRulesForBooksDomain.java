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
import java.util.List;

public class MappingRulesForBooksDomain {

    public static List<Long> booksToIdsList(List<BookEntity> bookEntities) {
        List<Long> bookIds = new ArrayList<>();
        for (BookEntity bookEntity : bookEntities) {
            bookIds.add(bookEntity.getId());
        }
        return bookIds;
    }

    public static List<BookEntity> idsToBooksList(List<Long> booksIdList, BookRepository bookRepository) {
        List<BookEntity> bookEntities = new ArrayList<>();
        for (Long id : booksIdList) {
            bookEntities.add(bookRepository.findById(id).orElse(null));
        }
        return bookEntities;
    }

    public static List<AuthorEntity> idsToAuthorsList(List<Long> authorsIdList, AuthorRepository authorRepository) {
        List<AuthorEntity> authorEntities = new ArrayList<>();
        for (Long id : authorsIdList) {
            authorEntities.add(authorRepository.findById(id).orElse(null));
        }
        return authorEntities;
    }

    public static List<Long> authorsToIdList(List<AuthorEntity> authorEntities) {
        List<Long> idList = new ArrayList<>();
        for (AuthorEntity authorEntity : authorEntities) {
            idList.add(authorEntity.getId());
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

    public static List<ReviewEntity> idsToReviewList(List<Long> reviewListId, ReviewRepository reviewRepository) {
        List<ReviewEntity> reviewEntityList = new ArrayList<>();
        for (Long id : reviewListId) {
            reviewEntityList.add(reviewRepository.findById(id).orElse(null));
        }
        return reviewEntityList;
    }

    public static List<Long> reviewsToIdList(List<ReviewEntity> reviewEntityList) {
        List<Long> idList = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntityList) {
            idList.add(reviewEntity.getId());
        }
        return idList;
    }

    public static BookEntity idToBook(Long id, BookRepository bookRepository) {
         return bookRepository.findById(id).orElse(null);
    }

    public static UserEntity idToUser(Long id, UserRepository userRepository) {
        return userRepository.findById(id).orElse(null);
    }

}
