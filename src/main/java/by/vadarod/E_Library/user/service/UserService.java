package by.vadarod.E_Library.user.service;

import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.tools.exception.model.UserLoginException;
import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.dto.UserUppDto;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.mapper.UserMapper;
import by.vadarod.E_Library.user.repository.RoleRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BookRepository bookRepository;

    private final UserMapper userMapper;

    private final BookMapper bookMapper;

    public List<UserUppDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable page =  PageRequest.of(pageNumber, pageSize,  Sort.by(Sort.Direction.ASC, "id"));
        Page<UserEntity> userEntityList =  userRepository.findAll(page);
        return userEntityList.stream().map(userMapper::toUserUppDto).toList();

    }

    public void dellById(long id) {
        userRepository.deleteById(id);
    }

    public UserUppDto getById(long id) {
        return userMapper.toUserUppDto(userRepository.getById(id));
    }

    public void saveUser(UserCreateDto userCreateDto) throws UserLoginException {
        if (!userRepository.findByLoginIgnoreCase(userCreateDto.getLogin()).isEmpty()) {
            throw new UserLoginException("Пользователь с таким логином уже существует");
        } else {
            UserEntity userEntity = userMapper.toUserEntity(userCreateDto, roleRepository, bookRepository);
            userRepository.save(userEntity);
        }
    }

    public void saveUppUser(UserUppDto userUppDto) throws UserLoginException {
        if (!userRepository.findByLoginIgnoreCaseAndIdNot(userUppDto.getLogin(), userUppDto.getId()).isEmpty()) {
            throw new UserLoginException("Пользователь с таким логином уже существует");
        } else {
            UserEntity userEntity = userMapper.toUserUppEntity(userUppDto, roleRepository, bookRepository);
            userRepository.save(userEntity);
        }
    }

    public List<BookUppDto> getFavoritesOfUser(Long userId) {
        return userRepository.findById(userId).orElse(null).getFavorites().stream().map(bookMapper::bookToBookUppDto).toList();
    }

    public void saveBookToFavorites(Long userId, Long bookId) {
        UserEntity  user = userRepository.getById(userId);
        BookEntity book = bookRepository.getById(bookId);
        user.getFavorites().add(book);
        userRepository.save(user);
    }

    public void dellBookFromFavorites(Long userId, Long bookId) {
        UserEntity  user = userRepository.getById(userId);
        List<BookEntity> bookEntityList = user.getFavorites();
        Iterator<BookEntity> iterator = bookEntityList.iterator();
        while (iterator.hasNext()) {
            BookEntity book = iterator.next();
            if (book.getId() == bookId) {
                iterator.remove();
            }
        }
        user.setFavorites(bookEntityList);
        userRepository.save(user);
    }
}
