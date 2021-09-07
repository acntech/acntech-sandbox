package no.acntech.sandbox.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Document(indexName = "sandbox.news.headlines")
public class News {

    @Id
    private String id;
    @Field(name = "headline", type = FieldType.Text)
    private String headline;
    @Field(name = "short_description", type = FieldType.Text)
    private String shortDescription;
    @Field(name = "category", type = FieldType.Text)
    private String category;
    @Field(name = "authors", type = FieldType.Text)
    private String authors;
    @Field(name = "date", type = FieldType.Date)
    private LocalDate date;
    @Field(name = "@timestamp", type = FieldType.Date)
    private ZonedDateTime timestamp;
    @Field(name = "link", type = FieldType.Text)
    private String link;

    public String getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthors() {
        return authors;
    }

    public LocalDate getDate() {
        return date;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getLink() {
        return link;
    }
}
