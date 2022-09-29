package APIHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import features.PojoToXML;
import org.testng.annotations.Test;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollars;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollarsResponse;

import java.math.BigDecimal;

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
