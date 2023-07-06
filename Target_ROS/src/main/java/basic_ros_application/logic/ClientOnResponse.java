package basic_ros_application.logic;

import basic_ros_application.msgs.SumResponse;
import org.apache.commons.logging.Log;

public class ClientOnResponse {

    public static void run(Log log, SumResponse response) {
        log.info("Client: Thanks, now I know the sum is:" + response.getSum() + "!");
    }
}
