
package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.HardwareVator;


@TeleOp(name="TeleOpSingle", group="TeleOp")

public class TeleOpSingle extends OpMode{

    /* Declare OpMode members. */
        


        double m_1 = 0;
        double m_0 = 0;
        double m_2 = 0;
        double m_3 = 0;
        double m_4 = 0;
        double m_5 = 0;
        double ds_0 = 0;
        double ds_1 = 0.9;
        public float slowfactor = 1.0f;
        double rev = 1;
        double intake = 0;

    
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
        robot.jt.setPosition(robot.jtup);
        robot.swipe.setPosition(robot.swipecenter);
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

        //GAMEPAD 2 CONTROLS

        if (gamepad1.x) {
            robot.swipe.setPosition(robot.swipecenter);
            robot.jt.setPosition(robot.jtup);
        }

        // Run intake pulley wheels on left controller 2 stick
        if (gamepad1.a) {
            intake++;
            if (intake > 2) {
                intake = 0;
            }
            while(gamepad1.a) {
                telemetry.addData("Intake", "%.2f", intake);
                telemetry.update();
            }
        }

        if (intake == 1){
            m_4 = m_5 = 0.8;
        }
        else if (intake == 2){
            m_4 = m_5 = -0.5;
        }
        else{
            m_4 = m_5 = 0;
        }
        if (gamepad1.b){
            m_4 = 0.6;
            m_5 = -0.6;
        }
        //GAMEPAD 1 CONTROLS

        if(gamepad1.left_bumper) {
            slowfactor += 0.25;
            if(slowfactor > 1.0) {
                slowfactor = 0.50f;
            }
            while(gamepad1.left_bumper) {
                telemetry.addData("Speed", "%.2f", slowfactor);
                telemetry.update();
            }
        }

        //right bumper reverse control
        if (gamepad1.right_bumper) {
            rev *= -1; //toggle between -1 and 1
            while(gamepad1.right_bumper) {
                if (rev == -1) {
                    telemetry.addLine()
                            .addData("Rev", "%.2f", rev)
                            .addData("Mode:", "Reverse");
                } else {
                    telemetry.addLine()
                            .addData("Rev", "%.2f", rev)
                            .addData("Mode:", "Drive");
                }
                telemetry.update();
            }
        }
        if(gamepad1.y) {
            robot.flipUp();
        } else {
            robot.flipDown();
        }

        // dpad control for mecanum wheel drive
            if (gamepad1.dpad_right) {
                m_3 = 1;    m_1 = -1;
                m_2 = -1;   m_0 = 1;
            } else if (gamepad1.dpad_left) {
                m_3 = -1;    m_1 = 1;
                m_2 = 1;     m_0 = -1;
            } else if (gamepad1.dpad_up) {
                m_3 = 1;    m_1 = 1;
                m_2 = 1;    m_0 = 1;
            } else if (gamepad1.dpad_down) {
                m_3 = -1;    m_1 = -1;
                m_2 = -1;    m_0 = -1;
            } else if (gamepad1.right_stick_x < -0.1) {
                m_0 = -gamepad1.right_stick_x;
                m_2 = gamepad1.right_stick_x;
                m_1 = -gamepad1.right_stick_x;
                m_3 = gamepad1.right_stick_x;
            } else if (gamepad1.right_stick_x > 0.1) {
                m_0 = -gamepad1.right_stick_x;
                m_2 = gamepad1.right_stick_x;
                m_1 = -gamepad1.right_stick_x;
                m_3 = gamepad1.right_stick_x;
            } else {
                m_0 = m_1 = m_2 = m_3 = 0;
            }
            // write power to motors
            robot.motorFR.setPower(m_1*slowfactor*rev);
            robot.motorRL.setPower(m_2*slowfactor*rev);
            robot.motorRR.setPower(m_0*slowfactor*rev);
            robot.motorFL.setPower(m_3*slowfactor*rev);
            robot.motorA1.setPower(m_4);
            robot.motorA2.setPower(m_5);

            //write to telemetry once at the end
            if(rev==1) {
                telemetry.addLine().addData("Rev", "%.2f", rev).addData("Mode:", "Drive");
            }else {
                telemetry.addLine().addData("Rev", "%.2f", rev).addData("Mode:", "Reverse");
            }
            telemetry.addData("Speed", "%.2f", slowfactor);
            telemetry.addData("FrontRight:m_1", "%.2f", m_1*slowfactor*rev);
            telemetry.addData("FrontLeft-m_3", "%.2f", m_3*slowfactor*rev);
            telemetry.addData("BackLeft-m_2", "%.2f", m_2*slowfactor*rev);
            telemetry.addData("BackRight-m_0", "%.2f", m_0*slowfactor*rev);
            telemetry.addData("m_4", "%.2f", m_4);
            //telemetry.addData("m_5", "%.2f", m_5);
            telemetry.update();
        }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
