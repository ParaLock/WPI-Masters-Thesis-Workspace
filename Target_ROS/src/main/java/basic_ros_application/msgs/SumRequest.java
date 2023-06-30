package basic_ros_application.msgs;

import org.ros.internal.message.Message;

public interface SumRequest extends Message {
    java.lang.String _TYPE = "basic_ros_application/msgs/SumRequest";
    java.lang.String _DEFINITION = "int64 a\nint64 b\n";

    long getA();

    void setA(long var1);

    long getB();

    void setB(long var1);
}
