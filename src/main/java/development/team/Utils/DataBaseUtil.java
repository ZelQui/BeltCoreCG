package development.team.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import development.team.Exceptions.ConectionNotBDException;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DataBaseUtil {

    // ================================
    // Parámetros generales del pool
    // ================================
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
        String dbvariable = null;
        try {
            // ================================
            // OPCIÓN 1: DESARROLLO (ACTIVO POR DEFECTO)
            // Metodo de conexion convencional definiendo variales
            // Poco Seguro, recomendación solo usar en Entorno de Desarrollo*/
            // ================================
            String DB_HOST = "159.112.132.125";
            String DB_PORT = "3306";
            String DB_NAME = "dbBeltcoreCG";
            String DB_USERNAME = "admon";
            String DB_PASSWORD = "integrador2";
            String DB_URL = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", DB_HOST, DB_PORT, DB_NAME);


            // ================================
            // OPCIÓN 2: PRODUCCIÓN (Metodo de conexion mediante archivo de configuracion)
            // Clase Java asociada a lectura de archivo: ConfigLoader
            // Mayor seguridad y escalabilidad, recomendación usar en Entorno de Producción
            // ================================
            /*
            dbvariable = "DATABASE_BELTCORE";
            Properties db = ConfigLoader.loadSection(dbvariable);

            String DB_HOST = db.getProperty("HOST");
            String DB_PORT = db.getProperty("PORT", "3306"); // valor por defecto si no se configura
            String DB_NAME = db.getProperty("DATABASE");
            String DB_USERNAME = db.getProperty("USERNAME");
            String DB_PASSWORD = db.getProperty("PASSWORD");

            if (DB_HOST == null || DB_NAME == null || DB_USERNAME == null || DB_PASSWORD == null) {
                throw new IllegalArgumentException("Faltan claves en config.ini");
            }
            String DB_URL = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", DB_HOST, DB_PORT, DB_NAME);
            */

            // Aplica la configuración general
            aplicarConfiguracion(config, DB_URL, DB_USERNAME, DB_PASSWORD);

            // Inicializar el DataSource
            dataSource = new HikariDataSource(config);

        /*} catch (IOException | IllegalArgumentException ex) {
            System.out.println("❌ Error en DataBaseUtil: " + ex.getMessage());
            throw new ConectionNotBDException("Error al cargar config de " + dbvariable + ": " + ex.getMessage());*/
        } catch (Exception e) {
            throw new ConectionNotBDException("Error al inicializar conexión a la BD: " + e.getMessage());
        }
    }

    // Método común para configurar el pool de Hikari
    private static void aplicarConfiguracion(HikariConfig config, String url, String username, String password) {
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        config.setMaximumPoolSize(MAX_POOL_SIZE);
        config.setMinimumIdle(MIN_IDLE_CONNECTIONS);
        config.setIdleTimeout(IDLE_TIMEOUT);
        config.setConnectionTimeout(CONNECTION_TIMEOUT);
        config.setValidationTimeout(VALIDATION_TIMEOUT);
        config.setMaxLifetime(MAX_LIFETIME);
        config.setConnectionTestQuery(TEST_QUERY);
    }

    // Método para obtener el DataSource
    public static DataSource getDataSource() {
        return dataSource;
    }

    // Método para cerrar el DataSource
    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            System.out.println("🔵 Cerrando pool de conexiones...");
            dataSource.close();
            System.out.println("✅ Pool de conexiones cerrado.");
        }
    }
}

