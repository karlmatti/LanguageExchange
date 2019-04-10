package language.exchange.langex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ChatboxController {

    @PostMapping("/chatbox/{data}")
    private String getChat(@PathVariable("data") String data) throws IOException {

        String arrayApart[];
        arrayApart = data.split(" ");

        String userID = arrayApart[0];
        String friendID = arrayApart[1];
        String chatID = arrayApart[2];
        String friendName = arrayApart[3];

        if (chatID.equals("undefined"))
            return null;

        Writer writer = null;
        String resultString = "";
        File f = new File(chatID);

        if (f.exists() && !f.isDirectory()) {
            File file = new File(chatID);

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                System.out.println(st);
                resultString = (new StringBuilder()).append(resultString)
                        .append(st.replace(userID, "self").replace(friendID, "other") + "\n").toString();
            }
            if (resultString.length() > 3)
                resultString = resultString.substring(0, resultString.length() - 2);

        } else {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(chatID), "utf-8"));
            } catch (IOException ex) {
                // Report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {/*ignore*/}
            }
        }

        return "{\"name\" : \"" + friendName + "\", \"detail\": [ \n" + resultString + "]}";

    }

    @PostMapping("/chatboxLastMessage/{data}")
    private String getLastMessage(@PathVariable("data") String data) throws IOException {


        String chatID = data;

        if (chatID.equals("undefined"))
            return null;

        Writer writer = null;
        String resultString = "";
        File f = new File(chatID);

        if (f.exists() && !f.isDirectory()) {
            File file = new File(chatID);

            BufferedReader br = new BufferedReader(new FileReader(file));

            resultString = br.readLine();
            System.out.println(resultString);
        }

        return resultString;

    }
    @PostMapping("/chatboxSend/{data}")
    private String sendToChat(@PathVariable("data") String data) throws IOException {
        String explode[] = data.split("-");

        String fileName = explode[0];
        String newData = explode[1];

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.newLine();
        writer.append(newData);
        writer.close();

        return null;
    }
}
