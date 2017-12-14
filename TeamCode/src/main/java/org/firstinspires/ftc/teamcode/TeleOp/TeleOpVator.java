
package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Hardware.HardwareVator;


@TeleOp(name="TeleOpVator", group="TeleOp")

public class TeleOpVator extends OpMode{

    /* Declare OpMode members. */
        


        public double m_1 = 0;
        double m_0 = 0;
        double m_2 = 0;
        double m_3 = 0;
        double m_4 = 0;
        double m_5 = 0;
        double ds_0 = 0;
        double ds_1 = 0.9;
        public float slowfactor;
        double rev = 1;

    
    HardwareVator robot       = new HardwareVator(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        
        
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        robot.sensorColor.enableLed(true);
    }
    
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        robot.motorFR.setPower(m_1);
        robot.motorRL.setPower(m_0);
        robot.motorRR.setPower(m_2);
        robot.motorFL.setPower(m_3);
        robot.motorA1.setPower(m_4);
        robot.motorA2.setPower(m_5);
        robot.jt.setPosition(ds_0);
        robot.swipe.setPosition(ds_1);
    }
    
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
     
    @Override
    public void loop() {

        if (gamepad2.x) {
            robot.swipe.setPosition(0.6);
        }
        //else if (gamepad2.a) {
          //  robot.swipe.setPosition(0);
        //}
        // Run intake pulley wheels on left controller 2 stick
        if (gamepad2.left_stick_y > 0.1f) {
           m_4 = -gamepad2.left_stick_y;
           m_5 = -gamepad2.left_stick_y;
        }
        else {
            m_4 = 0;
            m_5 = 0;
        }

        //left bumper power reduction
        //if (gamepad1.left_bumper) {
        //   slowfactor = 4;
        //} else if(!gamepad1.left_bumper){
        //    slowfactor = 1;
        //}
        slowfactor = (float) (1-(0.75*(gamepad1.left_trigger*gamepad1.left_trigger)));

        //right bumper reverse control
        if (gamepad1.right_bumper) {
            rev -= 2;
            if (rev == -1)
            telemetry.addLine()
                    .addData("Rev", "%.2f", rev)
                    .addData("Mode:","Reverse");
            else
                telemetry.addLine()
                        .addData("Rev", "%.2f", rev)
                        .addData("Mode:","Drive");
            if (rev < -2){
                rev = 1;
            }
        }
        if(gamepad2.y) {
            robot.flipUp();
        } else {
            robot.flipDown();
        }

        // dpad control for mecanum wheel drive
            if (gamepad1.dpad_right) {
                m_1 = -1;m_2 = -1;m_0 = 1;m_3 = 1;
            } else if (gamepad1.dpad_left) {
                m_1 = 1;m_2 = 1;m_0 = -1;m_3 = -1;
            } else if (gamepad1.dpad_up) {
                m_1 = 1;m_0 = 1;m_2 = 1;m_3 = 1;
            } else if (gamepad1.dpad_down) {
                m_1 = -1;m_0 = -1;m_2 = -1;m_3 = -1;
            } else if (gamepad1.right_stick_x < -0.1) {
                m_0 = -gamepad1.right_stick_x;m_2 = gamepad1.right_stick_x;
                m_1 = -gamepad1.right_stick_x;m_3 = gamepad1.right_stick_x;
            } else if (gamepad1.right_stick_x > 0.1) {
                m_0 = -gamepad1.right_stick_x;m_2 = gamepad1.right_stick_x;
                m_1 = -gamepad1.right_stick_x;m_3 = gamepad1.right_stick_x;
            } else {
                m_0 = m_1 = m_2 = m_3 = 0;
            }
        // write power to motors
        robot.motorFR.setPower(m_1*slowfactor*rev);
        robot.motorRL.setPower(m_0*slowfactor*rev);
        robot.motorRR.setPower(m_2*slowfactor*rev);
        robot.motorFL.setPower(m_3*slowfactor*rev);
        robot.motorA1.setPower(m_4);
        robot.motorA2.setPower(m_5);

//write to telemetry once at the end
        telemetry.addData("m_2", "%.2f", m_2);
        telemetry.addData("m_1", "%.2f", m_1);
        telemetry.addData("m_3", "%.2f", m_3);
        telemetry.addData("m_0", "%.2f", m_0);
        telemetry.addData("m_4", "%.2f", m_4);
        //telemetry.addData("m_5", "%.2f", m_5);
        }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
