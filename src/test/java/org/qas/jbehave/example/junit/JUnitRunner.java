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
        embedder.runStoriesAsPaths(null);
        System.out.println("--- FINISHED ---");
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
