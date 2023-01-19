# PROJECT: ROSBOT2
# FILE: rosbot_ekf/src/msgs_conversion.cpp
# FRAGEMENT:
```
int main(int argc, char **argv)
{
  
  ros::init(argc, argv, "msgs_conversion");


  ros::NodeHandle n;

  ros::Publisher imu_pub = n.advertise<sensor_msgs::Imu>("/imu", 1);
  imu_pub_ptr = &imu_pub;
  ros::Publisher odom_pub = n.advertise<nav_msgs::Odometry>("/odom/wheel", 1);
  odom_pub_ptr = &odom_pub;

  ros::Subscriber mpu_sub = n.subscribe("/mpu9250", 1000, mpuCallback);
  ros::Subscriber pose_sub = n.subscribe("/pose", 1000, poseCallback);

  ros::Rate loop_rate(20);

  while (ros::ok())
  {
    ros::spinOnce();
    loop_rate.sleep();
   
  }


  return 0;
}
```
# PROJECT: BOBBLE BOT
## FILE: src/nodes/JoystickControl 
```
class JoystickControl(object):

    def __init__(self):
        rospy.Subscriber('joy', Joy, self.joystickCallback)
        self.mode_cmd_pub = rospy.Publisher('/bobble/bobble_balance_controller/bb_cmd', ControlCommands, queue_size = 1)
        self.cmd_vel_pub = rospy.Publisher('/bobble/bobble_balance_controller/cmd_vel', Twist, queue_size = 1)
        self.mode_cmd = ControlCommands()
        self.cmd_vel = Twist()
        rospy.spin()
```
## FILE: bobble_controllers/msg/BobbleBotStatus.msg
```
int32 ControlMode
float32 MeasuredTiltDot
float32 MeasuredTurnRate
float32 FilteredTiltDot
float32 FilteredTurnRate
float32 Tilt
float32 TiltRate
float32 Heading
float32 TurnRate
float32 ForwardVelocity
float32 DesiredVelocity
float32 DesiredTilt
float32 DesiredTurnRate
float32 LeftMotorPosition
float32 LeftMotorVelocity
float32 RightMotorPosition
float32 RightMotorVelocity
float32 TiltEffort
float32 HeadingEffort
float32 LeftMotorEffortCmd
float32 RightMotorEffortCmd
```
## FILE: bobble_controllers/launch/run_sim.launch
```
<launch>
    <arg name="paused" default="false"/>
    <arg name="gui" default="false"/>
    <include file="$(find bobble_controllers)/launch/bobble_balance_state_hold.launch">
        <arg name="paused" value="$(arg paused)"/>
        <arg name="gui" value="$(arg gui)"/>
    </include>
</launch>
```
------------------------------------------------------------------------------------------------------------
# PROJECT: AWS-deepracer
# FILE: deepracer_nodes/cmdvel_to_servo_pkg/cmdvel_to_servo_pkg/cmdvel_to_servo_node.py
```
    def __init__(self, qos_profile):
        """Create a CmdvelToServoNode.
        """
        super().__init__('cmdvel_to_servo_node')
        self.get_logger().info("cmdvel_to_servo_node started.")

        # Create subscription to cmd_vel.
        self.cmdvel_subscriber = \
            self.create_subscription(geometry_msgs.msg.Twist,
                                     constants.CMDVEL_TOPIC,
                                     self.on_cmd_vel,
                                     qos_profile)

        # Creating publisher to publish action (angle and throttle).
        self.action_publisher = self.create_publisher(ServoCtrlMsg,
                                                      constants.ACTION_PUBLISH_TOPIC,
                                                      qos_profile)

        # Service to dynamically set MAX_SPEED_PCT.
        self.set_max_speed_service = self.create_service(SetMaxSpeedSrv,
                                                         constants.SET_MAX_SPEED_SERVICE_NAME,
                                                         self.set_max_speed_cb)
```
------------------------------------------------------------------------------------------------------------
# PROJECT: NVIDIA ISSAC
# FILE: isaac_ros_nvblox/nvblox_ros/src/lib/nvblox_node.cpp
```
  mesh_publisher_ = create_publisher<nvblox_msgs::msg::Mesh>("~/mesh", 1);
  pointcloud_publisher_ =
    create_publisher<sensor_msgs::msg::PointCloud2>("~/pointcloud", 1);
  map_slice_publisher_ =
    create_publisher<nvblox_msgs::msg::DistanceMapSlice>("~/map_slice", 1);
  mesh_marker_publisher_ =
    create_publisher<visualization_msgs::msg::MarkerArray>(
    "~/mesh_marker",
    1);
```