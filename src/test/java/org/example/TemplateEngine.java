package org.example;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    public static InterpolationResult interpolate(String text, Map<String, String> variables) {
        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z0-9]+\\}");
        Matcher matcher = pattern.matcher(text);
        var interpolatedText = text;
        var warnings = InterpolationWarnings.empty();
        while (matcher.find()) {
            String placeholder = matcher.group();
            String key = placeholder.replace("${", "").replace("}", "");
            String replacement = variables.get(key);
            if (replacement != null) {
                interpolatedText = interpolatedText.replace(placeholder, replacement);
            } else {
                warnings.addMissingPlaceHolder(placeholder);
            }
        }
        return InterpolationResult.from(warnings, new InterpolatedText(interpolatedText));
    }
}
