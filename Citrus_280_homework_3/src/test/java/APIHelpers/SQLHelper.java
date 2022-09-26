package APIHelpers;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import javax.sql.DataSource;

/*
Установка docker-образа с postrgres
https://hub.docker.com/_/postgres

docker pull postgres
docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

Создание таблицы
create table test_table (integer id);
insert into test_table(id) values (1);
 */

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
                .extract("id", "ID")
        );

        echo("ID = ${ID}");
    }
}
