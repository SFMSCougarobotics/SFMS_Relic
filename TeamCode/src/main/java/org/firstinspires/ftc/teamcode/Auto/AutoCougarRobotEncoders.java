package org.firstinspires.ftc.teamcode.Auto;

public class AutoCougarRobotEncoders {

    public void go(AbstractEncodersHWAuto o) {
        // Send telemetry message to signify robot waiting;
        //o.telemetry.addData("Status", "Ready to run ");
        //o.telemetry.update();

        //FL, FR, RL , RR
        //String target = o.identifyTarget(2000);
        //o.telemetry.addData("Target", target);
        //o.telemetry.update();
        //o.pause(5);

        o.debugEncoder(5,0.3,4,"FL");
        o.pause(2);
        o.debugEncoder(5,0.3,4,"FR");
        o.pause(2);
        o.debugEncoder(5,0.3,4,"RL");
        o.pause(2);
        o.debugEncoder(5,0.3,4,"RR");
        o.pause(2);


        o.goForwardSp(2,0.3);
        o.pause(2);
        o.goE(1.5,0.3,3);
        o.pause(2);
        o.turnE(1,0.3,3);
        o.pause(30);
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
