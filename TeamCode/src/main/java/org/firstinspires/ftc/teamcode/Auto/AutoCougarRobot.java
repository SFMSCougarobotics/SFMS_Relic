package org.firstinspires.ftc.teamcode.Auto;

public class AutoCougarRobot {

    public void go(AbstractCougarRobot o) {
        // Send telemetry message to signify robot waiting;
        o.telemetry.addData("Status", "Ready to run ");
        o.telemetry.update();
        
        o.strafeLeft(30);
        o.pause(5);
        o.flipCenter();
        o.pause(3);
        o.lowerJewelTool();
        o.pause(3);
        o.flipOpponentColor();
        //o.flipFront();
        //o.pause(3);
        //o.flipBack();
        //o.pause(3);
        //o.goForward(2.5);
        //o.pause(5);
        o.raiseJewelTool();
        o.pause(5);
        
        o.moveTheRightWay();
        //o.goForward(1);
        //o.goForward(2.5);

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
