package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.StringWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@XmlRootElement(name = "entries")
public class ExportXML {

    private List<Entry> entries;

    public ExportXML() {
    }

    public ExportXML(List<Entry> entries) {
        this.entries = entries;
    }

    @XmlElement(name = "entry")
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public void exportToFile(String filename) {
        try {
            // Configurar JAXB para convertir el objeto en XML
            JAXBContext context = JAXBContext.newInstance(ExportXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Crear un StringWriter para almacenar el XML temporalmente
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);

            // Obtener el contenido XML como una cadena
            String xmlContent = stringWriter.toString();

            // Escribir el contenido XML en el archivo usando java.nio
            Path path = Paths.get(filename);
            Files.writeString(path, xmlContent);
            System.out.println("Archivo XML generado con éxito: " + filename);

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}