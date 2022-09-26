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
import pojo.http.UserToCourse;

import java.util.ArrayList;
import java.util.List;


public class testMockGetListUsers extends TestNGCitrusTestRunner {
    public TestContext context;


    @Test(description = "Получение списка пользователей", enabled = true)
    @CitrusTest
    public void mockGetListUsers(){
        this.context = citrus.createTestContext();

        //При помощи http-клиента отправляем get-запрос в нашу заглушку restServer
        http(httpActionBuilder -> httpActionBuilder
                .client("restClient")
                .send()
                .get("/user/get/all")
                .fork(true) //!!! Добавлен асинхрон для работы с localhost: дожидаться ответа после взаимодействия с сервером
        );

        //Шаги взаимодействия с заглушкой.
        //1. Сначала принимаем get-запрос от клиента
        http(httpActionBuilder -> httpActionBuilder
                .server("restServer")
                .receive()
                .get());
        //2. Отправляем хардкод-ответ по запрошенным данным в соответствии с контрактом
        http(httpActionBuilder -> httpActionBuilder
                .server("restServer")
                .send()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .payload("[\n" +
                        "  {\n" +
                        "    \"name\": \"Test user\",\n" +
                        "    \"course\": \"QA\",\n" +
                        "    \"email\": \"test@test.test\",\n" +
                        "    \"age\": 23\n" +
                        "  }\n" +
                        "]"));

        //Http-клиент получает ответ и валидирует его (проверка по схеме)
        http(httpActionBuilder -> httpActionBuilder
                .client("restClient")
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .payload(getJsonData(),"objectMapper")
        );
    }

    public List<UserToCourse> getJsonData() {
        List<UserToCourse> userToCourseList = new ArrayList<>();

        UserToCourse userToCourse = new UserToCourse();
        userToCourse.setName("Test user");
        userToCourse.setCourse("QA");
        userToCourse.setEmail("test@test.test");
        userToCourse.setAge(23);
        userToCourseList.add(userToCourse);

        return userToCourseList;
    }
}