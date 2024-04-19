package org.example;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class WebsiteXmlParser {
    public static void main(String[] args) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            WebsiteList websiteList = xmlMapper.readValue(new File("src/main/resources/website.xml"), WebsiteList.class);

            List<Website> websites = websiteList.getWebsites();

            // Filter websites created after September 1
            LocalDateTime filterDate = LocalDateTime.of(2019, 9, 1, 0, 0);
            List<Website> filteredWebsites = websites.stream()
                    .filter(website -> {
                        LocalDateTime createdDate = LocalDateTime.parse(website.getCreatedDate(), DateTimeFormatter.ofPattern("M/d/yyyy h:m:s a"));
                        return createdDate.isAfter(filterDate);
                    })
                    .collect(Collectors.toList());

            // Print website information
            for (int i = 0; i < filteredWebsites.size(); i++) {
                Website website = filteredWebsites.get(i);
                System.out.println("Website " + (i + 1) + ":");
                System.out.println("URL: " + website.getURL());
                System.out.println("Title: " + website.getTitle());
                System.out.println("Description: " + website.getDescription());
                System.out.println("Created Date: " + website.getCreatedDate());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


