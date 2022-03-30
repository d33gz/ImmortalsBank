package revature.d33gz.allTests;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@SuppressWarnings("deprecation")
@RunWith(JUnitPlatform.class)
@SelectClasses({revature.d33gz.daoTests.ClientDAOTests.class, revature.d33gz.daoTests.AccountDAOTests.class, revature.d33gz.miscTests.miscellaneousTests.class,})
public class AllTests {

}