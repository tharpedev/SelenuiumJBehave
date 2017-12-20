package org.qas.jbehave.example.junit;

import org.jbehave.core.embedder.Embedder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JUnitRunner {
    @Test
    public void runClasspathLoadedStoriesAsJUnit() {
        // Embedder defines the configuration and candidate steps
        Embedder embedder = new JUnitEmbedder();
        List<String> storyPaths = Arrays.asList(
                "src/test/resources/org/qas/jbehave/example/stories/simpleSearch.story" //"simpleSearch.story"
        ); // use StoryFinder to look up paths
        embedder.runStoriesAsPaths(storyPaths);
    }

    @Test
    public void simpleTest() {
        assert (true);
    }

}
