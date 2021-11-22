package petAPI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewTextAttribute {

    protected String NEW_ID ="{\n" +
            "  \"id\": 1,\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"automation_test\"\n" +
            "  },\n" +
            "  \"name\": \"doggie\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";


    private LocalDateTime myDateObj = LocalDateTime.now();
    private DateTimeFormatter standartFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private DateTimeFormatter myFormatObj_ = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");

    public String standartDateFormat =myDateObj.format(standartFormatObj);
    public String myDateFormat_ =myDateObj.format(myFormatObj_);
}
