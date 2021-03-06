/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dingram20
 */
public class Tractor {

    private Attachment attachment;
    private boolean attached, engagePTO;
    private int engineRPM;
    private final int wheelCircumference;

    private void setRPM() {
        this.engineRPM = 24 * this.throttleControl;
    }

    public void engagePTO() {
        this.engagePTO = true;
    }

    public void disengagePTO() {
        this.engagePTO = false;
    }

    public void removeAttachment() {
        if (this.attached == true) {
            this.attachment.remove();
            this.attached = false;
        }
    }

    public enum Gear {

        NEUTRAL, LOW, MID, HIGH
    }
    Gear gear;

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public Tractor() {
        this.throttleControl = 0;
        this.gear = Gear.NEUTRAL;
        this.wheelCircumference = 4;
        this.engineRPM = 0;
        this.attached = false;
        this.engagePTO = false;
    }

    public void startEngine() {
        if ((this.throttleControl >= 10) && (this.gear == Gear.NEUTRAL) && this.engagePTO == false) {
            setRPM();
        }
    }

    public void stopEngine() {
        this.engineRPM = 0;
    }
    private int throttleControl;

    /**
     * Set the value of throttleControl
     *
     * @param throttleControl new value of throttleControl
     */
    public void setThrottle(int throttleControl) {
        //Validate throttle Control
        if (throttleControl >= 0 && throttleControl <= 100) {
            this.throttleControl = throttleControl;
            //check engine on and, if not on, set rpm 
            if (this.engineRPM != 0) {
                this.setRPM();
            }
        }
    }

    private double calculateSpeed() {
        double gearRatio, speed = 0.0;
        switch (this.gear) {
            case LOW:
                gearRatio = 5.0;
                break;
            case MID:
                gearRatio = 10.;
                break;
            case HIGH:
                gearRatio = 20.0;
                break;
            default:
                gearRatio = 0.0;
        }
        if (gearRatio != 0.0) {
            speed = (this.engineRPM / gearRatio * this.wheelCircumference * 60) / 1000;
        }
        return speed;
    }

    public void attach(Attachment attachment) {
        if (this.attached == false) {
            this.attached = true;
            this.attachment = attachment;
            this.attachment.attach();
        } 
    }

    @Override
    public String toString() {
        String started, ptoStatus, equipment = "Nothing Attached";
        if (this.engineRPM == 0) {
            started = "not started";
        } else {
            started = "started";
        }

        if (this.engagePTO) {
            ptoStatus = "engaged";
        } else {
            ptoStatus = "disengaged";
        }
        if (this.attached==true) {
            equipment = this.attachment.toString();    
        }
        return "Tractor: " + started + ", throttle= " + this.throttleControl + ", engine rpm= " + this.engineRPM + "kpm, tractor speed = " + calculateSpeed() + ", gear = " + this.gear + " PTO: " + ptoStatus + " Attachments: " + equipment;
    }
}
