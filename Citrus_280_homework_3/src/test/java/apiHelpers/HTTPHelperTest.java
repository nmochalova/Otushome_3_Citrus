package apiHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import org.testng.annotations.Test;

public class HTTPHelperTest extends TestNGCitrusTestDesigner {
  @Test(description = "Авторизация пользователя", enabled = true)
  @CitrusTest
  public void createUserTestBehavior() {
    //SOME STEPS
    //...

    //HTTP HELPER. Login User
    String email = "eve.holt@reqres.in";
    String password = "cityslicka";
    String token = "QpwL5tke4Pnpja7X4";

    applyBehavior(new HTTPHelper(email,password,token));

    //SOME STEPS
    //...
  }
}
