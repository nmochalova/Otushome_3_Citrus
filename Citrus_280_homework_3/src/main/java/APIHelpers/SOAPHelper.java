package APIHelpers;

import com.consol.citrus.dsl.design.AbstractTestBehavior;
import features.PojoToXML;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollars;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollarsResponse;

import java.math.BigDecimal;

public class SOAPHelper extends AbstractTestBehavior {
  private final String SOAP_CLIENT = "soapClient";
  private final String NAMESPACES = "http://www.dataaccess.com/webservicesserver/";
  private String num;
  private String dollars;

  public SOAPHelper(String num, String dollars) {
    this.num = num;
    this.dollars = dollars;
  }

  @Override
  public void apply() {
    PojoToXML<Class<NumberToDollars>> ptxRq = new PojoToXML<>(); //ptxRq - pojo to xml request
    PojoToXML<Class<NumberToDollarsResponse>> ptxRs = new PojoToXML<>(); //ptxRq - pojo to xml response

    //Отправка soap-сообщения с использованием самописного маршелера PojoToXML
    soap()
            .client(SOAP_CLIENT)
            .send()
            .payload(ptxRq.convert(NumberToDollars.class, getNumberToDollarsRequest(),
                    NAMESPACES,
                    "NumberToDollars"));

    //Получение soap-ответа и сравнение его с эталонным xml
    soap()
            .client(SOAP_CLIENT)
            .receive()
            .xsdSchemaRepository("schemaRepositoryService")
            .payload(ptxRs.convert(NumberToDollarsResponse.class, getNumberToDollarsResponse(),
                    NAMESPACES,
                    "NumberToDollarsResponse"));
  }

  //Метод который возвращает эталонный pojo-объект NumberToDollars для сравнения c полем Num=15
  //Для генерации pojo на основе wsdl необходимо в pom.xml подключить плагин cxf-codegen-plugin
  //и далее из терминала сгенерить pojo командой: mvn generate-source
  public NumberToDollars getNumberToDollarsRequest() {
    NumberToDollars numberToDollars = new NumberToDollars();
    numberToDollars.setDNum(new BigDecimal(this.num));
    return numberToDollars;
  }

  //Метод который возвращает эталонный pojo-объект NumberToDollarsResponse для сравнения c ответом "fifteen dollars"
  public NumberToDollarsResponse getNumberToDollarsResponse() {
    NumberToDollarsResponse numberToDollarsResponse = new NumberToDollarsResponse();
    numberToDollarsResponse.setNumberToDollarsResult(this.dollars);
    return numberToDollarsResponse;
  }
}
