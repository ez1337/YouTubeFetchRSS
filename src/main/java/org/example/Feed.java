package org.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "feed", namespace = "http://www.w3.org/2005/Atom")
public class Feed {

    @XmlElement(name = "entry", namespace = "http://www.w3.org/2005/Atom")
    private List<Entry> entries = new ArrayList<>();  // Anotamos el campo directamente

    public Feed() {
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
