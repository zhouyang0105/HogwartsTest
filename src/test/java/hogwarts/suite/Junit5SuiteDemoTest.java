package hogwarts.suite;

import hogwarts.testcaseinfo.testcase.Junit5Demo1Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
/*@SelectPackages({
        "com.testcaseinfo"
})
@IncludePackages({
        "com.testcaseinfo.testcase1",
        "com.testcaseinfo.testcase2"
})*/

@SelectClasses({
        Junit5Demo1Test.class
})
@IncludeTags({
    "testdemo"
})
public class Junit5SuiteDemoTest {
}
