package com.example.coconut.global.initData;

import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.scrap.service.ScrapService;
import com.example.coconut.domain.user.entity.UserRole;
import com.example.coconut.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class Dev {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner init(UserService userService, CategoryService categoryService) {
        return args -> {
            userService.signup("test", "1234", "testnickname", "test@test.com", "01012341234", UserRole.USER);
            userService.signup("user", "1234", "usernickname", "user@test.com", "01012341234", UserRole.USER);
            userService.signup("esong", "1234", "esongnickname", "esong@test.com", "01012341234", UserRole.USER);
            userService.signup("admin", "1234", "admin", "admin@test.com", "01012341234", UserRole.ADMIN);

            categoryService.addCategory("경제");
            categoryService.addCategory("연예");
            categoryService.addCategory("스포츠");
            categoryService.addCategory("음식");
            categoryService.addCategory("유머");
            categoryService.addCategory("기타");
        };
    }
}
