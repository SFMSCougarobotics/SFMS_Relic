
package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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
    public Servo    flipRight = null;
    public Servo    flipLeft = null;
    public ColorSensor sensorColor = null;
    public DistanceSensor sensorDistance = null;
    public DigitalChannel touch1 = null;
    public DigitalChannel touch2 = null;
    double flipRightDown = 0.0;
    double flipRightUp = 0.65;
    double flipLeftDown = 1.0;
    double flipLeftUp = 0.35;
    double flipRightPos = flipRightDown;
    double flipLeftPos = flipLeftDown;
    public ElapsedTime runtime = new ElapsedTime();
    public double FORWARD_SPEED = 0.4;
    public double TURN_SPEED    = 0.5;
    public double jtdown = 0.57;
    public double jtup = 0.0;
    public double swipefront = 0.7;
    public double swipeback = 1;
    public double swipecenter = 0.9;
    public boolean isRed = false;
    public boolean isFront = false;
    LinearOpMode op = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareVator(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        motorFR  = hwMap.get(DcMotor.class, "m_1");
        motorFL = hwMap.get(DcMotor.class, "m_3");
        motorRL = hwMap.get(DcMotor.class, "m_0");
        motorRR = hwMap.get(DcMotor.class, "m_2");
        motorA1 = hwMap.get(DcMotor.class, "m_4");
        motorA2 = hwMap.get(DcMotor.class, "m_5");
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorRL.setDirection(DcMotor.Direction.FORWARD);
        motorRR.setDirection(DcMotor.Direction.FORWARD);
        motorA2.setDirection(DcMotor.Direction.REVERSE);
        jt = hwMap.get(Servo.class,"servo_1_0");
        swipe = hwMap.get(Servo.class,"servo_1_1");
        flipRight = hwMap.get(Servo.class,"servo_1_2");
        flipLeft = hwMap.get(Servo.class,"servo_2_0");

        //servo2 = hwMap.get(Servo.class, "s11");
        //servo3 = hwMap.get(Servo.class, "s20");
        sensorColor = hwMap.get(ColorSensor.class, "color");
        sensorDistance = hwMap.get(DistanceSensor.class, "color");
        touch1 = hwMap.get(DigitalChannel.class, "t1");
        touch1.setMode(DigitalChannel.Mode.INPUT);
        touch2 = hwMap.get(DigitalChannel.class, "t2");
        touch2.setMode(DigitalChannel.Mode.INPUT);
        // Set all motors to zero power
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorRL.setPower(0);
        motorRR.setPower(0);
        motorA1.setPower(0);
        motorA2.setPower(0);
        jt.setPosition(0);
        swipe.setPosition(0.9);
        flipRight.setPosition(flipRightPos);
        flipLeft.setPosition(flipLeftPos);
    }


    public void flipUp() {
        //flipRightDown = 0.0, flipLeftDown = 1.0
        flipRightPos += .05;
        flipLeftPos -= .05;
        flipRightPos = Range.clip(flipRightPos, flipRightDown, flipRightUp);
        flipLeftPos = Range.clip(flipLeftPos, flipLeftUp, flipLeftDown);
        flipRight.setPosition(flipRightPos);
        flipLeft.setPosition(flipLeftPos);
    }

    public void flipDown() {
        flipRightPos -= .05;
        flipLeftPos += .05;
        flipRightPos = Range.clip(flipRightPos, flipRightDown, flipRightUp);
        flipLeftPos = Range.clip(flipLeftPos, flipLeftUp, flipLeftDown);
        flipRight.setPosition(flipRightPos);
        flipLeft.setPosition(flipLeftPos);
    }
}

