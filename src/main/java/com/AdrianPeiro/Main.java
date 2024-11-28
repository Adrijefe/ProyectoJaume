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

            // Configurar el motor de plantillas Thymeleaf
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("/Templates/");
            templateResolver.setSuffix(".html");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            // Crear el contexto para la plantilla principal
            Context context = new Context();
            context.setVariable("nombre", nom);
            context.setVariable("descripcion", descripcion);
            context.setVariable("Jugadores", listaJugadores.getJugadores());

            // Procesar la plantilla principal
            String indexTemplate = templateEngine.process("plantillaindex", context);
            System.out.println(indexTemplate);
            escribirHTML(indexTemplate, "src/main/resources/HTML/index.html");

            // Procesar cada jugador individualmente utilizando indexDetalles.html
            List<Jugadores> jugadores = listaJugadores.getJugadores();
            for (Jugadores j : jugadores) {
                context.setVariable("nombre", j.getNombre());
                context.setVariable("pais", j.getPais());
                context.setVariable("titulos_grand_slam", j.getTitulosGrandSlam());
                context.setVariable("anos_actividad", j.getAnosActividad());
                context.setVariable("Imagen", j.getImagen());

                String jugadorTemplate = templateEngine.process("indexDetalles", context);
                String nombreArchivo = "src/main/resources/HTML/" + j.getNombre().replaceAll("\\s+", "_") + ".html";
                escribirHTML(jugadorTemplate, nombreArchivo);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void escribirHTML(String html, String nombrefichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombrefichero))) {
            writer.write(html);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo HTML: " + e.getMessage());
            throw new RuntimeException("Error al escribir el archivo HTML", e);
        }
    }
}