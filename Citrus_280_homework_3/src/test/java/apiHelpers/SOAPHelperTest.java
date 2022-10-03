package apiHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import org.testng.annotations.Test;

public class SOAPHelperTest extends TestNGCitrusTestDesigner {
    public TestContext context;

    @Test(description = "SOAP тест: число 20", enabled = true)
    @CitrusTest
    public void getTestActions() {
        this.context = citrus.createTestContext();
        //SOME STEPS
        //...

        //HTTP HELPER. Check convert number to dollars
        String num = "10";                  //что конвертируем
        String dollars = "ten dollars";     //ожидаемый результат
        applyBehavior(new SOAPHelper(num,dollars));

        //SOME STEPS
        //...

    }


}
