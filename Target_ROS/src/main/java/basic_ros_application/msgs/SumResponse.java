package basic_ros_application.msgs;

import org.ros.internal.message.Message;

public interface SumResponse extends Message {

    public abstract long getSum();

    public abstract void setSum(long val);

    String _TYPE = "basic_ros_application/msgs/SumResponse";

    String _DEFINITION = "int64 sum\n";
}
