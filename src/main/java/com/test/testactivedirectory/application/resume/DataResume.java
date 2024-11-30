package com.test.testactivedirectory.application.resume;
import java.util.*;

public class DataResume {

    // Método estático que retorna la categoría con sus ítems
    public static Map<String, Object> getTipologiaData() {
        // Crear la lista de ítems que pertenecen a la categoría "Tipología"
        List<Map<String, String>> items = new ArrayList<>();

        // Agregar cada ítem como un mapa a la lista
        items.add(createItem("Gestión del Conocimiento e Innovación", "Buenas prácticas relacionadas con la sistematización, transferencia de conocimiento y metodologías innovadoras."));
        items.add(createItem("Servicio al Ciudadano", "Mejora en la atención y servicios para la ciudadanía."));
        items.add(createItem("Planeación Estratégica", "Acciones relacionadas con el diseño, implementación y seguimiento de planes estratégicos."));
        items.add(createItem("Gestión Financiera y Presupuestal", "Prácticas de optimización financiera, control de recursos y planeación presupuestal."));
        items.add(createItem("Gestión del Talento Humano", "Desarrollo de competencias, bienestar laboral y políticas de igualdad de género."));
        items.add(createItem("Gestión Ambiental y Sostenibilidad", "Prácticas relacionadas con la sostenibilidad ambiental y el cumplimiento normativo."));
        items.add(createItem("Gestión de la Transparencia y Rendición de Cuentas", "Mecanismos de promoción de la transparencia y participación ciudadana."));
        items.add(createItem("Innovación Tecnológica y Transformación Digital", "Uso de tecnología para la mejora de procesos y servicios públicos."));
        items.add(createItem("Gestión del Riesgo y Continuidad Operativa", "Planeación para prevenir, mitigar y responder a riesgos."));
        items.add(createItem("Gestión de Proyectos", "Prácticas relacionadas con la metodología y ejecución de proyectos."));
        items.add(createItem("Inclusión y Participación Social", "Prácticas enfocadas en la inclusión de grupos vulnerables y participación comunitaria."));
        items.add(createItem("Eficiencia Operativa y Simplificación de Procesos", "Mejoras en la gestión interna y la reducción de trámites."));

        // Crear el mapa principal con la clave "Categoria"
        Map<String, Object> datos = new HashMap<>();
        datos.put("Categoria", "Tipología");
        datos.put("Items", items);

        return datos;
    }

    // Método auxiliar para crear un ítem
    private static Map<String, String> createItem(String nombre, String descripcion) {
        Map<String, String> item = new HashMap<>();
        item.put("nombre", nombre);
        item.put("descripcion", descripcion);
        return item;
    }

    // Método que imprime los datos de la tipología
    public static void listarTipologia() {
        Map<String, Object> datos = getTipologiaData();
        List<Map<String, String>> listaItems = (List<Map<String, String>>) datos.get("Items");

        // Recorrer e imprimir cada ítem
        for (Map<String, String> item : listaItems) {
            System.out.println("Nombre: " + item.get("nombre"));
            System.out.println("Descripción: " + item.get("descripcion"));
        }
    }
}
