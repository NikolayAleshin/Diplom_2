package data;

import model.User;
import org.apache.commons.lang3.RandomStringUtils;

public class DataGeneratedUsers {

    public static User generatorNewUser() {
        String userEmail = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        // с помощью библиотеки RandomStringUtils генерируем пароль
        String userPassword = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
        String userName = RandomStringUtils.randomAlphabetic(10);

        return new User(userEmail + "@yandex.ru", userPassword, userName);
    }
}
