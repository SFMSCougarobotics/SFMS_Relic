package org.firstinspires.ftc.teamcode.Auto;

public class AutoCougarRobot {

    public void go(AbstractCougarRobot o) {
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
        o.pause(1);
        o.swipeCenter();
        o.pause(1);
        o.raiseJewelTool();
        o.pause(1);
        
        o.moveTheRightWay();

/*
        while (o.runtime.seconds() < 1.0) {
            //telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                //nothing
            }
        }
        //hub 1 port 0 moves the JT up and down
        //hub 1 port 1 flips it
        
        // Step 4:  Stop and close the claw.
        o.robot.motorFL.setPower(0);
        o.robot.motorFR.setPower(0);
*/
        //telemetry.addData("Path", "Complete");
        //telemetry.update();

    }
}
