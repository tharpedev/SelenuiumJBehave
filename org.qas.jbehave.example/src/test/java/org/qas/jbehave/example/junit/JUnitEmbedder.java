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
import org.qas.jbehave.example.steps.SimpleSearchSteps;

import java.net.MalformedURLException;
import java.net.URL;


public class JUnitEmbedder extends Embedder {
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
public Configuration configuration() {
    URL storyURL = null;
    try {
        String url = "file://" + System.getProperty("user.dir").replace('\\','/');
        //System.out.println(url);
        storyURL = new URL(url);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }

    return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromRelativeFile(storyURL))
            .useStoryReporterBuilder(new StoryReporterBuilder()
                    .withReporters(new LogCollector())
                    .withFormats(Format.CONSOLE)
                    .withFormats(Format.HTML)
                    .withFormats(Format.XML));
}

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new SimpleSearchSteps());
    }


}
