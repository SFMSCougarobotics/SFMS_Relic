package org.firstinspires.ftc.teamcode.Auto;

public class AutoCougarRobot {

    public void go(AbstractCougarRobot o) {
        // Send telemetry message to signify robot waiting;
        //o.telemetry.addData("Status", "Ready to run ");
        //o.telemetry.update();

        //o.turnLeft(10);
        //o.turnRight(10);

        //String target = o.identifyTarget(2000);

        //o.telemetry.addData("Target", target);
        //o.telemetry.update();
        //o.pause(5);

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
