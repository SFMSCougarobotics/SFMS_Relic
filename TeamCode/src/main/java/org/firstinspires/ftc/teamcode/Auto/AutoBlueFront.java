package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoBlueFront", group="Auto")

public class AutoBlueFront extends AbstractCougarRobot {
    public AutoCougarRobot cougar = new AutoCougarRobot();
    
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        isRed = false;
        isFront = true;
        waitForStart();
        cougar.go(this);
    }
}
