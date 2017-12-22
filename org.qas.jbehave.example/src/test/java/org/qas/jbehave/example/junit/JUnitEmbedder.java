package org.qas.jbehave.example.junit;

import com.qasymphony.qtest.automation.plugin.jbehave.core.LogCollector;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qas.jbehave.example.Utils.TestUtil;
import org.qas.jbehave.example.steps.SimpleSearchSteps;

import java.net.MalformedURLException;
import java.net.URL;
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

//    @Override
//    public Configuration configuration() {
//        Class<? extends JUnitEmbedder> embedderClass = this.getClass();
//        return new MostUsefulConfiguration()
//                .useStoryLoader(new LoadFromClasspath(embedderClass.getClassLoader()))
//                .useStoryReporterBuilder(new StoryReporterBuilder()
//                        .withReporters(new LogCollector())
//                        .withFormats(Format.CONSOLE)
//                        .withFormats(Format.HTML)
//                        .withFormats(Format.XML));
//    }
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
