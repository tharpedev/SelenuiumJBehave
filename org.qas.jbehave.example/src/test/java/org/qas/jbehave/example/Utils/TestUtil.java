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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

@Ignore
public class TestUtil{

    WebDriver driver;
    String chromeDriverPath;

    public Configuration configuration() {
        URL storyURL = null;
        try {
            storyURL = isRunWithJarFile() ? new URL("file://" + System.getProperty("java.io.tmpdir")) : this.getClass().getResource("/org/qas/jbehave/example/stories");
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
    public InjectableStepsFactory stepsFactory()  {
        try {
            chromeDriverPath = buildWebDriverPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
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
            driver.quit();
        }
        if (isRunWithJarFile() && null != chromeDriverPath) {
            Files.delete(Paths.get(chromeDriverPath));
        }
        chromeDriverPath = null;
        driver = null;
    }

    private String buildWebDriverPath() throws IOException {
        String osName =  System.getProperty("os.name").toLowerCase();
        System.out.println("Os name: " + osName);

        final HashMap<String, String> mapEntries = new HashMap<>();
        mapEntries.put("phoneStore.story", "org/qas/jbehave/example/stories/phoneStore.story");
        mapEntries.put("simpleSearch.story","org/qas/jbehave/example/stories/simpleSearch.story");

        if (osName.contains("win")) {
            mapEntries.put("chromedriver", "webdriver/windows/chromedriver.exe");
        } else if (osName.contains("mac")){
            mapEntries.put("chromedriver","webdriver/mac/chromedriver");
        } else {
            mapEntries.put("chromedriver","webdriver/linux/chromedriver");
        }
        String destDir = System.getProperty("java.io.tmpdir");
        String ret = extractJarFile(destDir, mapEntries);
        return ret;
    }


    private boolean isRunWithJarFile() {
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        return jarFile.isFile();
    }

    private String extractJarFile(String destDir, HashMap<String, String> mapEntries) throws IOException {
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        //final File jarFile = new File("C:\\QASProjects\\GitLabs\\jbehave-agent-sample\\org.qas.jbehave.example\\target\\org.fazlan.jbehave.exmaple-1.0-SNAPSHOT-tests.jar");

        if(jarFile.isFile()) {  // Run with JAR file
            final JarFile jar = new JarFile(jarFile);
            java.util.Enumeration<java.util.jar.JarEntry> enu = jar.entries();
            for(Map.Entry<String, String> mapEntry : mapEntries.entrySet()) {
                String key = mapEntry.getKey();
                String value = mapEntry.getValue();

                ZipEntry entry = jar.getEntry(value);
                String tempFile = Paths.get(destDir,key).toString();
                InputStream in = new BufferedInputStream(jar.getInputStream(entry));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));
                byte[] buffer = new byte[2048];
                for (;;)  {
                    int nBytes = in.read(buffer);
                    if (nBytes <= 0) break;
                    out.write(buffer, 0, nBytes);
                }
                out.flush();
                out.close();
                in.close();
            }
            jar.close();
            return Paths.get(destDir, "chromedriver").toString();
        } else { // Run with IDE
            String value = "/" + mapEntries.get("chromedriver");

            URL url = null;
            try  {
                url = this.getClass().getResource(value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return (null != url) ? url.getPath() : "";
        }
    }
}
