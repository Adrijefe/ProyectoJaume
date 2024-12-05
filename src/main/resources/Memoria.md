# Introducción

Mi proyecto tiene como objetivo principal la creación automática de páginas web dinámicas que la he hecho de jugadores de tenis, partiendo de un archivo JSON que contiene información relevante sobre cada jugador. El desarrollo integra tecnologías como **Java**, **Thymeleaf** y **CSS**, lo que permite la generación eficiente de contenido estructurado y visualmente atractivo (esa es la intención mas o menos). También se incluye la creación de un archivo **RSS**, facilitando la distribución de información mediante lectores de este tipo.

A lo largo del desarrollo se experimentaron retos en la implementación de CSS, el manejo de plantillas HTML y la manipulación dinámica de datos. Este documento detalla las herramientas utilizadas, los desafíos encontrados y las soluciones aplicadas.

---

## Objetivos del Proyecto

- Automatizar la generación de páginas web para jugadores de tenis a partir de datos en formato JSON.
- Utilizar **Thymeleaf** como motor de plantillas para combinar datos y diseño.
- Diseñar un estilo visual medianamente atractivo mediante **CSS**.
- Crear un archivo **RSS** que permita distribuir información estructurada de manera correcta.
- Aprender y resolver problemas comunes en proyectos de desarrollo web.

---

## Tecnologías Utilizadas

- **Java**: Para la manipulación de datos JSON,HTLM y la creacion del RSS.
- **Thymeleaf**: Para la generación dinámica de contenido HTML.
- **CSS**: Para la personalización del diseño visual.
- **JSON**: Como fuente de datos estructurados.
- **RSS**: Para el formato de distribución de contenido.

---

## Desarrollo del Proyecto

### Estructura de los Datos

El archivo JSON contenía información clave de los jugadores de tenis:
```json
{
  "jugadores": [
    {
      "nombre": "RogerFederer",
      "pais": "Suiza",
      "titulos_grand_slam": 20,
      "anos_actividad": "1998-2021",
      "Imagen": "https://media.revistagq.com/photos/61522a221d29f507cd989f7a/16:9/w_2560%2Cc_limit/GettyImages-1327542158.jpg"
    },
    {
      "nombre": "RafaelNadal",
      "pais": "España",
      "titulos_grand_slam": 22,
      "anos_actividad": "2001-2024",
      "Imagen": "https://www.centrosenlinea.com/wp-content/uploads/2021/03/Rafa-Nadal-foto-portada.jpg"
    }
  ]
}

```
Como vemos arriba cada jugador contiene el nombre, el pais del cual pertenece, los titulos grandSlams que tiene, los años que ha estado en la élite 

---
# Diseño con CSS

## CSS Utilizado

