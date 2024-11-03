package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    public void test() {
        //Given
        when(userRepository.findByUsername("gold")).thenReturn(User.builder().username("gold").password("pass").build());

        //When
        User user = userService.findByUsername("gold");

        //Then
        assertNotNull(user);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "3, 5, 8"
    })
    void testSum(int a, int b, int expected) {
        assertEquals(expected, a+ b);
    }
}
