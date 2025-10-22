package devices;

public interface Device {
    void turnOn();              // включить устройство
    void turnOff();             // выключить устройство
    void performFunction();     // выполнить основную функцию (статус/действие)
    boolean isOn();             // текущее состояние питания
    String getName();           // человеко-читаемое имя устройства
}
