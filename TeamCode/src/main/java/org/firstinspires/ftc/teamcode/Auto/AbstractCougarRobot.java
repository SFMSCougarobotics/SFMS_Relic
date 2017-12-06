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
    public double flipfront = 0.7;
    public double flipback = 1;
    public double flipcenter = 0.9;
    public boolean isRed = false;
    public boolean isFront = false;

    public void touched1IsBlue() {
        if(getTouch1()) {
            isRed=false;
        } else {
            isRed=true;
        }
    }
    
    public void lowerJewelTool() {
        robot.jt.setPosition(jtdown);
    }
    
    public void raiseJewelTool() {
        robot.jt.setPosition(jtup);
    }
    
    public void flipCenter() {
        robot.flip.setPosition(flipcenter);
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
    
    public void flipOpponentColor() {
        //touched1IsBlue();
        if(isRed) {
            if(seeBlue()) {
                flipFront();
            } else {
                flipBack();
            }
        } else {
            if(seeBlue()) {
                flipBack();
            } else {
                flipFront();
            }
        }
    }
    
    public void flipBack() {
        if(opModeIsActive()) {
            robot.flip.setPosition(flipback);
            pause(1);
            robot.flip.setPosition(flipcenter);
        }
    }
    
    public void flipFront() {
        if(opModeIsActive()) {
            robot.flip.setPosition(flipfront);
            pause(1);
            robot.flip.setPosition(flipcenter);
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
    
    public void pause(double t) {
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
            //telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
            
        }
    }
}
