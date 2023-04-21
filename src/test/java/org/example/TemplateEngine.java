package org.example;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
indexOf($)
 */
public class TemplateEngine {
    public static InterpolationResult interpolate(String text, Map<String, String> variables) {
        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z0-9]+\\}");
        Matcher matcher = pattern.matcher(text);
        var interpolatedText = text;
        while (matcher.find()) {
            String placeholder = matcher.group();
            String key = placeholder.replace("${", "").replace("}", "");
            interpolatedText = interpolatedText.replace(placeholder, variables.get(key));
        }
        return InterpolationResult.from(List.of(), interpolatedText);
    }
}
