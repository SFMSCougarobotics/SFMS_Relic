
package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class HardwareVator
{
    /* Public OpMode members. */
    public DcMotor  motorFL   = null;
    public DcMotor  motorFR  = null;
    public DcMotor  motorRL     = null;
    public DcMotor  motorRR     = null;
    public DcMotor  motorA1 = null;
    public DcMotor  motorA2 = null;
    public Servo jt  = null;
    public Servo swipe  = null;
    public Servo    flipLeft = null;
    public ColorSensor sensorColor = null;
    public DistanceSensor sensorDistance = null;
    public BNO055IMU IMU = null;
    double flipLeftDown = 1.0;
    double flipLeftUp = 0.3;
    double flipLeftPos = flipLeftDown;
    public ElapsedTime runtime = new ElapsedTime();
    public double jtdown = 0.30; //now 0.25 with combined bot
    public double jtup = 0.9; //now 0.9
    public double swipefront = 0.0; //now 0
    public double swipeback = 0.3; //now 0.3
    public double swipecenter = 0.13; //now 0.13
    public boolean useIntakeDuringAutonomous = false;
    public boolean targetLeft = false;
    public boolean targetRight = false;
    public boolean targetCenter = false;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;

    /* Constructor */
    public HardwareVator(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        motorRR = hwMap.get(DcMotor.class, "m_0");
        motorFR = hwMap.get(DcMotor.class, "m_1");
        motorRL = hwMap.get(DcMotor.class, "m_2");
        motorFL = hwMap.get(DcMotor.class, "m_3");
        motorA1 = hwMap.get(DcMotor.class, "m_4");
        motorA2 = hwMap.get(DcMotor.class, "m_5");
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorRL.setDirection(DcMotor.Direction.REVERSE);
        motorRR.setDirection(DcMotor.Direction.FORWARD);//reversed on 1-13-18
        motorFR.setDirection(DcMotor.Direction.FORWARD);
        motorA1.setDirection(DcMotor.Direction.FORWARD);
        motorA2.setDirection(DcMotor.Direction.REVERSE);
        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        jt = hwMap.get(Servo.class,"servo_1_0");
        swipe = hwMap.get(Servo.class,"servo_1_1");
        //flipRight = hwMap.get(Servo.class,"servo_1_2");
        flipLeft = hwMap.get(Servo.class,"servo_1_3");

        //servo2 = hwMap.get(Servo.class, "s11");
        //servo3 = hwMap.get(Servo.class, "s20");
        sensorColor = hwMap.get(ColorSensor.class, "color");
        sensorDistance = hwMap.get(DistanceSensor.class, "color");
        //touch1 = hwMap.get(DigitalChannel.class, "t1");
        //touch1.setMode(DigitalChannel.Mode.INPUT);
        //touch2 = hwMap.get(DigitalChannel.class, "t2");
        //touch2.setMode(DigitalChannel.Mode.INPUT);
        // Set all motors to zero power
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorRL.setPower(0);
        motorRR.setPower(0);
        motorA1.setPower(0);
        motorA2.setPower(0);
        jt.setPosition(jtup);
        swipe.setPosition(swipecenter);
        //flipRight.setPosition(flipRightPos);
        flipLeft.setPosition(flipLeftPos);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        //imu = hardwareMap.get(BNO055IMU.class, "imu");
        //imu.initialize(parameters);

        // Set up our telemetry dashboard
        //composeTelemetry();
    }

    public String identifyTarget(double tickms) {
        VuforiaLocalizer vuforia;
        String target = "";
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId",
                "id",
                hwMap.appContext.getPackageName()
        );
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "ASw67R//////AAAAGZDZg8GQ5k2jg4Vw1AbxsJctfNc5A4c3MM4rZ7131aV7XLzN6u81EZxsKoqJkgIYSgYot/73fnKdar+08jR7U89z3zClP1haL9O+pW996ZQyz4FtDNCLgoPH+AwdMbE+lQcQzrzJEWTxqyR5pKTSTnLT2cNLdxaE99AbBZOlQlhKWq4Y45xQjTTEfiGqqUBXlSoIbD0ipX8rolD8GoEinCZ8Ld1NbEwAwe3ub1dT3sbU19JGO1VRRF6uO9araTPBnF96LBXvpmPnqCIAX4TC8nxqnRqVZyKfmTP+h8Xq7uxr9PxNJCMV7GD3WTqI2QEk/uFD8ywD3u07rdbSRd35qDk4toZRY8udynZoAxKfk/Vq";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        relicTrackables.activate();
        runtime.reset();
        while (runtime.seconds() < tickms) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                target = vuMark.toString();
                if(target.equals("LEFT")) {
                    targetLeft = true;
                } else if(target.equals("RIGHT")) {
                    targetRight = true;
                } else if(target.equals("CENTER")) {
                    targetCenter = true;
                } else {
                    //Yikes - the target doesn't match any of these???
                }
                return target;
            }
        }
        return "UNKNOWN";
    }

    public void flipUp() {
        //flipRightDown = 0.0, flipLeftDown = 1.0
        //flipRightPos += .05;
        flipLeftPos -= .05;
        //flipRightPos = Range.clip(flipRightPos, flipRightDown, flipRightUp);
        flipLeftPos = Range.clip(flipLeftPos, flipLeftUp, flipLeftDown);
        //flipRight.setPosition(flipRightPos);
        flipLeft.setPosition(flipLeftPos);
    }

    public void flipDown() {
        //flipRightPos -= .05;
        flipLeftPos += .05;
        //flipRightPos = Range.clip(flipRightPos, flipRightDown, flipRightUp);
        flipLeftPos = Range.clip(flipLeftPos, flipLeftUp, flipLeftDown);
        //flipRight.setPosition(flipRightPos);
        flipLeft.setPosition(flipLeftPos);
    }

    public void flipSlowly(int direction) {
        //direction 0 for down, 1 for up
        //the interval is how many units we increase or decrease
        //tickms is how many milliseconds to wait between changes
        //assume we are going down, and we will reverse if we are going up
        double interval = 0.1;
        double tickms = 0.1;

//        double rightInterval = 0.0;
//        //assume the servo is up
//
//        if(flipRightDown < flipRightUp) {
//            //we want the right servo to decrease to go down
//            rightInterval = -1 * interval;
//        } else {
//            rightInterval = interval;
//        }

        double leftInterval = 0.0;
        if(flipLeftDown < flipLeftUp) {
            //we want the left servo to decrease to go down
            leftInterval = -1 * interval;
        } else {
            leftInterval = interval;
        }

        //if we are going up, then reverse the intervals
        if(direction == 1) {
            leftInterval *= -1;
            //rightInterval *= -1;
        }

        if(direction == 1) {
            //we want to increase position
            while(flipLeftPos > flipLeftUp) {
                //flipRightPos += rightInterval;
                flipLeftPos += leftInterval;
                //flipRight.setPosition(flipRightPos);
                flipLeft.setPosition(flipLeftPos);
                runtime.reset();
                while (runtime.seconds() < tickms) {
                    //do nothing while the arm moves a bit
                }
            }
        } else {
            //we want to decrease position
            while(flipLeftPos < flipLeftUp) {
                //flipRightPos += rightInterval;
                flipLeftPos += leftInterval;
                //flipRight.setPosition(flipRightPos);
                flipLeft.setPosition(flipLeftPos);
                runtime.reset();
                while (runtime.seconds() < tickms) {
                    //do nothing while the arm moves a bit
                }
            }
        }
    }
}

