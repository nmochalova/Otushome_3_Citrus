package APIHelpers;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.design.AbstractTestBehavior;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SQLHelper extends AbstractTestBehavior {
  private DataSource sqlHelper;
  private TestContext context;

  public SQLHelper(DataSource sqlHelper,TestContext context) {
    this.sqlHelper=sqlHelper;
    this.context = context;
  }

  @Override
  public void apply() {
    query(sqlHelper)
            .statement("SELECT ID FROM TEST_TABLE;")
            .extract("id", "currentId");

    echo("currentId = ${currentId}");
    context.setVariable("currentId","${currentId}"); //сетим id в контекст
  }
}
