package org.example;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Main {

    public static void main(String[] args) {

        /*Hace una solicitud HTTP a la URL. Crea una instancia de HttpClient para crear un cliente
         * y HttpRequest para construir la solicitud. La respuesta se guarda en el String xmlContent.
         * El metodo parseXMLWithJAXB procesa el XML para deserializar el contenido y convertirlo en objetos.*/
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(chooseChannel()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            String xmlContent = response.body();
            parseXMLWithJAXB(xmlContent);

        } catch (IOException | InterruptedException | JAXBException e) {
            e.printStackTrace();
        }
    }

    /*Este metodo crea un contexto y un objeto unmarshaller para convertir el XML en un objeto Feed.
     * Si existen entradas en Feed, itera sobre cada entry y muestra por pantalla los detalles de cada entrada.
     * Al final, crea una instancia de la clase ExportXML para crear un fichero XML con el contenido de las entradas.*/
    private static void parseXMLWithJAXB(String xmlContent) throws JAXBException {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Feed.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


            StringReader reader = new StringReader(xmlContent);
            Feed feed = (Feed) unmarshaller.unmarshal(reader);


            if (feed.getEntries() != null && !feed.getEntries().isEmpty()) {
                int n = 0;
                for (Entry entry : feed.getEntries()) {
                    System.out.println("Title: " + entry.getTitle());
                    System.out.println("Author: " + entry.getAuthor());
                    System.out.println("Date: " + entry.getDate());
                    if (entry.getLink() != null) {
                        System.out.println("Link: " + entry.getLink());
                    } else {
                        System.out.println("Link: No disponible");
                    }

                    System.out.println();
                    n++;
                }
                System.out.println("Total: "+n);


                ExportXML export = new ExportXML(feed.getEntries());
                export.exportToFile("./videos/25-11-2024-list.xml");
            } else {
                System.out.println("No hay entradas en el feed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String chooseChannel(){
        String URL = "";
        Scanner sn = new Scanner(System.in);
        System.out.println("Ingresa el número del canal que quieres guardar: \n 1.- Fireship \n 2.- TodoCode \n 3.- MoureDev");
        int op = sn.nextInt();
        switch(op){
            case 1: //Fireship
                URL = "https://www.youtube.com/feeds/videos.xml?channel_id=UCsBjURrPoezykLs9EqgamOA";
                break;
            case 2: //TodoCode
                URL = "https://www.youtube.com/feeds/videos.xml?channel_id=UCz0EXCSvMwYKruljsZjCOzw";
                break;
            case 3: //MoureDev
                URL = "https://www.youtube.com/feeds/videos.xml?channel_id=UCxPD7bsocoAMq8Dj18kmGyQ";
                break;
        }
        return URL;
    }
}