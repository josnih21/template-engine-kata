package org.example;

import java.util.List;

public class InterpolationResult {

    private final InterpolationWarnings warnings;
    private final InterpolatedText text;

    private InterpolationResult(InterpolationWarnings warnings, InterpolatedText text) {
        this.warnings = warnings;
        this.text = text;
    }

    public static InterpolationResult from(List<String> warnings, String text) {
        return new InterpolationResult(new InterpolationWarnings(warnings), new InterpolatedText(text));
    }

    public InterpolatedText getText() {
        return text;
    }
}
