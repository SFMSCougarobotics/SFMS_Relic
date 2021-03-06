package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="AutoUsingIntake5498", group="Auto")

public class AutoUsingIntake5498 extends AbstractCougarRobot5498 {
    public AutoCougarRobot cougar = new AutoCougarRobot();
    
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.useIntakeDuringAutonomous = true;

        double swipePosition = robot.swipecenter;
        while (! isStarted()) {

            if (gamepad1.a) {
                isRed = ! isRed;
                while(gamepad1.a) {
                    //wait here for the button to be released
                    telemetry.addData("Alliance (a)", isRed ? "RED" : "BLUE");
                    telemetry.update();
                }
            }

            if (gamepad1.y) {
                isFront = ! isFront;
                while(gamepad1.y) {
                    //wait here for the button to be released
                    telemetry.addData("Alliance (a)", isFront ? "FRONT" : "BACK");
                    telemetry.update();
                }
            }
            if(gamepad1.x) {
                swipePosition += 0.0001;
            }
            if(gamepad1.b) {
                swipePosition -= 0.0001;
            }
            robot.swipe.setPosition(swipePosition);
            telemetry.addData("swipePos",swipePosition);
            telemetry.addData("Alliance (a)", isRed ?   "RED"   : "BLUE");
            telemetry.addData("Position (y)", isFront ? "FRONT" : "BACK");
            //telemetry.addData("Time", getRuntime());
            telemetry.update();
        }

        waitForStart();
        cougar.go(this);
    }
}
