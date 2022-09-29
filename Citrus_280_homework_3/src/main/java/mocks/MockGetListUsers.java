package mocks;

import com.consol.citrus.dsl.builder.BuilderSupport;
import com.consol.citrus.dsl.builder.HttpActionBuilder;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import pojo.http.Course;
import pojo.http.UserToCourse;

import java.util.List;

public class MockGetListUsers extends BaseMock{
  public static BuilderSupport<HttpActionBuilder> mockGetListUsersFork() {
    return httpActionBuilder -> httpActionBuilder
            .client(REST_CLIENT)
            .send()
            .get(PATH_GET_USER)
            .fork(true); //!!! Добавлен асинхрон для работы с localhost: дожидаться ответа после взаимодействия с сервером
  }

  public static BuilderSupport<HttpActionBuilder> mockRestServerReceiveGet() {
    return httpActionBuilder -> httpActionBuilder
            .server(REST_SERVER)
            .receive()
            .get();
  }

  public static BuilderSupport<HttpActionBuilder> mockRestServerResponseListUsers() {
    return httpActionBuilder -> httpActionBuilder
            .server(REST_SERVER)
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
                    "]");
  }

  public static BuilderSupport<HttpActionBuilder> mockRestClientReceiveListCourses( List<UserToCourse> usersList) {
   return httpActionBuilder -> httpActionBuilder
           .client(REST_CLIENT)
           .receive()
           .response(HttpStatus.OK)
           .messageType(MessageType.JSON)
           .payload(usersList,"objectMapper");
  }
}
