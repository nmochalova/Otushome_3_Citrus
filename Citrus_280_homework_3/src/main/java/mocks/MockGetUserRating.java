package mocks;

import com.consol.citrus.dsl.builder.BuilderSupport;
import com.consol.citrus.dsl.builder.HttpActionBuilder;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import pojo.http.GetUserRatingResponse;
import pojo.http.UserToCourse;

import java.util.List;

public class MockGetUserRating extends BaseMock{
  public static BuilderSupport<HttpActionBuilder> mockGetListUserRatingFork(String userId) {
    return httpActionBuilder -> httpActionBuilder
            .client(REST_CLIENT)
            .send()
            .get(PATH_GET_USER_RATING+userId)
            .fork(true); //!!! Добавлен асинхрон для работы с localhost: дожидаться ответа после взаимодействия с сервером
  }

  public static BuilderSupport<HttpActionBuilder> mockRestServerReceiveGet() {
    return httpActionBuilder -> httpActionBuilder
            .server(REST_SERVER)
            .receive()
            .get();
  }

  public static BuilderSupport<HttpActionBuilder> mockRestServerResponseUserRating() {
    return httpActionBuilder -> httpActionBuilder
            .server("restServer")
            .send()
            .response(HttpStatus.OK)
            .messageType(MessageType.JSON)
            .payload("{\n" +
                    "  \"name\": \"Test user\",\n" +
                    "  \"score\": 78\n" +
                    "}");
  }

  public static BuilderSupport<HttpActionBuilder> mockRestClientReceiveUserRating(GetUserRatingResponse userRating) {
   return httpActionBuilder -> httpActionBuilder
           .client("restClient")
           .receive()
           .response(HttpStatus.OK)
           .messageType(MessageType.JSON)
           .payload(userRating,"objectMapper");
  }
}
