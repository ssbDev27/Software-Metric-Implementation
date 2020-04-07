We have used Pit-Test tool to calculate the mutation testing metrics.

We used the Pit-test with the following steps:

1. Install pitest plugin by adding the plugin parameter into `pom.xml` of the project.
2. Run the `clean` task in the maven builder
3. Run the `test` task in the maven builder
4. Results are generated in `target/pit-reports` directory

Since we are using project that uses only maven builder, we configure the pitest plugin the same way in all the projects.

We add the dependency in the build section of the `pom.xml`,

```
     <plugin>
        <groupId>org.pitest</groupId>
        <artifactId>pitest-maven</artifactId>
        <version>${pitest.version}</version>
        <executions>
          <execution>
            <id>pit-report</id>
            <phase>test</phase>
            <goals>
              <goal>mutationCoverage</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```

We have to run `test` task for generating the coverage report.