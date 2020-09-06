package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.ChangeAppConditionTests;
import tests.GetStartetTest;
import tests.MyListsTests;
import tests.SearchTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    ArticleTests.class,
    ChangeAppConditionTests.class,
    GetStartetTest.class,
    MyListsTests.class,
    SearchTests.class
})

public class TestSuites {
}
