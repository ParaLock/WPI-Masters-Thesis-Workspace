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

import basic_ros_application.logic.ServerOnRequest;
import basic_ros_application.msgs.Sum;
import basic_ros_application.msgs.SumRequest;
import basic_ros_application.msgs.SumResponse;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.service.ServiceServer;

public final class ROSJavaServerNodeMain extends AbstractNodeMain {
    private final String rosServiceName;
    private final String rosNodeName;

    private Log log;

    public ROSJavaServerNodeMain(final String rosServiceName, final String rosNodeName) {
        //Let's require that rosNodeName and rosService are not null to eagerly identify this error
        //These checks are completely optional
        Preconditions.checkArgument(StringUtils.isNotBlank(rosNodeName));
        Preconditions.checkArgument(StringUtils.isNotBlank(rosServiceName));

        this.rosServiceName = rosServiceName;
        this.rosNodeName = rosNodeName;
    }

    public GraphName getDefaultNodeName() {
        return GraphName.of(this.rosNodeName);
    }

    private void onRequest(SumRequest request, SumResponse response) {
        ServerOnRequest.run(log, request, response);
    }

    public void onStart(ConnectedNode connectedNode) {

        this.log = connectedNode.getLog();

        ServiceServer<SumRequest, SumResponse> serviceServer = connectedNode.newServiceServer(
                this.rosServiceName,
                Sum._TYPE,
                this::onRequest
        );

        this.log.info(("Created ROS Service Server[" + serviceServer.getName()) + "] in URI:" + serviceServer.getUri());
    }
}
