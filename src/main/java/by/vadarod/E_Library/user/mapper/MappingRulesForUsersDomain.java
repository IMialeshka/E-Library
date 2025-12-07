package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.repository.RoleRepository;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MappingRulesForUsersDomain {
    public static List<UserEntity> mapListIdToListUserEntity(List<Long> idList, UserRepository userRepository) {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (Long id : idList) {
            userEntityList.add(userRepository.findById(id).orElse(null));
        }
        return  userEntityList;
    }

    public static List<Long> mapListEntityToListId(List<UserEntity> userEntityList) {
        List<Long> idList = new ArrayList<>();
        for (UserEntity user : userEntityList) {
            idList.add(user.getId());
        }
        return  idList;
    }

    public static UserEntity mapIdToUser(Long id, UserRepository userRepository) {
        return userRepository.findById(id).orElse(null);
    }

    public static RoleEntity mapIdToRole(Long id, RoleRepository roleRepository) {
        return roleRepository.findById(id).orElse(null);
    }

    public static SubscriptionEntity mapIdToSubscription(Long id, SubscriptionRepository subscriptionRepository) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public static List<BookEntity> mapIdListToBookEntityList(List<Long> idList, BookRepository bookRepository) {
        List<BookEntity> bookEntityList = new ArrayList<>();
        for (Long id : idList) {
            bookEntityList.add(bookRepository.findById(id).orElse(null));
        }
        return  bookEntityList;
    }

    public static List<Long> mapIdBookToIdList(List<BookEntity> bookEntities) {
        List<Long> idList = new ArrayList<>();
        for (BookEntity book : bookEntities) {
            idList.add(book.getId());
        }
        return  idList;
    }
}
