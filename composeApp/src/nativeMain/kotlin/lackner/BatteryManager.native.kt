package lackner

actual class BatteryManager {
    actual fun getBatteryLevel(): Int {
        UIDevice.currentDevice.batteryMonitoringEnabled = true
        val batteryLevel = UIDevice.cirrentDevice.batteryLevel

        return (batteryLevel*100).roundToInt()
    }
}