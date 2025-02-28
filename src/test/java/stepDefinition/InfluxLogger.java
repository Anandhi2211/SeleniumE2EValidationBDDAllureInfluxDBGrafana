package stepDefinition;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

public class InfluxLogger {

    private static final String INFLUXDB_URL = "http://localhost:8086";
    private static final String DATABASE = "allure";
    private static final InfluxDB influxDB = InfluxDBFactory.connect(INFLUXDB_URL);

    static {
        influxDB.setDatabase(DATABASE);
    }
    public static void logTestResult(String testName, String status, long duration) {
        influxDB.write(Point.measurement("ui_tests")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("testName", testName)
                .addField("status", status)
                .addField("duration", duration)
                .build());
    }
}
