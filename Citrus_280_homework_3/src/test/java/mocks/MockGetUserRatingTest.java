package mocks;

/* Из лекции по citrus:
Заглушка restServer инициализируется при старте теста и ждет нас на порту 5555.
В citrus заглушка делается под каждый тест, поднимается на портах, тест заканчивается, заглушки закрываются.
 */

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;
import pojo.http.GetUserRatingResponse;

public class MockGetUserRatingTest extends TestNGCitrusTestRunner {
    public TestContext context;


    @Test(description = "Получение оценки пользователя", enabled = true)
    @CitrusTest
    public void mockTestGetUser(){
        this.context = citrus.createTestContext();

        //При помощи http-клиента отправляем get-запрос в нашу заглушку restServer
        String userId = context.getVariable("userId");
        http(MockGetUserRating.mockGetListUserRatingFork(userId));

        //Шаги взаимодействия с заглушкой.
        //1. Сначала принимаем get-запрос от клиента
        http(MockGetUserRating.mockRestServerReceiveGet());
        //2. Отправляем хардкод-ответ по запрошенным данным в соответствии с контрактом
        http(MockGetUserRating.mockRestServerResponseUserRating());

        //Http-клиент получает ответ и валидирует его (проверка по схеме)
        GetUserRatingResponse userRating = getJsonData();
        http(MockGetUserRating.mockRestClientReceiveUserRating(userRating));
    }

    public GetUserRatingResponse getJsonData() {
        GetUserRatingResponse rating = new GetUserRatingResponse();

        rating.setName("Test user");
        rating.setScore(78);

        return rating;
    }
}
