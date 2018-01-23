package org.firstinspires.ftc.teamcode.Auto;

public class AutoCougarRobot {

    public void go(AbstractEncoders o) {
        // Send telemetry message to signify robot waiting;
        o.telemetry.addData("Status", "Ready to run ");
        o.telemetry.update();

        //o.turnLeft(10);
        //o.turnRight(10);

        o.raiseJewelTool();
        o.swipeCenter();
        o.pause(1);


        o.lowerJewelTool();
        o.pause(3);
        o.swipeOpponentColor();
        o.raiseJewelTool();
        o.swipeCenter();
        o.pause(1);
        o.moveTheRightWayUsingIntake();

    }
}
