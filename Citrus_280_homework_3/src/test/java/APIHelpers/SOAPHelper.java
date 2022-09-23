package APIHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import features.PojoToXML;
import org.testng.annotations.Test;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollars;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollarsResponse;

import java.math.BigDecimal;


public class SOAPHelper extends TestNGCitrusTestRunner {
    public TestContext context;

    @Test(description = "SOAP тест: число 20", enabled = true)
    @CitrusTest
    public void getTestActions() {
        this.context = citrus.createTestContext();

        PojoToXML<Class<NumberToDollars>> ptxRq = new PojoToXML<>(); //ptxRq - pojo to xml request
        PojoToXML<Class<NumberToDollarsResponse>> ptxRs = new PojoToXML<>(); //ptxRq - pojo to xml response

        //Отправка soap-сообщения с использованием самописного маршелера PojoToXML
        soap(soapActionBuilder -> soapActionBuilder
                .client("soapClient")
                .send()
                .payload(ptxRq.convert(NumberToDollars.class, getNumberToDollarsRequest(),
                        "http://www.dataaccess.com/webservicesserver/",
                        "NumberToDollars"))
        );

        //Получение soap-ответа и сравнение его с эталонным xml
        soap(soapActionBuilder -> soapActionBuilder
                .client("soapClient")
                .receive()
                .xsdSchemaRepository("schemaRepositoryService")
                .payload(ptxRs.convert(NumberToDollarsResponse.class, getNumberToDollarsResponse(),
                        "http://www.dataaccess.com/webservicesserver/",
                        "NumberToDollarsResponse"))
        );
    }

    //Метод который возвращает эталонный pojo-объект NumberToDollars для сравнения c полем Num=15
    //Для генерации pojo на основе wsdl необходимо в pom.xml подключить плагин cxf-codegen-plugin
    //и далее из терминала сгенерить pojo командой: mvn generate-source
    public NumberToDollars getNumberToDollarsRequest() {
        NumberToDollars numberToDollars = new NumberToDollars();
        numberToDollars.setDNum(new BigDecimal("20"));
        return numberToDollars;
    }

    //Метод который возвращает эталонный pojo-объект NumberToDollarsResponse для сравнения c ответом "fifteen dollars"
    public NumberToDollarsResponse getNumberToDollarsResponse() {
        NumberToDollarsResponse numberToDollarsResponse = new NumberToDollarsResponse();
        numberToDollarsResponse.setNumberToDollarsResult("twenty dollars");
        return numberToDollarsResponse;
    }
}