```css
body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
    color: #333;
    margin: 0;
    padding: 0;
}

header {
    background-color: #0073e6;
    color: white;
    text-align: center;
    padding: 20px 0;
    margin-bottom: 20px;
}

header h1 {
    margin: 0;
    font-size: 2.5em;
}

header p {
    margin: 5px 0 0;
    font-size: 1.2em;
}

div {
    padding: 20px;
}

h2 {
    font-size: 1.8em;
    color: #0073e6;
    margin-bottom: 10px;
}

ul {
    list-style: none;
    padding: 0;
}

li {
    background: #ffffff;
    margin: 10px 0;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 5px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

li span {
    font-size: 1.2em;
    font-weight: bold;
    color: #333;
}

li a {
    text-decoration: none;
    color: #0073e6;
    font-weight: bold;
    font-size: 1em;
}

li a:hover {
    text-decoration: underline;
    color: #005bb5;
}

````
Este es el CSS que hemos utilizado para el índece,basicamente para el incio de la web.

````css
body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
    color: #333;
    margin: 0;
    padding: 0;
    line-height: 1.6;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    text-align: center;
}

header {
    padding: 20px;
    background-color: #0073e6;
    color: white;
    border-radius: 10px; // Bordes redondeados
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 600px;
    margin-bottom: 20px;
}

header h1, header h2 {
    margin: 0;
}

h1 {
    color: #0073e6;
    margin: 0;
}

p {
    font-size: 1em;
    margin: 10px 0;
    color: #555;
}

img {
    display: block;
    width: 300px;
    height: 200px;
    object-fit: cover;
    border-radius: 5px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    margin: 20px 0;
}

a {
    display: inline-block;
    color: #0073e6;
    text-decoration: none;
    font-weight: bold;
    padding: 10px 15px;
    border: 1px solid #0073e6;
    border-radius: 5px;
    transition: all 0.3s ease;
}

a:hover {
    background-color: #0073e6;
    color: white;
}

````
Y este es el que hemos utilizado para la información de cada Jugador.

---

## Plantilla Utilizadas
Esta es la plantilla utilizada para el inicio de la web
````html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Lista de Jugadores</title>
    <link rel="stylesheet" href="../CSS/style.css">



</head>
<body>
<header>
    <h1>Jugadores de Tenis</h1>
    <p>Explora los perfiles de los jugadores más destacados.</p>
</header>
<div>
    <h2>Lista de Jugadores</h2>
    <ul>
        <li th:each="jugador : ${Jugadores}">
            <span th:text="${jugador.nombre}"></span>
            <a th:href="${jugador.nombre} + '.html'" th:text="${jugador.nombre}"></a>
        </li>
    </ul>
</div>
</body>
</html>

````
Y este html para la informacion de los tenistas
```` html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../CSS/style1.css">


    <header>
        <h1 th:text="${nom}"></h1>
        <h2 th:text="${descripcion}"></h2>
    </header>
</head>
<body>
<h1 th:text="${nombre}"></h1>
<p><strong>Pais:</strong> <span th:text="${pais}"></span></p>
<p><strong>Titulos de Grand Slam:</strong> <span th:text="${titulos_grand_slam}"></span></p>
<p><strong>Anos de Actividad:</strong> <span th:text="${anos_actividad}"></span></p>
<img th:src="${Imagen}" alt="Imagen" />

<p><a th:href="index.html">Volver a la lista</a></p>
</body>
</html>

````
---
## Schema Utilizado
````json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Lista de Jugadores de Tenis",
  "type": "object",
  "properties": {

    "jugadores": {
      "type": "array",
      "description": "Lista de los mejores jugadores de tenis",
      "items": {
        "type": "object",
        "properties": {
          "nombre": {
            "type": "string",
            "description": "Nombre del jugador"
          },
          "pais": {
            "type": "string",
            "description": "País de origen del jugador"
          },
          "titulos_grand_slam": {
            "type": "integer",
            "description": "Número de títulos de Grand Slam ganados por el jugador",
            "minimum": 0
          },
          "anos_actividad": {
            "type": "string",
            "description": "Periodo de actividad del jugador, usando el formato 'YYYY-YYYY' o 'YYYY-presente'",
            "pattern": "^(\\d{4}-(\\d{4}|presente))$"
          }
        },
        "required": ["nombre", "pais", "titulos_grand_slam", "anos_actividad"],
        "additionalProperties": false
      }
    }
  },
  "required": ["jugadores"],
  "additionalProperties": false
}

````
Gracias a este tozito de codigo, nos crea automaticamente el rss.
````html
 generarRSS("src/main/resources/rss.xml", listaJugadores.getJugadores());
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

            writer.write("</channel>\n");
        writer.write("</rss>\n");
    } catch (IOException e) {
    System.err.println("Error al escribir el archivo RSS: " + e.getMessage());
    throw new RuntimeException("Error al escribir el archivo RSS", e);
    }
            }
           }
           }
           
````
A continuación tenemos las dependencias utilizadas en el proyecto.

`````html
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.AdrianPeiro</groupId>
    <artifactId>ProyectoJaume</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>23</maven.compiler.source>
        <maven.compiler.target>23</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.18.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.18.0</version>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
            <version>3.0.15.RELEASE</version>
        </dependency>
            <dependency>
                <groupId>org.thymeleaf</groupId>
                <artifactId>thymeleaf</artifactId>
                <version>3.1.2.RELEASE</version>
            </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.11</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>5.3.20</version>
        </dependency>
    </dependencies>




</project>
``````
---
## Principales Errores

## Problema 1: Estilos CSS no aplicados correctamente
- **Causa**: No me cogía bien el CSS.

- **Solución**: Coloqué el CSS en una carpeta llamada `CSS` y en los archivos HTML especifiqué correctamente la ruta al archivo de los estilos.

---

## Problema 2: Imágenes no mostradas
- **Causa**: La ruta de la imagen se tomó de un sitio web que redirigía a otra página en lugar de mostrar las imágenes.

- **Solución**: Revisé las imágenes y me aseguré que las URLs funcionaran correctamente antes de utilizarlas.

---

## Problema 3: Generación dinámica incompleta
- **Causa**: El archivo JSON contenía nombres separados, lo que causaba problemas al generar los HTML, mostrando errores de inexistencia.

- **Solución**: Unifiqué los nombres en el JSON en una sola cadena. Aunque no es la solución más elegante, fue la única manera de hacer que funcionara correctamente.


