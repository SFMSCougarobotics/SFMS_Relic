package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="AutoUsingIntake", group="Auto")

public class AutoUsingIntake extends AbstractCougarRobot {
    public AutoCougarRobot cougar = new AutoCougarRobot();
    
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.useIntakeDuringAutonomous = true;
        String team = "4947";

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
                    telemetry.addData("Position (y)", isFront ? "FRONT" : "BACK");
                    telemetry.update();
                }
            }
            if(gamepad1.x) {
                is4947 = !is4947;
                is5498 = !is5498;
                if(is4947) {
                    team = "4947";
                } else {
                    team = "5498";
                }
                while(gamepad1.x) {
                    //wait here for the button to be released
                    telemetry.addData("Team (x)", team);
                    telemetry.update();
                }
            }
            if(gamepad1.b) {
                //swipePosition -= 0.0001;
            }
            robot.swipe.setPosition(swipePosition);
            telemetry.addData("Alliance (a)", isRed ?   "RED"   : "BLUE");
            telemetry.addData("Position (y)", isFront ? "FRONT" : "BACK");
            telemetry.addData("Team (x)", team);
            //telemetry.addData("Time", getRuntime());
            telemetry.update();
        }

        waitForStart();
        cougar.go(this);
    }
}
