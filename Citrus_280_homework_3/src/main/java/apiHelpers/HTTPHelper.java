package apiHelpers;

import com.consol.citrus.dsl.design.AbstractTestBehavior;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;

//Этот http-helper авторизуется в системе с заданными email и password и сравнивает полученный token с ожидаемым
public class HTTPHelper extends AbstractTestBehavior {
  private final String REST_CLIENT_REQRES = "restClientReqres";
  private String email;
  private String password;
  private String token;

  public HTTPHelper(String email, String password, String token) {
    this.email = email;
    this.password = password;
    this.token = token;
  }

  @Override
  public void apply() {
    http()
            .client(REST_CLIENT_REQRES)
            .send()
            .post("login")
            .messageType(MessageType.JSON)
            .payload("{\n" +
                    "    \"email\": \"" + email + "\",\n" +
                    "    \"password\": \"" + password + "\"\n" +
                    "}");

    http()
            .client(REST_CLIENT_REQRES)
            .receive()
            .response(HttpStatus.OK)
            .messageType(MessageType.JSON)
            .validate("$.token",token)   //валидация отдельных полей: проверяем совпадение полей
            .extractFromPayload("$.token","token");  //извлекаем token

     echo("token = ${token}"); //выводим token в консоль
  }
}
