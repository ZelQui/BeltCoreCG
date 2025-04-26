package development.team.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Importante que se haya definido en el sistema el JAVA_HOME y PATH
 * Se debe crear un archivo de configuración: "config.ini" dentro de la ruta del jdk, directorio raiz
 * Revisar siempre que la estructura de conexion de un nuevo proyecto esté bien definida:
     ; Configuración para la aplicación NOMBRE
     [DATABASE_NOMBRE]
     DB_TYPE=MYSQL
     HOST=ip:port
     DATABASE=***
     USERNAME=***
     PASSWORD=***
*/

public class ConfigLoader {

    // Caché para almacenar las configuraciones por sección
    private static final Map<String, Properties> cache = new HashMap<>();

    // Método para limpiar la caché
    public static void clearCache() {
        cache.clear();
    }

    public static Properties loadSection(String section) throws IOException {
        // Primero, verificar si la sección está en la caché
        if (cache.containsKey(section)) {
            System.out.println("Sección cargada desde caché: " + section);
            return cache.get(section);
        }

        // Si la sección no está en caché, leer el archivo config.ini
        System.out.println("Cargando sección desde archivo: " + section);

        // Obtener la variable de entorno JAVA_HOME
        String javaHome = System.getenv("JAVA_HOME");

        if (javaHome == null || javaHome.isEmpty()) {
            throw new IOException("La variable de entorno JAVA_HOME no está definida.");
        }

        // Construir la ruta completa al archivo config.ini
        String configPath = javaHome + "\\config.ini";

        // Verificar si el archivo config.ini existe en esa ruta
        File configFile = new File(configPath);
        if (!configFile.exists()) {
            throw new IOException("Archivo config.ini no encontrado en la ruta: " + configPath);
        }

        // Cargar el archivo de propiedades
        Properties properties = new Properties();
        boolean sectionFound = false;

        try (FileInputStream fis = new FileInputStream(configFile)) {
            // Leer el archivo completo en memoria
            byte[] fileContent = fis.readAllBytes();
            String fileContentStr = new String(fileContent);

            // Dividir el archivo en secciones
            String[] sections = fileContentStr.split("\\["); // Divide por las secciones, que empiezan con "["

            for (String sectionContent : sections) {
                // Comprobar si esta sección es la que estamos buscando
                if (sectionContent.startsWith(section)) {
                    sectionFound = true;
                    String[] lines = sectionContent.split("\n"); // Separar las líneas de la sección

                    // Procesar las propiedades de la sección
                    for (String line : lines) {
                        line = line.trim();
                        if (!line.startsWith(";") && !line.isEmpty()) {
                            int index = line.indexOf("=");
                            if (index > 0) {
                                String key = line.substring(0, index).trim();
                                String value = line.substring(index + 1).trim();
                                properties.setProperty(key, value);
                            }
                        }
                    }
                    break; // Ya encontramos la sección, salimos del bucle
                }
            }

        } catch (IOException e) {
            throw e;
        }

        // Si no se encuentra la sección, lanzamos una excepción
        if (!sectionFound) {
            throw new IOException("Sección no encontrada: " + section);
        }

        // Almacenar la sección cargada en la caché para futuras consultas
        cache.put(section, properties);
        System.out.println("Sección cargada y almacenada en caché: " + section);

        return properties;
    }
}
