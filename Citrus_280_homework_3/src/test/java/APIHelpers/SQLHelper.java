package APIHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import javax.sql.DataSource;

public class SQLHelper extends TestNGCitrusTestRunner {
    @Autowired
    public DataSource sqlHelper;
    public TestContext context;

    @Test(description = "SQL Helper", enabled = true)
    @CitrusTest
    public void createUserTest() {
        this.context = citrus.createTestContext();

        query(action -> action
                .dataSource(sqlHelper)
                .statement("SELECT ID FROM TEST_TABLE;")
                .extract("ID", "ID")
        );

        echo("ID = " + context.getVariable("ID"));
    }
}
