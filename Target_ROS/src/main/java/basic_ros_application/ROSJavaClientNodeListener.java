package basic_ros_application;

import basic_ros_application.logic.ClientOnResponse;
import basic_ros_application.msgs.SumResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.ros.exception.RemoteException;
import org.ros.node.ConnectedNode;
import org.ros.node.service.ServiceResponseListener;
public class ROSJavaClientNodeListener implements ServiceResponseListener<SumResponse> {

    ConnectedNode connectedNode;

    public ROSJavaClientNodeListener(ConnectedNode node) {
        connectedNode = node;
    }

    public void onSuccess(SumResponse response) {
        //log the service response
        ClientOnResponse.run(connectedNode.getLog(), response);
    }
    public void onFailure(RemoteException remoteException) {
        //example logging error
        connectedNode.getLog().error(ExceptionUtils.getStackTrace(remoteException));
    }
}
