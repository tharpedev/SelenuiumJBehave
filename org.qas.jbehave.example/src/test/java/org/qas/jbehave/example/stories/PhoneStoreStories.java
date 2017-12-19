package org.qas.jbehave.example.stories;

import org.qas.jbehave.example.steps.PhoneStoreSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;
import com.qasymphony.qtest.automation.plugin.jbehave.core.LogCollector;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class PhoneStoreStories extends JUnitStories {

    @Override
    public Configuration configuration() {
        URL storyURL = null;
        try {
            String url = "file://" + System.getProperty("user.dir").replace('\\','/');
            //System.out.println(url);
            storyURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new MostUsefulConfiguration().useStoryLoader(new LoadFromRelativeFile(storyURL))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withReporters(new LogCollector())
                        .withFormats(Format.CONSOLE)
                        .withFormats(Format.HTML)
                        .withFormats(Format.XML));
    }

    // Here we specify the steps classes
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                new PhoneStoreSteps());
    }

    //Here we specify the stories
    @Override
    protected List<String> storyPaths() {
        return Arrays.asList(
                "src/test/resources/org/qas/jbehave/example/stories/phoneStore.story"
        );
    }

    @Override
    @Test
    public void run() {
        try {
            super.run();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}