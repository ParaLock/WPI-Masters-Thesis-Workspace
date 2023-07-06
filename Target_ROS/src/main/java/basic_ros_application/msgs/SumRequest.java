package basic_ros_application.msgs;

import org.ros.internal.message.Message;

public interface SumRequest extends Message {

    public abstract long getA();

    public abstract void setA(long val);

    public abstract long getB();

    public abstract void setB(long val);

    String _TYPE = "basic_ros_application/msgs/SumRequest";

    String _DEFINITION = "int64 a\nint64 b\n";
}
