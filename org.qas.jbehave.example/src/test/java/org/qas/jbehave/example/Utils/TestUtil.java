package org.qas.jbehave.example.Utils;

import com.qasymphony.qtest.automation.plugin.jbehave.core.LogCollector;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qas.jbehave.example.steps.PhoneStoreSteps;
import org.qas.jbehave.example.steps.SimpleSearchSteps;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Ignore
public class TestUtil{

    WebDriver driver;

    public Configuration configuration() {
        URL storyURL = null;
//        try {
            //String url = "file://" + System.getProperty("user.dir").replace('\\','/');
            //System.out.println(url);

            storyURL = this.getClass().getResource("/org/qas/jbehave/example/stories");
            //System.out.println(url);
            //storyURL = new URL("file://" + url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        return new MostUsefulConfiguration().useStoryLoader(new LoadFromRelativeFile(storyURL))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withReporters(new LogCollector())
                        .withFormats(Format.CONSOLE)
                        .withFormats(Format.HTML)
                        .withFormats(Format.XML));
    }

    // Here we specify the steps classes
    public InjectableStepsFactory stepsFactory() {
        System.setProperty("webdriver.chrome.driver", buildWebDriverPath());
        driver = new ChromeDriver();
        return new InstanceStepsFactory(configuration(),
                new PhoneStoreSteps(),
                new SimpleSearchSteps(driver));
    }

    //Here we specify the stories
    public List<String> storyPaths() {
        return Arrays.asList(
                "phoneStore.story"
                ,"simpleSearch.story"
        );
    }

    public void tearDown() throws Exception {
        if (null != driver) {
            driver.close();
        }
        driver = null;
    }

    private String buildWebDriverPath() {
        String osName =  System.getProperty("os.name").toLowerCase();
        URL url = null;
        System.out.println("Os name: " + osName);
        String ret = "";
        if (osName.contains("win")) {
            url = this.getClass().getResource("/webdriver/windows/chromedriver.exe");
        } else if (osName.contains("mac")){
            url = this.getClass().getResource("/webdriver/mac/chromedriver");
        } else {
            url = this.getClass().getResource("/webdriver/linux/chromedriver");
        }
        return (null != url) ? url.getPath() : "";
    }
}
