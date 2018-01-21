package org.firstinspires.ftc.teamcode.Auto;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.Hardware.HardwareVator;
//codingame


public abstract class AbstractCougarRobot extends LinearOpMode {
    public HardwareVator robot   = new HardwareVator();
    public ElapsedTime runtime = new ElapsedTime();
    public double FORWARD_SPEED = 0.4;
    public double TURN_SPEED    = 0.5;
    //public double jtdown = 0.57;
    //public double jtup = 0.0;
    //public double swipefront = 0.7;
    //public double swipeback = 1;
    //public double swipecenter = 0.9;
    public boolean isRed = false;
    public boolean isFront = false;
    //public double flipRightDown = 0.0;
    // public double flipRightUp = 0.65;
    public double flipLeftDown = 1.0;
    public double flipLeftUp = 0.35;

    public void lowerJewelTool() {
        robot.jt.setPosition(robot.jtdown);
    }
    
    public void raiseJewelTool() {
        robot.jt.setPosition(robot.jtup);
    }
    
    public void swipeCenter() {
        robot.swipe.setPosition(robot.swipecenter);
    }
    
    public boolean seeBlue() {
        if(robot.sensorColor.red() < robot.sensorColor.blue()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean seeRed() {
        if(robot.sensorColor.red() < robot.sensorColor.blue()) {
            return false;
        } else {
            return true;
        }
    }
    /*
    public boolean getTouch1() {
        if(robot.touch1.getState() == true) {
            return false;
        } else {
            return true;
        }
    }
    */
    public void swipeOpponentColor() {
        if(isRed) {
            if(seeBlue()) {
                swipeFront();
            } else {
                swipeBack();
            }
        } else {
            if(seeBlue()) {
                swipeBack();
            } else {
                swipeFront();
            }
        }
    }
    
    public void swipeBack() {
        if(opModeIsActive()) {
            robot.swipe.setPosition(robot.swipeback);
            pause(1);
            robot.swipe.setPosition(robot.swipecenter);
        }
    }
    
    public void swipeFront() {
        if(opModeIsActive()) {
            robot.swipe.setPosition(robot.swipefront);
            pause(1);
            robot.swipe.setPosition(robot.swipecenter);
        }
    }

    public void moveTheRightWayUsingIntake() {
        if(isRed) {
            //we are red
            if(isFront) {
                //red front
                goBackward(1.1);
                turnLeft(1.1);
                pause(1);
                goForwardSp(0.35,0.3); //need to move towards the box a bit
                pause(0.5);
                outtakeON();
                pause(0.5);
                goForwardSp(0.9,0.2);
                goBackwardSp(0.6,0.2);
                goForwardSp(0.9, 0.3);
                goBackwardSp(0.5,0.3);
                outtakeOFF();
            } else {
                //red back
                goBackwardSp(1.3, 0.3);
                raiseJewelTool();
                strafeRight(1.6);
                raiseJewelTool();
                turnRight(2.6);
                pause(1);
                goForwardSp(0.2, 0.2);
                raiseJewelTool();
                outtakeON();
                raiseJewelTool();
                goForwardSp(0.9,0.3);
                goBackwardSp(0.6,0.3);
                goForwardSp(0.9,0.3);
                goBackwardSp(0.5,0.3);
                outtakeOFF();
            }
        } else {
            //we are blue
            if(isFront) {
                //we are blue front
                goForward(1.1);
                turnLeft(1.35);
                pause(0.5);
                goBackwardSp(0.35, 0.3);
                outtakeON();
                goForwardSp(0.8,0.2);
                goBackwardSp(0.4,0.2);
                goForwardSp(0.8,0.2);
                goBackwardSp(0.4,0.2);
                outtakeOFF();
            } else {
                //we are blue back
                goForwardSp(1.3, 0.3);
                strafeRight(1.6);
                pause(0.5);
                goForwardSp(0.4,0.2);
                outtakeON();
                goForwardSp(0.8,0.3);
                goBackwardSp(0.3,0.3);;
                goForwardSp(0.8,0.3);
                goBackwardSp(0.4,0.3);;
                outtakeOFF();
            }
        }
    }
    
    public void moveTheRightWay() {
        if(robot.useIntakeDuringAutonomous) {
            moveTheRightWayUsingIntake();
            return;
        }
        if(isRed) {
            //we are red
            if(isFront) {
                //red front
                goBackward(1.1);
                turnRight(1.35);
                goBackwardSp(0.45,0.3); //need to move towards the box a bit
                intake(1);
                flipglyph();
                goForwardSp(0.7, 0.2);
                goBackwardSp(1.0,0.3);
                goForwardSp(0.7,0.2);
            } else {
                //red back
                goBackwardSp(1.55, 0.3);
                raiseJewelTool();
                strafeRight(1.6);
                raiseJewelTool();
                goForwardSp(0.2, 0.2);
                raiseJewelTool();
                intake(1);
                flipglyph();
                raiseJewelTool();
                goBackwardSp(1.0,0.3);
                goForwardSp(0.4,0.2);
            }
        } else {
            //we are blue
            if(isFront) {
                //we are blue front
                goForward(1.1);
                turnRight(1.35);
                goBackwardSp(0.2, 0.3);
                intake(1);
                flipglyph();
                goForwardSp(0.9, 0.3);
                goBackwardSp(0.3,0.3);
            } else {
                //we are blue back
                goForwardSp(1.60, 0.3);
                strafeRight(1.6);
                turnRight(2.7);
                goBackwardSp(0.2,0.2);
                intake(1);
                flipglyph();
                goForwardSp(0.7, 0.3);
                goBackwardSp(0.2,0.3);

            }
        }
    }

    public void strafeLeft(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(-FORWARD_SPEED);robot.motorFR.setPower(FORWARD_SPEED);
        robot.motorRL.setPower(FORWARD_SPEED);robot.motorRR.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }
    
    public void strafeRight(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(FORWARD_SPEED);robot.motorFR.setPower(-FORWARD_SPEED);
        robot.motorRL.setPower(-FORWARD_SPEED);robot.motorRR.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }
    
    public void goForward(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(FORWARD_SPEED);robot.motorFR.setPower(FORWARD_SPEED);
        robot.motorRL.setPower(FORWARD_SPEED);robot.motorRR.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }

    public void goForwardSp(double t,double speed) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(speed);robot.motorFR.setPower(speed);
        robot.motorRL.setPower(speed);robot.motorRR.setPower(speed);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }

    public void goBackward(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(-FORWARD_SPEED);robot.motorFR.setPower(-FORWARD_SPEED);
        robot.motorRL.setPower(-FORWARD_SPEED);robot.motorRR.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }
    public void goBackwardSp(double t,double speed) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(-speed);robot.motorFR.setPower(-speed);
        robot.motorRL.setPower(-speed);robot.motorRR.setPower(-speed);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);
        robot.motorRR.setPower(0);
    }
    public void turnLeft(double t) {
        // Step 1:  Drive forward for t seconds

        robot.motorFL.setPower(-FORWARD_SPEED);robot.motorFR.setPower(FORWARD_SPEED);
        robot.motorRL.setPower(-FORWARD_SPEED);robot.motorRR.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }

    public void turnRight(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(FORWARD_SPEED);robot.motorFR.setPower(-FORWARD_SPEED);
        robot.motorRL.setPower(FORWARD_SPEED);robot.motorRR.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }

    public void intake(double t) {
        robot.motorA1.setPower(0.6);
        robot.motorA2.setPower(0.6);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.motorA1.setPower(0);
        robot.motorA2.setPower(0);
    }

    public void outtake(double t) {
        robot.motorA1.setPower(-0.6);
        robot.motorA2.setPower(-0.6);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            telemetry.addData("Path", "Leg1: %2.5f S Elapsed", runtime.seconds());
        }
        robot.motorA1.setPower(0);
        robot.motorA2.setPower(0);
    }
    public void outtakeON() {
        robot.motorA1.setPower(-0.6);
        robot.motorA2.setPower(-0.6);
    }

    public void outtakeOFF() {
        robot.motorA1.setPower(0);
        robot.motorA2.setPower(0);
    }

    public void intakeON() {
        robot.motorA1.setPower(0.8);
        robot.motorA2.setPower(0.8);
    }

    public void intakeOFF() {
        robot.motorA1.setPower(0);
        robot.motorA2.setPower(0);
    }

    public void flipglyph(){
        //robot.flipRight.setPosition(flipRightUp);
        robot.motorA1.setPower(0.8);
        robot.motorA2.setPower(0.8);
        pause(1);
        robot.flipLeft.setPosition(flipLeftUp);
        pause(2);
        //robot.flipRight.setPosition(flipRightDown);
        robot.flipLeft.setPosition(flipLeftDown);
        robot.motorA1.setPower(0);
        robot.motorA2.setPower(0);
        pause(1);
    }

    public void pause(double t) {
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            //telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
            
        }
    }
}
