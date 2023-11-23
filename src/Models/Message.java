package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Message {
    private final User sender;
    private User receiver;
    private final String content;
    private final String date;

    public Message(User sender, User receiver, String content, String date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }
    @Override
    public String toString () {
        return date + "\n" + sender.asMessageString() + "Content: " + content + "\n";
    }

    //https://stackoverflow.com/questions/5713558/detect-and-extract-url-from-a-string
    public void extractUrls()
    {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex =   "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(content);

        while (urlMatcher.find())
        {
            containedUrls.add(content.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        if (!containedUrls.isEmpty()) {
            System.out.println("There are some URLs in this message! Be careful opening links from the internet!");
            for (String url : containedUrls) {
                System.out.println(url);
            }
        }
    }
}