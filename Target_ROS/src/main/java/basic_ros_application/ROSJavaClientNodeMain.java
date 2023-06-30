/**
 * Copyright 2020 Spyros Koukas
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package basic_ros_application;

import basic_ros_application.msgs.Sum;
import basic_ros_application.msgs.SumRequest;
import basic_ros_application.msgs.SumResponse;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.ros.exception.RosRuntimeException;
import org.ros.exception.ServiceNotFoundException;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.service.ServiceClient;


/**
 * An example client
 * An object of this class is a ROS Node with a specific,  given rosNodeName that targets a ros service server published at the given serviceName and  of type {@link rosjava_test_msgs.AddTwoInts}
 * This object makes service calls to a ros service of type {@link rosjava_test_msgs.AddTwoInts} published at the specified serviceName . After each successful service call it prints and logs the messages at info level.
 * Created at 2022-06-21
 *
 * @author Spyros Koukas
 */
public class ROSJavaClientNodeMain extends AbstractNodeMain {

    private final java.lang.String rosServiceName;
    private final java.lang.String rosNodeName;

    /**
     * @param rosServiceName the name of the service to call
     * @param rosNodeName    the name of the ROS node
     */
    public ROSJavaClientNodeMain(final java.lang.String rosServiceName, final java.lang.String rosNodeName) {
        //Let's require that rosNodeName and rosServiceName are not null to eagerly identify this error
        //These checks are completely optional
        Preconditions.checkArgument(StringUtils.isNotBlank(rosNodeName));
        Preconditions.checkArgument(StringUtils.isNotBlank(rosServiceName));

        this.rosServiceName = rosServiceName;
        this.rosNodeName = rosNodeName;
    }

    @Override
    public final GraphName getDefaultNodeName() {
        return GraphName.of(this.rosNodeName);
    }

    @Override
    public final void onStart(ConnectedNode connectedNode) {

        final Log log = connectedNode.getLog();

        try {
            ServiceClient<SumRequest, SumResponse> serviceClient = connectedNode.newServiceClient(
                    this.rosServiceName,
                    Sum._TYPE
            );
            serviceClient.isConnected();

            //Create a response listener for consuming the service server response
            ROSJavaClientNodeListener responseListener = new ROSJavaClientNodeListener(
                    connectedNode
            );

            //The CancellableLoop will run again and again until the node is stopped.
            connectedNode.executeCancellableLoop(
                    new ROSJavaClientNodeLoop(serviceClient, log, responseListener)
            );

        } catch (ServiceNotFoundException serviceNotFoundException) {
            log.error(ExceptionUtils.getStackTrace(serviceNotFoundException));
            throw new RosRuntimeException(serviceNotFoundException);
        }


    }
}