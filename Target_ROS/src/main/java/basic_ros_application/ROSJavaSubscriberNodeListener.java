package basic_ros_application;

import basic_ros_application.logic.SubscriberOnMessage;
import basic_ros_application.msgs.StringMessage;
import org.apache.commons.logging.Log;
import org.ros.message.MessageListener;

public class ROSJavaSubscriberNodeListener implements MessageListener<StringMessage> {

    Log log;

    public ROSJavaSubscriberNodeListener(Log _log) {
        log = _log;
    }

    public void onNewMessage(StringMessage message) {
        SubscriberOnMessage.run(log, message);
    }
}
