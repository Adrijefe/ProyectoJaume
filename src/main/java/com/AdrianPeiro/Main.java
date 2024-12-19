package com.AdrianPeiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            // Cargar propiedades desde el archivo config.ini
            Properties props = new Properties();
            try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.ini")) {
                props.load(inputStream);
            }
            String nom = props.getProperty("nom");
            String descripcion = props.getProperty("descripcion");

            // Leer el archivo JSON
            File json = new File("src/main/resources/mejoresTenistas.json");
            ObjectMapper mapper = new ObjectMapper();
            ListaJugadores listaJugadores = mapper.readValue(json, ListaJugadores.class);

            // Configurar las plantillas Thymeleaf
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("/Templates/");
            templateResolver.setSuffix(".html");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            // Crear el contexto para la plantilla principal
            Context context = new Context();
            context.setVariable("nom", nom);
            context.setVariable("descripcion", descripcion);
            context.setVariable("Jugadores", listaJugadores.getJugadores());

            // Procesar la plantilla principal
            String indexTemplate = templateEngine.process("plantillaindex", context);
            System.out.println(indexTemplate);
            escribirHTML(indexTemplate, "src/main/resources/HTML/index.html");

            // Procesar cada jugador individualmente utilizando indexDetalles.html
            List<Jugadores> jugadores = listaJugadores.getJugadores();
            for (Jugadores j : jugadores) {
                context.setVariable(  "nombre", j.getNombre());
                context.setVariable("pais", j.getPais());
                context.setVariable("titulos_grand_slam", j.getTitulosGrandSlam());
                context.setVariable("anos_actividad", j.getAnosActividad());
                context.setVariable("Imagen", j.getImagen());

                String jugadorTemplate = templateEngine.process("indexDetalles", context);
                String nombreArchivo = "src/main/resources/HTML/" + j.getNombre().replaceAll("\\s+", "") + ".html";
                escribirHTML(jugadorTemplate, nombreArchivo);


            }
            // Generar archivo RSS
            generarRSS("src/main/resources/rss.xml", listaJugadores.getJugadores());
        } catch (IOException e) {
            System.out.println("Error, lo siento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void generarRSS(String rutaArchivo, List<Jugadores> jugadores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<rss version=\"2.0\">\n");
            writer.write("<channel>\n");
            writer.write("<title>Mejores Tenistas</title>\n");
            writer.write("<description>Lista de los mejores tenistas de la historia</description>\n");
            writer.write("<link>http://localhost/tenistas</link>\n");

            for (Jugadores jugador : jugadores) {
                writer.write("<item>\n");
                writer.write("<title>" + jugador.getNombre() + "</title>\n");
                writer.write("<description>\n");
                writer.write("País: " + jugador.getPais() + "\n");
                writer.write("Títulos de Grand Slam: " + jugador.getTitulosGrandSlam() + "\n");
                writer.write("Años de actividad: " + jugador.getAnosActividad() + "\n");
                writer.write("</description>\n");
                writer.write("<link>http://localhost/tenistas/" + jugador.getNombre().replaceAll("\\s+", "") + ".html</link>\n");
                writer.write("</item>\n");
            }
            writer.write("</channel>\n");
            writer.write("</rss>\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void escribirHTML(String html, String nombrefichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombrefichero))) {
            writer.write(html);
        } catch (IOException e) {
            System.out.println(e.getMessage());

            //No va ayuda
        }
    }
}