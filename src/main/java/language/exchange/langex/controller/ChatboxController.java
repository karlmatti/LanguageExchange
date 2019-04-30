package language.exchange.langex.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String last = "";
        File f = new File(chatID);

        if (f.exists() && !f.isDirectory()) {
            File file = new File(chatID);

            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((resultString = br.readLine()) != null) {
                last = resultString;
            }

            Pattern pattern = Pattern.compile("(?<=\"content\": \").+(?=\"},)");
            Matcher mathcher = pattern.matcher(last);
            System.out.println(mathcher.find());
            last = mathcher.group(0);

        }

        return last;

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

    @PostMapping("/chatboxFixMessage/{data}")
    private String chatBoxFix(@PathVariable("data") String data) throws IOException, JSONException {
        JSONObject obj = new JSONObject(data);

        int requiredLine = obj.getInt("messageNumber") + 1;
        String newMessage = obj.getString("newMessage");
        int chatNumber = obj.getInt("chatNumber");

        String originalFileContent = "";
        BufferedReader reader = null;
        BufferedWriter writer = null;


        try {

            reader = new BufferedReader(new FileReader("1.txt"));
            String currentReadingLine = reader.readLine();

            int currentLineNumber = 0;

            while (currentReadingLine != null) {
                if (requiredLine == currentLineNumber) {
                    JSONObject obj2 = new JSONObject(currentReadingLine.substring(0, currentReadingLine.length() - 1));
                    String thisUserID = obj2.getString("owner");
                    String lastText = obj2.getString("content");

                    currentReadingLine = "{ \"owner\": " + "\"" + thisUserID + "\"" + ",\"content\": " + "\"" + lastText + "\" , \"newContent\": \"" + newMessage + "\"},";
                }
                originalFileContent += currentReadingLine + System.lineSeparator();
                currentReadingLine = reader.readLine();

                System.out.println("current reading line is " + currentReadingLine);
                System.out.println("original file content " + originalFileContent);
                currentLineNumber++;

            }

            System.out.println("content");
            System.out.println(originalFileContent);
            writer = new BufferedWriter(new FileWriter("1.txt", false));
            writer.write(originalFileContent);
            writer.close();


        } catch (IOException e) {
            try {
                if (reader != null) {
                    reader.close();
                }

                if (writer != null) {
                    writer.close();
                }

            } catch (IOException s) {
                //handle exception
            }
        }

        return null;
    }
}
