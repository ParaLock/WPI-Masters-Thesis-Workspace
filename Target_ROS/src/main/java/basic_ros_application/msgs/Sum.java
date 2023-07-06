package basic_ros_application.msgs;

import org.ros.internal.message.Message;

public interface Sum extends Message {

    String _DEFINITION = "int64 a\nint64 b\n---\nint64 sum\n";

    String _TYPE = "basic_ros_application/msgs/Sum";
}
