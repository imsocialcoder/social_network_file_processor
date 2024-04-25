# Social Network File Processor
The Social Network File Processor is a Java application designed to process social reactions (events) posted to individual posts in a social networking platform. It handles the storage and cumulative aggregation of reaction data from daily files into a main events file.

## Overview
Each day, the application receives daily reaction files containing event data in two different formats: "v1_" and "v2_". The format of the daily file is indicated by the prefix used in the file name. The application's primary objective is to process these daily files, aggregate the reaction data, and update the main events file ("events.csv") with the cumulative reaction counts for each event type of each post.

## File Formats
- events.csv: Main events file containing cumulative reaction data.
  
  Format: post_id,event_type,count

- "v1_" Files: Daily files with the same format as events.csv.
  
  Format: post_id,event_type,count
- "v2_" Files: Daily files with a different format, where event information is shown as multiline.
  
  Format:
  
  `post_id`

  `event_type,count`
  
  `event_type,count`

## Algorithm
The application employs an algorithm capable of processing large datasets that do not fit into memory. It handles the dynamic nature of incoming data, allowing compatibility with new formats that may arise in the future.

## Requirements
- Java 21
- Maven

## Test
To run unit test go to project folder and run mvn test:

`cd social_network_file_processor; 
mvn test`

OR

`mvn clean install`

Unit tests are using the following files which are on project root folder (the folder where pom.xml exists):
- v1_input_invalid_test.csv
- v1_input_test.csv
- v2_input_test.csv
- v2_input_invalid_test.csv
- events.csv

## Usage
Before run the application, please make sure that events.csv (on root folder where pom.xml exists) has correct content.

Sample content for the events.csv:

b6651d07-6b0d-11e9-8ebb-06bad62f3c64,Like,5 

f591d90d-6b0d-11e9-8ebb-06bad62f3c64,Unlike,50

0288af76-6b0e-11e9-8ebb-06bad62f3c64,Like,28

b6651d07-6b0d-11e9-8ebb-06bad62f3c64,Unlike,3

f591d90d-6b0d-11e9-8ebb-06bad62f3c64,Like,500



## Running the application:

Run the following command:

`cd social_network_file_processor;
mvn exec:java`

The application is using the following files which are on project root folder (the folder where pom.xml exists):
- v1_input.csv
- v2_input.csv
- events.csv

Result will be in events.csv file.
