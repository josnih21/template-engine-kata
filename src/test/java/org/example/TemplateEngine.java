package org.example;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
indexOf($)
 */
public class TemplateEngine {
    public static String interpolate(String input, Map<String, String> variables) {

        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z0-9]+\\}");
        Matcher matcher = pattern.matcher(input);
        var interpolatedText = input;
        while (matcher.find()) {
            String match = matcher.group();
            String key = match.replace("${", "").replace("}", "");
            interpolatedText = input.replace(match, variables.get(key));
        }
        return interpolatedText;
    }
}
