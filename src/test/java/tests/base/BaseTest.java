package tests.base;

import api.utils.JsonReader;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        JsonReader.loadData("src/test/java/tests/data/data.json");
    }
}