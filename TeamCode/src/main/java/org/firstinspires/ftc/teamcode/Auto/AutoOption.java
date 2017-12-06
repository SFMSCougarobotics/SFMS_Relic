package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="AutoOption", group="Auto")

public class AutoOption extends AbstractCougarRobot {
    public AutoCougarRobot cougar = new AutoCougarRobot();
    
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        while (! isStarted()) {

            if (gamepad1.a) isRed = ! isRed;
            if (gamepad1.y) isFront = ! isFront;
            telemetry.addData("Alliance (a)", isRed ? "BLUE" : "RED");
            telemetry.addData("Position (y)", isFront ? "BACK" : "FRONT");
            telemetry.addData("Time", getRuntime());
            telemetry.update();
        }

        waitForStart();
        cougar.go(this);
    }
}
