package org.regitiny.catiny.messenger.cucumber;

import org.junit.runner.RunWith;

import org.regitiny.catiny.messenger.AbstractCassandraTest;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/features")

public class CucumberIT extends AbstractCassandraTest {

}
