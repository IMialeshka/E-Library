package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.repository.RoleRepository;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingRulesForUsersDomain {
    public static List<UserEntity> mapListIdToListUserEntity(Map<Long, String> users, UserRepository userRepository) {
        return  userRepository.findByIdIn(users.keySet());
    }

    public static Map<Long, String> mapListEntityToListId(List<UserEntity> userEntityList) {
        Map<Long, String> idList = new HashMap<>();
        for (UserEntity user : userEntityList) {
            idList.put(user.getId(), user.getName());
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

    public static List<BookEntity> mapIdListToBookEntityList(Map<Long, String> idList, BookRepository bookRepository) {
        return bookRepository.findByIdIn(idList.keySet());
    }

    public static Map<Long, String> mapIdBookToIdList(List<BookEntity> bookEntities) {
        Map<Long, String> idList = new HashMap<>();
        for (BookEntity book : bookEntities) {
            idList.put(book.getId(), book.getName());
        }
        return  idList;
    }
}
