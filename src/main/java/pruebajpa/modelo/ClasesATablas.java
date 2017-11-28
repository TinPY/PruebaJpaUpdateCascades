
package pruebajpa.modelo;

// Clases del Modelo a Mapear (Agregar las necesarias para cada Módulo)


import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClasesATablas {
        private Configuration config = null;
        
        public ClasesATablas() {
                config = new Configuration();
        }
        
        public ClasesATablas setDialect(String dialect) {
                config.setProperty(AvailableSettings.DIALECT, dialect);
                return this;
        }
        
        /**
         * Metodo que determina cuales clases se usara para la generacion del DDL
         * @param annotatedClasses - entidades anotadas con Hibernate
         */
        public ClasesATablas addAnnotatedClasses(Class<?>[] annotatedClasses) {
                for (Class<?> clazz : annotatedClasses)
                        config.addAnnotatedClass(clazz);
                return this;
        }
        
        /**
         * Metodo genera traduccion de entidades en esquemas de tabla
         * Genera scripts 'CREATE' y 'DELETE' para las entidades Hibernate
         * Implementacion actual incluye el uso del metodo {@link #write(FileOutputStream, String[], Formatter)}
         * @param outputStream - stream que se usara para la creacion del archivo *.sql
         * @throws IOException
         */
        public ClasesATablas translate(FileOutputStream outputStream) throws IOException {
                Dialect requiredDialect = Dialect.getDialect(config.getProperties());
                String[] query = null;
                
                query = config.generateDropSchemaScript(requiredDialect);
                write(outputStream, query, FormatStyle.DDL.getFormatter());
                
                query = config.generateSchemaCreationScript(requiredDialect);
                write(outputStream, query, FormatStyle.DDL.getFormatter());
                
                return this;
        }
        
        /**
         * El metodo escribe linea por linea los scripts DDL en el stream de salida.
         * Tambien cada linea aparece en la consola.
         * @throws IOException
         */
        private void write(FileOutputStream outputStream, String[] lines, Formatter formatter)
                        throws IOException {
                String tempStr = null;
                
                for (String line : lines) {
                        tempStr = formatter.format(line)+";";
                        /*System.out.println(tempStr);*/
                        outputStream.write(tempStr.getBytes());
                }
        }
        
        public static void main(String[] args) throws IOException {
                ClasesATablas translator = new ClasesATablas();
                
                // Agregar las clases a mapear (deben contener anotaciones Hibernate)
                Class<?>[] entityClasses = {

//                                Estado.class,
//                                Solicitud.class,
//                                  Escuela.class,
//                                  Departamento.class,
                                  Persona.class,
                                  Proyecto.class,
                                  Etapa.class,
                                  Tarea.class

//                                  SolicitudDesignacion.class
                                };
                
//                translator.setDialect("org.hibernate.dialect.HSQLDialect")
//                        .addAnnotatedClasses(entityClasses)
//                        .translate(new FileOutputStream(new File("db-schema.sql")));
                
//                translator.setDialect("org.hibernate.dialect.MySQLDialect")
//                .addAnnotatedClasses(entityClasses)
//                .translate(new FileOutputStream(new File("db-estructura.sql")));
                
                //translator.setDialect("org.hibernate.dialect.MySQLDialect")
                translator.setDialect("org.hibernate.dialect.PostgreSQLDialect")
                .addAnnotatedClasses(entityClasses)
                .translate(new FileOutputStream(new File("src/main/resources/dbscripts/estructuraPostgres.sql")));

        }

}