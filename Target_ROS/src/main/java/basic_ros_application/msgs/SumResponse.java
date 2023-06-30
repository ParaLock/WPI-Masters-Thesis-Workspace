package basic_ros_application.msgs;

import org.ros.internal.message.Message;

public interface SumResponse extends Message {
    java.lang.String _TYPE = "basic_ros_application/msgs/SumResponse";
    java.lang.String _DEFINITION = "int64 sum";

    long getSum();

    void setSum(long var1);
}
