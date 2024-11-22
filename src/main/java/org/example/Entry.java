package org.example;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "entry", namespace = "http://www.w3.org/2005/Atom")
public class Entry {

    @XmlElement(name = "title", namespace = "http://www.w3.org/2005/Atom")
    private String title;

    @XmlElement(name = "link", namespace = "http://www.w3.org/2005/Atom")
    private Link link;

    @XmlElement(name = "author", namespace = "http://www.w3.org/2005/Atom")
    private Author author;

    @XmlElement(name = "published", namespace = "http://www.w3.org/2005/Atom")
    private String date;

    public Entry() {
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link != null ? link.getHref() : "No disponible";
    }

    public String getAuthor() {
        return author != null ? author.getName() : "No disponible";
    }

    public String getDate() {
        return date;
    }

    // Clase interna para capturar el nombre del autor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Author {
        @XmlElement(name = "name", namespace = "http://www.w3.org/2005/Atom")
        private String name;

        public String getName() {
            return name;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Link {

        @XmlAttribute(name = "href")
        private String href;

        public String getHref() {
            return href;
        }
    }
}