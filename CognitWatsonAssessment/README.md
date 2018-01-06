# CognitWatsonNLCAssessment

IBM Watson Natural Language Classifier applies cognitive computing techniques to return the top matching predefined 
classes for short text input. I create and train a classifier on Bluemix with the training data to can respond with 
matched answer even question is slightly different from trained data.

Maven dependency
<dependency>
  <groupId>com.ibm.watson.developer_cloud</groupId>
  <artifactId>java-sdk</artifactId>
  <version>4.2.1</version>
</dependency>

Getting started

follow the below steps to run this application

•	Go to •	https://cognitwatsonassessment.eu-gb.mybluemix.net
•	Enter Question in search box
•	Click ask button and result will appear in the same screen

to create natural language classifier service and consume it within your application

1-You need a Bluemix account to login
2-select from catalog 'Natural Language Classifier' under watson and create service 
3-get Service credentials to use in connecting with service from you app (you can use token instead of 
sending Service credentials with each service call)
4-create classifier and upload or add training data to train it-get classifier id to use in test examples
