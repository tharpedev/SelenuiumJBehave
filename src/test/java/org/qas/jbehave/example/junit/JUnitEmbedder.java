package org.qas.jbehave.example.junit;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.junit.After;
import org.qas.jbehave.example.Utils.TestUtil;

import java.util.List;


public class JUnitEmbedder extends Embedder {

    TestUtil testUtil;

    public  JUnitEmbedder(TestUtil testUtil) {
        this.testUtil = testUtil;
    }
    @Override
    public EmbedderControls embedderControls() {
        return new EmbedderControls().doIgnoreFailureInStories(true).doIgnoreFailureInView(true);
    }

    @Override
    public Configuration configuration() {
        return  testUtil.configuration();
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return testUtil.stepsFactory();
    }

    @After
    public void tearDown() throws Exception {
        testUtil.tearDown();
    }

    @Override
    public void runStoriesAsPaths(List<String> storyPaths) {
        super.runStoriesAsPaths(testUtil.storyPaths());
    }
}
