package org.example;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    public static InterpolationResult interpolate(String text, Map<String, String> variables) {
        Matcher matcher = getPlaceholderMatcher(text);
        var warnings = new InterpolationWarnings();
        while (matcher.find()) {
            String placeholder = matcher.group();
            String key = getKeyFromPlaceholder(placeholder);
            String value = variables.get(key);
            if (value != null) {
                text = text.replace(placeholder, value);
            } else {
                warnings.addMissingPlaceHolder(placeholder);
            }
        }
        return InterpolationResult.from(warnings, new InterpolatedText(text));
    }

    private static String getKeyFromPlaceholder(String placeholder) {
        return placeholder.replace("${", "").replace("}", "");
    }

    private static Matcher getPlaceholderMatcher(String text) {
        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z0-9]+\\}");
        return pattern.matcher(text);
    }
}
