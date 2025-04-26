/**
 * Metodo de conexion convencional definiendo variales
 * Poco Seguro, recomendación solo usar en Entorno de Desarrollo
*/

package development.team.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import development.team.Exceptions.ConectionNotBDException;

import javax.sql.DataSource;

public class DataBaseUtil {
    // Variables de configuración
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3316";
    private static final String DB_NAME = "dbbeltcorecg"; // Ajusta si tu BD tiene otro nombre
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver"; // Cambiado a MySQL

    // Construimos la URL de MySQL
    private static final String DB_URL = String.format(
            "jdbc:mysql://%s:%s/%s?serverTimezone=UTC", DB_HOST, DB_PORT, DB_NAME
    );

    // Configuración del pool
    private static final int MAX_POOL_SIZE = 10;
    private static final int MIN_IDLE_CONNECTIONS = 2;
    private static final long IDLE_TIMEOUT = 120_000; // 2 minutos
    private static final long CONNECTION_TIMEOUT = 30_000; // 30 segundos
    private static final long VALIDATION_TIMEOUT = 10_000; // 10 segundos
    private static final long MAX_LIFETIME = 1_800_000; // 30 minutos
    private static final String TEST_QUERY = "SELECT 1";

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        try {
            // Configuración del pool de conexiones
            config.setJdbcUrl(DB_URL);
            config.setUsername(DB_USERNAME);
            config.setPassword(DB_PASSWORD);
            config.setDriverClassName(DB_DRIVER);

            // Configuración del tamaño del pool
            config.setMaximumPoolSize(MAX_POOL_SIZE);
            config.setMinimumIdle(MIN_IDLE_CONNECTIONS);

            // Configuración de tiempos
            config.setIdleTimeout(IDLE_TIMEOUT);
            config.setConnectionTimeout(CONNECTION_TIMEOUT);
            config.setValidationTimeout(VALIDATION_TIMEOUT);
            config.setMaxLifetime(MAX_LIFETIME);

            // Prueba de conectividad
            config.setConnectionTestQuery(TEST_QUERY);

            // Inicializar DataSource
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new ConectionNotBDException(
                    "Error al inicializar la conexión a la base de datos: " + e.getMessage()
            );
        }
    }

    // Método para obtener el DataSource
    public static DataSource getDataSource() {
        return dataSource;
    }

    // Método para cerrar el DataSource al finalizar la aplicación
    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}

/**
 * Metodo de conexion mejorado mediante archivo de configuracion
 * Clase Java asociada a lectura de archivo: ConfigLoader
 * Mayor seguridad y escalabilidad, recomendación usar en Entorno de Producción
*/

/*package development.team.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import development.team.Exceptions.ConectionNotBDException;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DataBaseUtil {
    // Configuración del pool
    private static final int MAX_POOL_SIZE = 10;
    private static final int MIN_IDLE_CONNECTIONS = 2;
    private static final long IDLE_TIMEOUT = 120_000; // 2 minutos
    private static final long CONNECTION_TIMEOUT = 30_000; // 30 segundos
    private static final long VALIDATION_TIMEOUT = 10_000; // 10 segundos
    private static final long MAX_LIFETIME = 1_800_000; // 30 minutos
    private static final String TEST_QUERY = "SELECT 1";

    private static final HikariDataSource dataSource;

    static {
        try {
            Properties db = ConfigLoader.loadSection("DATABASE_BELTCORE");

            String dbType = db.getProperty("DB_TYPE");
            String host = db.getProperty("HOST");
            String name = db.getProperty("DATABASE");
            String user = db.getProperty("USERNAME");
            String pass = db.getProperty("PASSWORD");

            if (dbType == null || host == null || name == null || user == null || pass == null) {
                throw new IllegalArgumentException("Faltan claves en config.ini");
            }

            String url, driver;
            switch (dbType.toUpperCase()) {
                case "MYSQL":
                    url = "jdbc:mysql://" + host + "/" + name + "?serverTimezone=UTC";
                    driver = "com.mysql.cj.jdbc.Driver";
                    break;
                case "SQLSERVER":
                    url = "jdbc:sqlserver://" + host + ";databaseName=" + name;
                    driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    break;
                case "ORACLE":
                    url = "jdbc:oracle:thin:@" + host + ":" + name;
                    driver = "oracle.jdbc.driver.OracleDriver";
                    break;
                case "MARIADB":
                    url = "jdbc:mariadb://" + host + "/" + name;
                    driver = "org.mariadb.jdbc.Driver";
                    break;
                default:
                    System.out.println("❌ DB_TYPE no soportado: " + dbType);
                    throw new UnsupportedOperationException("DB_TYPE no soportado: " + dbType);
            }

            System.out.println("🔵 Driver seleccionado: " + driver);

            HikariConfig config = new HikariConfig();
            try {
                // Configuración del pool de conexiones
                config.setJdbcUrl(url);
                config.setUsername(user);
                config.setPassword(pass);
                config.setDriverClassName(driver);

                // Configuración del tamaño del pool
                config.setMaximumPoolSize(MAX_POOL_SIZE);
                config.setMinimumIdle(MIN_IDLE_CONNECTIONS);

                // Configuración de tiempos
                config.setIdleTimeout(IDLE_TIMEOUT);
                config.setConnectionTimeout(CONNECTION_TIMEOUT);
                config.setValidationTimeout(VALIDATION_TIMEOUT);
                config.setMaxLifetime(MAX_LIFETIME);

                // Prueba de conectividad
                config.setConnectionTestQuery(TEST_QUERY);

                // Inicializar DataSource
                dataSource = new HikariDataSource(config);
            } catch (Exception e) {
                throw new ConectionNotBDException(
                        "Error al inicializar la conexión a la base de datos: " + e.getMessage()
                );
            }

        } catch (IOException | IllegalArgumentException | UnsupportedOperationException ex) {
            System.out.println("❌ Error en DataBaseUtil: " + ex.getMessage());
            throw new ConectionNotBDException("Error al cargar DB de BELTCORE: " + ex.getMessage());
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            System.out.println("🔵 Cerrando pool de conexiones...");
            dataSource.close();
            System.out.println("✅ Pool de conexiones cerrado.");
        }
    }
}*/
