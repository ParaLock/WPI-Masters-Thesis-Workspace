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

import basic_ros_application.msgs.StringMessage;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;


/**
 * An example subscriber
 * An object of this class is a ROS Node with a specific,  given rosNodeName.
 * This object subscribes to the specified rosTopicName . For each message received it prints and logs the messages at info level.
 * Created at 2020-05-16
 *
 * @author Spyros Koukas
 */
public final class ROSJavaSubscriberNodeMain extends AbstractNodeMain {
    private final String rosTopicName;
    private final String rosNodeName;

    public ROSJavaSubscriberNodeMain(String rosTopicName, String rosNodeName) {

        Preconditions.checkArgument(StringUtils.isNotBlank(rosNodeName));
        Preconditions.checkArgument(StringUtils.isNotBlank(rosTopicName));

        this.rosTopicName = rosTopicName;
        this.rosNodeName = rosNodeName;
    }

    public GraphName getDefaultNodeName() {
        return GraphName.of(this.rosNodeName);
    }

    public void onStart(ConnectedNode connectedNode) {

        Log log = connectedNode.getLog();

        Subscriber<StringMessage> subscriber = connectedNode.newSubscriber(
                this.rosTopicName,
                StringMessage._TYPE
        );

        subscriber.addMessageListener(new ROSJavaSubscriberNodeListener(log));
    }
}