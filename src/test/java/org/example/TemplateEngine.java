package org.example;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    public static InterpolationResult interpolate(String text, Map<String, String> variables) {
        Matcher matcher = getPlaceholderMatcher(text);
        var interpolatedText = text;
        var warnings = new InterpolationWarnings();
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

    private static Matcher getPlaceholderMatcher(String text) {
        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z0-9]+\\}");
        return pattern.matcher(text);
    }
}
