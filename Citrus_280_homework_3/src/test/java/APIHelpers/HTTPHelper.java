package APIHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

//HTTP Helper - Авторизация пользователя
public class HTTPHelper extends TestNGCitrusTestRunner {
    private TestContext context;
    private String email = "eve.holt@reqres.in";
    private String password = "cityslicka";
    private String token = "QpwL5tke4Pnpja7X4";

    @Test(description = "Авторизация пользователя", enabled = true)
    @CitrusTest
    public void createUserTest() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client("restClientReqres")
                .send()
                .post("login")
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"email\": \"" + email + "\",\n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "}")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client("restClientReqres")
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .validate("$.token",token)   //валидация отдельных полей: проверяем совпадение полей
                .extractFromPayload("$.token","token")  //извлекаем token
        );

        echo("token = ${token}"); //выводим token в консоль
        context.setVariable("token","${token}"); //сетим token в контекст
    }
}
