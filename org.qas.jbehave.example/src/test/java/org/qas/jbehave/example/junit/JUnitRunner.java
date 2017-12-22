package org.qas.jbehave.example.junit;

import org.jbehave.core.embedder.Embedder;
import org.junit.After;
import org.junit.Test;
import org.qas.jbehave.example.Utils.TestUtil;


public class JUnitRunner {
    TestUtil testUtil = new TestUtil();


    @Test
    public void runClasspathLoadedStoriesAsJUnit() {
        // Embedder defines the configuration and candidate steps
        Embedder embedder = new JUnitEmbedder(testUtil);
//        List<String> storyPaths = Arrays.asList(
//                //"src/test/resources/org/qas/jbehave/example/stories/simpleSearch.story" //"simpleSearch.story"
//                "src/test/resources/org/qas/jbehave/example/stories/phoneStore.story"
//                ,"src/test/resources/org/qas/jbehave/example/stories/simpleSearch.story"
//        ); // use StoryFinder to look up paths
        embedder.runStoriesAsPaths(null);

    }

    @Test
    public void simpleTest() {
        assert (true);
    }

    @After
    public void tearDown() throws Exception {
        testUtil.tearDown();
    }




}
