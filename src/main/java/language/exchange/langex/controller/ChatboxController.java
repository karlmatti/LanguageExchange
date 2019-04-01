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
    private Map<String, String> getFriendByID(@PathVariable("data") String data) throws IOException {


        String arrayApart[];
        arrayApart = data.split(" ");

        String userID= arrayApart[0];
        String friendID = arrayApart[1];
        String chatID = arrayApart[2];
        String friendName = arrayApart[3];

        System.out.println(userID);
        System.out.println(friendID);
        System.out.println(chatID);
        System.out.println(friendName);

        Writer writer = null;

        File f = new File(chatID);
        if (f.exists() && !f.isDirectory()) {
            File file = new File(chatID);

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null)
                System.out.println(st);
        } else {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(chatID), "utf-8"));
            } catch (IOException ex) {
                // Report
            } finally {
                try {writer.close();} catch (Exception ex) {/*ignore*/}
            }
        }






        return null;

    }

}
