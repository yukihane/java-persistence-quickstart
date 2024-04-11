package boilerplate.dao;

import boilerplate.AppConfig;
import boilerplate.TestEnvironment;
import boilerplate.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(TestEnvironment.class)
public class EmployeeDaoTest {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testSelectInIds() {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(
                () -> {
                    List<Integer> ids = Arrays.asList(1,2);
                    List<Employee> employee = dao.selectInIds(ids);
                    assertEquals(2, employee.size());
                });
    }
    @Test
    public void testSelectInEmpty() {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(
                () -> {
                    List<Integer> ids = new ArrayList<>();
                    List<Employee> employee = dao.selectInIds(ids);
                    assertEquals(0, employee.size());
                });
    }
}
