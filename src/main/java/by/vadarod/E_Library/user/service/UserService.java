package by.vadarod.E_Library.user.service;

import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.jwt.model.JwtAuthenticationResponse;
import by.vadarod.E_Library.jwt.service.JwtService;
import by.vadarod.E_Library.jwt.service.RefreshTokenService;
import by.vadarod.E_Library.tools.exception.model.UserLoginException;
import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.dto.UserUppDto;
import by.vadarod.E_Library.user.entity.RoleEntity;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BookRepository bookRepository;

    private final UserMapper userMapper;

    private final BookMapper bookMapper;

    private final PasswordEncoder byCryptPasswordEncoder;

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Secured({"ADMIN", "LIBRARIAN"})
    public List<UserUppDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable page =  PageRequest.of(pageNumber, pageSize,  Sort.by(Sort.Direction.ASC, "id"));
        Page<UserEntity> userEntityList =  userRepository.findAll(page);
        return userEntityList.stream().map(userMapper::toUserUppDto).toList();

    }

    @Secured({"ADMIN"})
    public void dellById(long id) {
        userRepository.deleteById(id);
    }

    @Secured({"ADMIN", "LIBRARIAN", "READER"})
    public UserUppDto getById(long id) {
        return userMapper.toUserUppDto(userRepository.getById(id));
    }

    @Secured({"ADMIN"})
    public void saveLibrarian (UserCreateDto userCreateDto) throws UserLoginException {
        if (userRepository.findByLogin(userCreateDto.getLogin()).isPresent()) {
            throw new UserLoginException("Пользователь с таким логином уже существует");
        } else {
            UserEntity userEntity = userMapper.toUserEntity(userCreateDto, roleRepository, bookRepository);
            RoleEntity roleEntity = roleRepository.getRoleByName("LIBRARIAN");
            userEntity.setRole(roleEntity);
            userEntity.setPassword(byCryptPasswordEncoder.encode(userCreateDto.getPassword()));
            userRepository.save(userEntity);
        }
    }

    public JwtAuthenticationResponse saveUser(UserCreateDto userCreateDto) throws UserLoginException {
        if (userRepository.findByLogin(userCreateDto.getLogin()).isPresent()) {
            throw new UserLoginException("Пользователь с таким логином уже существует");
        } else {
            UserEntity userEntity = userMapper.toUserEntity(userCreateDto, roleRepository, bookRepository);
            RoleEntity roleEntity = roleRepository.getRoleByName("READER");
            userEntity.setRole(roleEntity);
            userEntity.setPassword(byCryptPasswordEncoder.encode(userCreateDto.getPassword()));
            userRepository.save(userEntity);

            String jwt = jwtService.generateToken(userEntity);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenService.generateRefreshToken(userEntity.getLogin()));
            return jwtAuthenticationResponse;
        }
    }

    @Secured({"ADMIN", "LIBRARIAN", "READER"})
    public void saveUppUser(UserUppDto userUppDto) throws UserLoginException {
        if (userRepository.findByLoginAndIdNot(userUppDto.getLogin(), userUppDto.getId()).isPresent()) {
            throw new UserLoginException("Пользователь с таким логином уже существует");
        } else {
            UserEntity userEntity = userMapper.toUserUppEntity(userUppDto, roleRepository, bookRepository);
            userEntity.setPassword(byCryptPasswordEncoder.encode(userUppDto.getPassword()));
            userRepository.save(userEntity);
        }
    }

    @Secured({"ADMIN", "READER"})
    public List<BookUppDto> getFavoritesOfUser(String login) {
        return userRepository.findByLogin(login).orElse(null).getFavorites().stream().map(bookMapper::bookToBookUppDto).toList();
    }

    @Secured({"ADMIN", "READER"})
    public void saveBookToFavorites(String login, Long bookId) {
        UserEntity  user = userRepository.findByLogin(login).get();
        BookEntity book = bookRepository.getById(bookId);
        user.getFavorites().add(book);
        userRepository.save(user);
    }

    @Secured({"ADMIN", "READER"})
    public void dellBookFromFavorites(String login, Long bookId) {
        UserEntity  user = userRepository.findByLogin(login).get();
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

    public UserDetails getUserDetails(String userName) {
        Optional<UserEntity> userEntityList = userRepository.findByLogin(userName);


        return userRepository.findByLogin(userName).stream().findFirst().orElse(null);
    }
}
