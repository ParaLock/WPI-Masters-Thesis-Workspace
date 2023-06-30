package basic_ros_application;

import basic_ros_application.logic.ClientOnLoop;
import basic_ros_application.msgs.SumRequest;
import basic_ros_application.msgs.SumResponse;
import org.apache.commons.logging.Log;
import org.ros.concurrent.CancellableLoop;
import org.ros.node.service.ServiceClient;
import org.ros.node.service.ServiceResponseListener;

public class ROSJavaClientNodeLoop extends CancellableLoop {
    private ServiceClient<SumRequest, SumResponse> serviceClient;
    private Log log;
    ServiceResponseListener<SumResponse> responseListener;

    public ROSJavaClientNodeLoop(
            ServiceClient<SumRequest, SumResponse> _serviceClient,
            Log _log,
            ServiceResponseListener<SumResponse> _responseListener
    ) {

        serviceClient = _serviceClient;
        log = _log;
        responseListener = _responseListener;
    }

    protected void loop() throws InterruptedException {
        //Create a new service request message
        SumRequest request = serviceClient.newMessage();

        ClientOnLoop.run(request, log);

        serviceClient.call(request,responseListener);

    }
}
