package apiHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import javax.sql.DataSource;

public class SQLHelperTest extends TestNGCitrusTestDesigner {
    @Autowired
    public DataSource sqlHelper;
    public TestContext context;

    @Test(description = "SQL Helper", enabled = true)
    @CitrusTest
    public void createUserTest() {
        this.context = citrus.createTestContext();
        //SOME STEPS
        //...

        //HTTP HELPER. Check convert number to dollars
        applyBehavior(new SQLHelper(sqlHelper,context));
        echo("createUserTest \"currentId\" = " + context.getVariable("currentId"));

        //SOME STEPS
        //...
    }
}
