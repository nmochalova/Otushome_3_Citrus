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
import pojo.http.Course;

import java.util.ArrayList;
import java.util.List;


public class MockGetListCoursesTest extends TestNGCitrusTestRunner {
    public TestContext context;


    @Test(description = "Получение списка курсов", enabled = true)
    @CitrusTest
    public void mockGetListCourses(){
        this.context = citrus.createTestContext();

        //При помощи http-клиента отправляем get-запрос в нашу заглушку restServer
        http(httpActionBuilder -> httpActionBuilder
                .client("restClient")
                .send()
                .get("/cource/get/all")
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
                        "    \"name\": \"QA java\",\n" +
                        "    \"price\": 15000\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"name\": \"Java\",\n" +
                        "    \"price\": 12000\n" +
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

    public List<Course> getJsonData() {
        List<Course> courses = new ArrayList<>();

        Course course1 = new Course();
        course1.setName("QA java");
        course1.setPrice(15000);
        courses.add(course1);

        Course course2 = new Course();
        course2.setName("Java");
        course2.setPrice(12000);
        courses.add(course2);

        return courses;
    }
}
