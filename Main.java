public class Main {
    public static void main(String[] args){

        Lamp lamp = new Lamp("Лампа", 0, 100);
        Thermostat thermostat = new Thermostat("Термостат", 15, 30, 20);
        Blinds blinds = new Blinds("Жалюзи", 90);
        SmartDevice[] devices = {lamp, thermostat, blinds};

        System.out.println("--- Начальное состояние ---");
        for (SmartDevice device : devices) {
            System.out.println(device.getStatus());
        }

        System.out.println("\n--- Тестирование лампы ---");
        lamp.turnOn();
        lamp.getStatus();
        lamp.setBrightness();
        System.out.println(lamp.getStatus());

        System.out.println("\n--- Тестирование термостата ---");
        thermostat.setTemperature(16);
        System.out.println(thermostat.getStatus());

        System.out.println("\n--- Тестирование жалюзи ---");
        blinds.setAngle(45);
        System.out.println(blinds.getStatus());

        System.out.println("\n--- Итоговое состояние ---");
        for (SmartDevice device : devices) {
            System.out.println(device.getStatus());
        }
    }
}