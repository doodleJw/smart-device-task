import java.util.Scanner;

abstract class SmartDevice {
    public String name;

    public SmartDevice(String name){
        this.name = name;
    }

    abstract String getStatus();
}

class Lamp extends SmartDevice{
    private int brightCurr;
    private int brightMax;
    private boolean status = false;
    Scanner scan = new Scanner(System.in);

    public Lamp(String name, int brightCurr, int brightMax) {
        super(name);
        this.brightCurr = brightCurr;
        this.brightMax = brightMax;
    }

    public int turnOn(){
        isTurnOn(true);
        if (brightCurr == 0) {
            brightCurr = 50;
        }
        return brightCurr;
    }

    public int turnOff(){
        isTurnOn(false);
        brightCurr = 0;
        return brightCurr;
    }

    public int setBrightness(){
        int needBright;
        System.out.print("Введите яркость (1-" + brightMax + "): ");
        while(true) {
            needBright = scan.nextInt();
            if (needBright >= 1 && needBright <= brightMax) {
                brightCurr = needBright;
                isTurnOn(true);
                break;
            }
            else {
                System.out.print("Ошибка. Введите число от 1 до " + brightMax + ": ");
            }
        }
        return brightCurr;
    }

    public boolean isTurnOn(boolean status1){
        status = status1;
        return status;
    }

    String getStatus(){
        if(status){
            return String.format("%s: ВКЛ, яркость %d/%d", name, brightCurr, brightMax);
        }
        else{
            return String.format("%s: ВЫКЛ", name);
        }
    }
}

class Thermostat extends SmartDevice{
    private int tempTarget;
    private int tempMax;
    private int tempMin;
    private int tempCurrent;
    private String mode = "Ожидание";

    public Thermostat(String name, int tempMin, int tempMax, int tempCurrent) {
        super(name);
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.tempCurrent = tempCurrent;
        this.tempTarget = tempCurrent;
    }

    public int setTemperature(int needTarg){
        if (needTarg >= tempMin && needTarg <= tempMax) {
            tempTarget = needTarg;
            if (tempCurrent < tempTarget) {
                mode = "Нагрев";
            } else if (tempCurrent > tempTarget) {
                mode = "Охлаждение";
            } else {
                mode = "Ожидание";
            }
        } else {
            System.out.println("Ошибка: Температура вне диапазона (" + tempMin + " - " + tempMax + ")");
        }
        return tempTarget;
    }

    String getStatus(){
        return String.format("%s: Текущая %d°C, Целевая %d°C, Режим: %s",
                name, tempCurrent, tempTarget, mode);
    }
}

class Blinds extends SmartDevice{
    private int angleCurr;
    private int angleMax;
    private boolean status = false;

    public Blinds(String name, int angleMax) {
        super(name);
        this.angleMax = angleMax;
        this.angleCurr = 0;
    }

    public void closeBlinds(){
        isClosed(true);
        angleCurr = 0;
    }

    public void openBlinds(){
        isClosed(false);
        angleCurr = angleMax;
    }

    public int setAngle(int needAngle){
        if (needAngle >= 0 && needAngle <= angleMax) {
            isClosed(false);
            angleCurr = needAngle;
        } else {
            System.out.println("Ошибка: Угол вне диапазона (0-" + angleMax + ")");
        }
        return angleCurr;
    }

    String getStatus(){
        return String.format("%s: Угол %d°/%d°", name, angleCurr, angleMax);
    }

    public boolean isClosed(boolean status1){
        status = status1;
        return status;
    }
}