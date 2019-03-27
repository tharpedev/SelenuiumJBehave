package org.qas.jbehave.example.stories;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.junit.After;
import org.qas.jbehave.example.Utils.TestUtil;
import java.util.List;

public class ExampleStories extends JUnitStories {
    TestUtil testUtil = new TestUtil();
    @Override
    public Configuration configuration() {
        return testUtil.configuration();
    }

    // Here we specify the steps classes
    @Override
    public InjectableStepsFactory stepsFactory() {
        return testUtil.stepsFactory();
    }

    //Here we specify the stories
    @Override
    protected List<String> storyPaths() {
        return testUtil.storyPaths();
    }

    @After
    public void tearDown() throws Exception {
        testUtil.tearDown();
    }
}