package basic_ros_application;

import basic_ros_application.logic.PublisherOnLoop;
import basic_ros_application.msgs.StringMessage;
import org.apache.commons.logging.Log;
import org.ros.concurrent.CancellableLoop;
import org.ros.node.topic.Publisher;

public class ROSJavaPublisherNodeLoop extends CancellableLoop {
    private Log log;
    private Publisher<StringMessage> publisher;

    public ROSJavaPublisherNodeLoop(Log _log, Publisher<StringMessage> _publisher) {
        publisher = _publisher;
        log = _log;
    }

    protected void loop() throws InterruptedException{
        StringMessage message = publisher.newMessage();

        PublisherOnLoop.run(log, message);
        publisher.publish(message);
    }
}
