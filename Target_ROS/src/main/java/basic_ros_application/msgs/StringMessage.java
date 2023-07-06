package basic_ros_application.msgs;

import org.ros.internal.message.Message;

public interface StringMessage extends Message {

    public abstract String getData();

    public abstract void setData(String val);

    String _TYPE = "basic_ros_application/msgs/StringMessage";

    String _DEFINITION = "string data\n";
}
