package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.HardwareVator;

import static java.lang.Math.round;
//codingame


public abstract class AbstractEncoders extends LinearOpMode {
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

    public String identifyTarget(double tickms) {
        return robot.identifyTarget(tickms);
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

    public double turn90 = 1;

    public void moveTheRightWayUsingIntake() {
        //goE(revolution, speed); 12.56" per revolution
        // turnE(direction 1=right & -1=left, revolution, speed)
        if(isRed) {
            //we are red
            if(isFront) {
                //red front
                goE(-2.5, 0.3,3); //board to CB
                pause(0.5);
                turnE(-1,0.3,turn90); //face CB
                pause(0.5);
                goE(0.4,0.3,1); //approach CB
                pause(0.5);
                outtakeON(); //spit glyph
                pause(1);
                goE(-0.4,0.3,1); //back up
                pause(0.5);
                goE(0.4, 0.3,1); //push forward
                pause(0.5);
                goE(-0.4,0.3,1); //back up
                outtakeOFF();
            } else {
                //red back
                goE(-2.5, 0.3,3); //board to approach
                pause(0.5);
                turnE(1,0.3,turn90); //turn to parallel CB
                pause(0.5);
                goE(1,0.3,1); //move to center to CB
                //strafeRight(1.6);
                raiseJewelTool();
                turnE(1,0.3,turn90); //turn to face CB
                pause(0.5);
                goE(1,0.3,1); //approach CB
                raiseJewelTool();
                outtakeON(); //spit glyph
                pause(1);
                raiseJewelTool();
                goE(-1,0.3,1); //back up
                pause(0.5);
                goE(1,0.3,1); //push forward
                pause(0.5);
                goE(-.75,0.3,1); //back up
                outtakeOFF();
            }
        } else {
            //we are blue
            if(isFront) {
                //we are blue front
                goE(1.9, 0.5,3); //board to CB
                pause(0.5);
                turnE(-1,0.3,turn90);
                pause(0.5);
                goE(0.9,0.3,3); //need to move towards the box a bit
                pause(0.5);
                outtakeON();
                pause(1);
                goE(-.2,0.3,3);
                pause(0.5);
                goE(0.3, 0.3,3);
                pause(0.5);
                goE(-3,0.3,3);
                outtakeOFF();
            } else {
                //we are blue back
                goE(1,0.3,1);
                pause(0.5);
                //strafeRight(1.6);
                turnE(1,0.3,turn90);
                pause(0.5);
                goE(1,0.3,1);
                pause(0.5);
                turnE(-1,0.3,turn90);
                pause(0.5);
                goE(1,0.3,1);
                outtakeON();
                pause(1);
                goE(-1,0.3,1);
                pause(0.5);
                goE(1,0.3,1);
                pause(0.5);
                goE(-.5,0.3,1);
                outtakeOFF();
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

    void debugEncoder(double r, double speed, double timeout, String whichMotor) {
        /*
            This will test one motor
            call this function with the motor you want as the last param like
            debugEncoder( 3, 0.3, 5, "FR"); //to debug the front right motor
        */
        int count;
        DcMotor targetMotor = null;

        //one rotation is 14.5 inches

        count = (int) Math.round(-r*1.5*537.6);
        if(whichMotor.equals("FL")) {
            targetMotor = robot.motorFL;
        } else if(whichMotor.equals("FR")) {
            targetMotor = robot.motorFR;
        } else if(whichMotor.equals("RL")) {
            targetMotor = robot.motorRL;
        } else {
            targetMotor = robot.motorRR;
        }
        targetMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        targetMotor.setTargetPosition(count);
        targetMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        targetMotor.setPower(speed);
        runtime.reset();
        while (opModeIsActive() && targetMotor.isBusy() && (runtime.seconds() < timeout)){
            telemetry.addData("Motor",whichMotor);
            telemetry.addData("Running to",  "%7d", count);
            telemetry.addData("Current Pos",  "%7d", targetMotor.getCurrentPosition());
            telemetry.update();
        }
        targetMotor.setPower(0);
    }

    public void goE(double r, double speed, double timeout) {
        // Step 1: Drive forward for r revolutions (280 encoder counts per rev);
        // Step 2: Set power level
        // Step 3: Set timeout
        int count;

        //one rotation is 14.5 inches

        count = (int) Math.round(-r*1.5*537.6);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorRL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorRR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setTargetPosition(count); robot.motorFR.setTargetPosition(count);
        robot.motorRL.setTargetPosition(count); robot.motorRR.setTargetPosition(count);
        //robot.motorFL.setPower(speed); robot.motorFR.setPower(speed);
        //robot.motorRL.setPower(speed); robot.motorRR.setPower(speed);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorRL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorRR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        runtime.reset();

        robot.motorFL.setPower(speed);
        robot.motorFR.setPower(speed);
        robot.motorRL.setPower(speed);
        robot.motorRR.setPower(speed);
        while (opModeIsActive() &&
                (robot.motorFR.isBusy() && robot.motorRR.isBusy()) &&
                ((runtime.seconds() < timeout) )){

            telemetry.addData("Fwd/Bck",  "Running to %7d :%7d", count, count);
            telemetry.addData("FrntPos",  "FL %7d : FR %7d", robot.motorFL.getCurrentPosition(), robot.motorFR.getCurrentPosition());
            telemetry.addData("BackPos", "RL %7d : RR %7d", robot.motorRL.getCurrentPosition(), robot.motorRR.getCurrentPosition());
            telemetry.update();

        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }
    public void turnE(int dir, double speed, double timeout) {
        // Step 1: Set direction 1 is right, -1 is left
        // Step 2: Drive forward for r revolutions (280 encoder counts per rev);
        // Step 3: Set power level
        // Step 4: Set timeout

        //for a quarter turn, r should be 1.3

        //int count;
        //count = (int) Math.round(-r * 1.5 * 537.6);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorRL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorRR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //robot.motorFL.setTargetPosition(dir * count);
        //robot.motorFR.setTargetPosition(-dir * count);
        //robot.motorRL.setTargetPosition(dir * count);
        //robot.motorRR.setTargetPosition(-dir * count);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorRL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorRR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        runtime.reset();
        robot.motorFL.setPower(dir*speed); robot.motorFR.setPower(-dir*speed);
        robot.motorRL.setPower(dir*speed); robot.motorRR.setPower(-dir*speed);
        while (opModeIsActive() &&
            (runtime.seconds() < timeout)){
            //telemetry.addData("Turn",  "Running to %7d :%7d", dir*count, dir*count);
            telemetry.addData("FrntPos",  "FL %7d : FR %7d", robot.motorFL.getCurrentPosition(), robot.motorFR.getCurrentPosition());
            telemetry.addData("BackPos", "BL %7d : BR %7d", robot.motorRL.getCurrentPosition(), robot.motorRR.getCurrentPosition());
            telemetry.update();
        }
        robot.motorFL.setPower(0);robot.motorFR.setPower(0);
        robot.motorRL.setPower(0);robot.motorRR.setPower(0);
    }


    public void rightE(double r, double speed, double timeout) {
        // Step 2: Drive forward for r revolutions (280 encoder counts per rev);
        // Step 3: Set power level
        // Step 4: Set timeout

        //for a quarter turn, r should be 1.3

        int count;
        count = (int) Math.round(-r * 1.5 * 537.6);
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorRL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFL.setTargetPosition(count);
        robot.motorRL.setTargetPosition(count);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorRL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        runtime.reset();
        robot.motorFL.setPower(speed);
        robot.motorRL.setPower(speed);
        while (opModeIsActive() &&
                (robot.motorFL.isBusy() ||
                 robot.motorRL.isBusy()
                ) &&
                (runtime.seconds() < timeout)
            ){
            telemetry.addData("Turn",  "Running to %7d :%7d", count, count);
            telemetry.addData("Position",  "Running to %7d :%7d", robot.motorFL.getCurrentPosition(), robot.motorRL.getCurrentPosition());
            telemetry.update();
            telemetry.update();
        }
        robot.motorFL.setPower(0);
        robot.motorRL.setPower(0);
    }

    public void leftE(double r, double speed, double timeout) {
        // Step 2: Drive forward for r revolutions (280 encoder counts per rev);
        // Step 3: Set power level
        // Step 4: Set timeout

        //for a quarter turn, r should be 1.3

        int count;
        count = (int) Math.round(-r * 1.5 * 537.6);
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorRR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFR.setTargetPosition(count);
        robot.motorRR.setTargetPosition(count);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorRR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        runtime.reset();
        robot.motorFR.setPower(speed);
        robot.motorRR.setPower(speed);
        while (opModeIsActive() &&
                (robot.motorFR.isBusy() ||
                 robot.motorRR.isBusy()
                ) &&
                (runtime.seconds() < timeout)
                ){
            telemetry.addData("Turn",  "Running to %7d :%7d", count, count);
            telemetry.addData("Position",  "Running to %7d :%7d", robot.motorFR.getCurrentPosition(), robot.motorRR.getCurrentPosition());
            telemetry.update();
            telemetry.update();
        }
        robot.motorFR.setPower(0);
        robot.motorRR.setPower(0);
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
