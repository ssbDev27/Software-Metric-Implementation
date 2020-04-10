# MeasurementProject
Team I


Directory Structure
data
This folder contains all the data related to the test subjects and metrics. The first hierarchy is the test subjects and the second level hierarchy is the metrics for those test subjects. In the second level hierarchy, jacoco-ut contains the data about the Metric 1, 2 and 4. jira or github contains the data about Metric 6. pit-reports contains the data about Metric 3. And DLOC contains the data about Metric 5.

configurations
This folder contains all the information needed to configure the plugins we have used.

Correlations
This folder Correlations contains Excel files to find out the correlations between individual metrics. All the correlations for Metric 1 to 4 are done in individual files of the test subjects. And the correlations for Metric 5 and 6 are done in Correlations.xlsx.

Calculations
Maintenance Effort
We have used the ruby script in order to calculate the DLOC for Metric 5. It has been done on MacOS 10.14.3 with default ruby command line tool (v2.3.7). This script is located in configurations folder.

Correlations
We did not explicitly use any code or script to calculate our correlations. We have gathered all the data together in excel sheets and have used the excel functions to get the results.
