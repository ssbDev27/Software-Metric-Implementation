We have used Jacoco tool to calculate the Statement coverage, Branch coverage and Cyclomatic Complexity:

We have to follow certain steps to compute the results from Jacoco:

1. Install Jacoco dendpency by adding the plugin parameter into `pom.xml` of the project.
2. Run the `clean` task in maven builder
3. Run the `test` task in the maven builder
4. Results are generated in `target/reports` directory

Since we are using project that uses only maven builder, we configure the jacoco the same way in all the project.

We add the dependency as,
```
      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>${jacoco.version}</version>
        <executions>
          <execution>
            <id>prepare-agent</id>
            <goals>
              <goal>prepare-agent</goal>
            </goals>
          </execution>
          <execution>
            <id>post-unit-test</id>
            <phase>test</phase>
            <goals>
              <goal>report</goal>
            </goals>
            <configuration>
              <!--Sets the path to the file which contains the execution data.-->
              <dataFile>target/jacoco.exec</dataFile>
              <!--Sets the output directory for the code coverage report.-->
              <outputDirectory>target/jacoco-ut</outputDirectory>
            </configuration>
          </execution>
        </executions>
      </plugin>
```

Note here that we add the report generation in `test` phase. So we have to run `test` task to generate the reports.

We can also use the IntelliJ's built in Jacoco plugin to generate the report. We just have to put the code coverage option to Jacoco in the run configurations of the task `test`. And then when we execute the task `test` with coverage, we can see the jacoco results in a separate window which we can export later to csv or html.