package by.vadarod.E_Library;

import by.vadarod.E_Library.book.dto.*;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.service.AuthorService;
import by.vadarod.E_Library.book.service.BookService;
import by.vadarod.E_Library.book.service.ReviewService;
import by.vadarod.E_Library.user.dto.*;
import by.vadarod.E_Library.user.service.RoleService;
import by.vadarod.E_Library.user.service.SubscriptionService;
import by.vadarod.E_Library.user.service.SubscriptionUserService;
import by.vadarod.E_Library.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class ELibraryApplication {


	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ELibraryApplication.class, args);

        RoleService roleService = context.getBean(RoleService.class);
        RoleCreateDto roleDto = new RoleCreateDto();
        roleDto.setName("admin");
        roleService.saveRole(roleDto);


        RoleCreateDto roleDtoU = new RoleCreateDto();
        roleDtoU.setName("reader");
        roleService.saveRole(roleDtoU);

        System.out.println("=======================================================================");
        RoleUppDto roleUppDto = roleService.getById(1);
        RoleUppDto roleUppDto1 = roleService.getById(2);

        UserService userService = context.getBean(UserService.class);
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("admin");
        userCreateDto.setLogin("admin");
        userCreateDto.setPassword("123456");
        userCreateDto.setRole(roleUppDto);

        userService.saveUser(userCreateDto);

        UserCreateDto userCreateDtoUser = new UserCreateDto();
        userCreateDtoUser.setName("user1");
        userCreateDtoUser.setLogin("user");
        userCreateDtoUser.setPassword("123456");
        userCreateDtoUser.setRole(roleUppDto1);

        userService.saveUser(userCreateDtoUser);
        System.out.println("=======================================================================");
        SubscriptionService subscriptionService = context.getBean(SubscriptionService.class);

        SubscriptionCreateDto subscriptionCreateDto = new SubscriptionCreateDto();
        subscriptionCreateDto.setName("subscription1");
        subscriptionCreateDto.setPrice(100.1);
        subscriptionCreateDto.setLengthMonth((short) 12);
        subscriptionService.saveSubscription(subscriptionCreateDto);

        SubscriptionCreateDto subscriptionCreateDto1 = new SubscriptionCreateDto();
        subscriptionCreateDto1.setName("subscription2");
        subscriptionCreateDto1.setPrice(50.12);
        subscriptionCreateDto1.setLengthDay((short) 60);
        subscriptionService.saveSubscription(subscriptionCreateDto1);

        System.out.println("=======================================================================");

        SubscriptionUserService subscriptionUserService = context.getBean(SubscriptionUserService.class);
        subscriptionUserService.saveSubscriptionUser(1,1);
        subscriptionUserService.saveSubscriptionUser(2,2);

        System.out.println("=======================================================================");
        AuthorService authorService = context.getBean(AuthorService.class);

        AuthorCreateDto authorCreateDto1 = new AuthorCreateDto();
        authorCreateDto1.setName("author1");
        authorService.saveAuthor(authorCreateDto1);

        AuthorCreateDto authorCreateDto2 = new AuthorCreateDto();
        authorCreateDto2.setName("author2");
        authorService.saveAuthor(authorCreateDto2);

        System.out.println("=======================================================================");
        BookService bookService = context.getBean(BookService.class);

        BookCreateDto bookCreateDto = new BookCreateDto();

        bookCreateDto.setName("book1");
        bookCreateDto.setGenre(Genre.HORROR);
        List<AuthorUppDto> authorUppDtoList = new ArrayList<>();
        AuthorUppDto authorUppDto1 = authorService.getById(1);
        authorUppDtoList.add(authorUppDto1);
        bookCreateDto.setAuthors(authorUppDtoList);
        bookCreateDto.setPathFile("book1.pdf");
        bookService.saveBook(bookCreateDto);

        BookCreateDto bookCreateDto1 = new BookCreateDto();
        bookCreateDto1.setName("book2");
        bookCreateDto1.setGenre(Genre.ROMANCE);
        bookCreateDto1.setAuthors(authorUppDtoList);
        bookCreateDto1.setPathFile("book2.pdf");
        bookService.saveBook(bookCreateDto1);

        System.out.println("=======================================================================");
        ReviewService reviewService = context.getBean(ReviewService.class);
        BookUppDto bookUppDto1 = bookService.getById(1);
        UserUppDto userUppDto1 = userService.getById(1);
        ReviewCreateDto reviewCreateDto = new ReviewCreateDto();
        reviewCreateDto.setBook(bookUppDto1);
        reviewCreateDto.setUser(userUppDto1);
        reviewCreateDto.setText("1111111111111111111111");
        reviewCreateDto.setRating((short) 6);
        reviewService.saveReview(reviewCreateDto);

        ReviewCreateDto reviewCreateDto2 = new ReviewCreateDto();
        reviewCreateDto2.setBook(bookUppDto1);
        reviewCreateDto2.setUser(userUppDto1);
        reviewCreateDto2.setText("22222222222222222222222222222222");
        reviewCreateDto2.setRating((short) 5);
        reviewService.saveReview(reviewCreateDto2);

	}

}
