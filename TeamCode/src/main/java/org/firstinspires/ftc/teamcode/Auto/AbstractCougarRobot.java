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


public abstract class AbstractCougarRobot extends LinearOpMode {
    public HardwareVator robot   = new HardwareVator();
    public ElapsedTime runtime = new ElapsedTime();
    public double FORWARD_SPEED = 0.4;
    public double TURN_SPEED    = 0.5;
    public double jtdown = 0.57;
    public double jtup = 0.0;
    public double swipefront = 0.7;
    public double swipeback = 1;
    public double swipecenter = 0.9;
    public boolean isRed = false;
    public boolean isFront = false;
    public double flipRightDown = 0.0;
    public double flipRightUp = 0.65;
    public double flipLeftDown = 1.0;
    public double flipLeftUp = 0.35;

    public void lowerJewelTool() {
        robot.jt.setPosition(jtdown);
    }
    
    public void raiseJewelTool() {
        robot.jt.setPosition(jtup);
    }
    
    public void swipeCenter() {
        robot.swipe.setPosition(swipecenter);
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
    
    public boolean getTouch1() {
        if(robot.touch1.getState() == true) {
            return false;
        } else {
            return true;
        }
    }
    
    public void swipeOpponentColor() {
        //touched1IsBlue();
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
            robot.swipe.setPosition(swipeback);
            pause(1);
            robot.swipe.setPosition(swipecenter);
        }
    }
    
    public void swipeFront() {
        if(opModeIsActive()) {
            robot.swipe.setPosition(swipefront);
            pause(1);
            robot.swipe.setPosition(swipecenter);
        }
    }
    
    public void moveTheRightWay() {
        if(isRed) {
            //we are red
            if(isFront) {
                goBackward(1);
            } else {
                goBackward(0.8);
                strafeRight(1.5);
            }
        } else {
            //we are blue
            if(isFront) {
                //we are in the front
                goForward(1);
                turnRight(1.5);
                goBackward(0.5);
                flipglyph();
            } else {
                goForward(1);
                strafeRight(1.5);
            }
        }
    }

    public void strafeLeft(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(FORWARD_SPEED);
        robot.motorFR.setPower(-FORWARD_SPEED);
        robot.motorRL.setPower(FORWARD_SPEED);
        robot.motorRR.setPower(-FORWARD_SPEED);
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
    
    public void strafeRight(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(FORWARD_SPEED);
        robot.motorFR.setPower(-FORWARD_SPEED);
        robot.motorRL.setPower(FORWARD_SPEED);
        robot.motorRR.setPower(-FORWARD_SPEED);
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
    
    public void goForward(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(FORWARD_SPEED);
        robot.motorFR.setPower(FORWARD_SPEED);
        robot.motorRL.setPower(FORWARD_SPEED);
        robot.motorRR.setPower(FORWARD_SPEED);
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

    public void goBackward(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(-FORWARD_SPEED);
        robot.motorFR.setPower(-FORWARD_SPEED);
        robot.motorRL.setPower(-FORWARD_SPEED);
        robot.motorRR.setPower(-FORWARD_SPEED);
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
        robot.motorFL.setPower(-FORWARD_SPEED);
        robot.motorFR.setPower(-FORWARD_SPEED);
        robot.motorRL.setPower(FORWARD_SPEED);
        robot.motorRR.setPower(FORWARD_SPEED);
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

    public void turnRight(double t) {
        // Step 1:  Drive forward for t seconds
        robot.motorFL.setPower(FORWARD_SPEED);
        robot.motorFR.setPower(FORWARD_SPEED);
        robot.motorRL.setPower(-FORWARD_SPEED);
        robot.motorRR.setPower(-FORWARD_SPEED);
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

    public void intakeON() {
        // Step 1:  Drive forward for t seconds
        robot.motorA1.setPower(0.75);
        robot.motorA2.setPower(0.75);
    }

    public void intakeOFF() {
        // Step 1:  Drive forward for t seconds
        robot.motorA1.setPower(0);
        robot.motorA2.setPower(0);
    }
    public void flipglyph(){
        robot.flipRight.setPosition(flipRightUp);
        robot.flipLeft.setPosition(flipLeftUp);
        robot.flipRight.setPosition(flipRightDown);
        robot.flipLeft.setPosition(flipLeftDown);
    }

    public void pause(double t) {
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            //telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
            
        }
    }
}
