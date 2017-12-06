
package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    public Servo flip  = null;
    //public Servo    servo2 = null;
    //public Servo    servo3 = null;
    public ColorSensor sensorColor = null;
    public DistanceSensor sensorDistance = null;
    public DigitalChannel touch1 = null;
    public DigitalChannel touch2 = null;

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
        jt = hwMap.get(Servo.class, "ds_0");
        flip = hwMap.get(Servo.class, "ds_1");
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
        flip.setPosition(0.9);
    }
 }

