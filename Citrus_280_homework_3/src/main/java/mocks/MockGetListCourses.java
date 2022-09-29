package mocks;

import com.consol.citrus.dsl.builder.BuilderSupport;
import com.consol.citrus.dsl.builder.HttpActionBuilder;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import pojo.http.Course;

import java.util.List;

public class MockGetListCourses extends BaseMock{
  public static BuilderSupport<HttpActionBuilder> mockGetListCoursesFork() {
    return httpActionBuilder -> httpActionBuilder
            .client(REST_CLIENT)
            .send()
            .get(PATH_GET_COURSE)
            .fork(true); //!!! Добавлен асинхрон для работы с localhost: дожидаться ответа после взаимодействия с сервером
  }

  public static BuilderSupport<HttpActionBuilder> mockRestServerReceiveGet() {
    return httpActionBuilder -> httpActionBuilder
            .server(REST_SERVER)
            .receive()
            .get();
  }

  public static BuilderSupport<HttpActionBuilder> mockRestServerResponseListCourses() {
    return httpActionBuilder -> httpActionBuilder
            .server(REST_SERVER)
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
                    "]");
  }

  public static BuilderSupport<HttpActionBuilder> mockRestClientReceiveListCourses(List<Course> courseList) {
   return httpActionBuilder -> httpActionBuilder
          .client(REST_CLIENT)
          .receive()
          .response(HttpStatus.OK)
          .messageType(MessageType.JSON)
          .payload(courseList,"objectMapper");
  }
}
