demo-jms
========

## Install:

`mvn clean install`

## Usage

### Run ActiveMQ:

`mvn activemq:run`

### Queue sender/receiver:

In one shell, run:

`java -cp target/demo-jms-*-uberjar.jar com.romajs.jms.QueueReceiver <queue name>`

Then in another shell, run:

`java -cp target/demo-jms-*-uberjar.jar com.romajs.jms.QueueSender <queue name> <text message>`

### Topic publish/subscriber:

In one shell, run:

`java -cp target/demo-jms-*-uberjar.jar com.romajs.jms.TopicSubscriber <topic name>`

Then in another shell, run:

`java -cp target/demo-jms-*-uberjar.jar com.romajs.jms.TopicPublish <topic name> <text message>`


### Topic publish/subscriber (durable):

To not lose any messages when your subscriber is offline, you must make it durable ;)

`java -cp target/demo-jms-*-uberjar.jar com.romajs.jms.DurableTopicSubscriber <topic name> <subscription name> <client id>`
