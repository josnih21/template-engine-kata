package org.example;

import java.util.Objects;

public class InterpolationResult {

    private final InterpolationWarnings warnings;
    private final InterpolatedText text;

    private InterpolationResult(InterpolationWarnings warnings, InterpolatedText text) {
        this.warnings = warnings;
        this.text = text;
    }

    public static InterpolationResult from(InterpolationWarnings warnings, InterpolatedText interpolatedText) {
        return new InterpolationResult(warnings, interpolatedText);
    }

    public InterpolatedText getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterpolationResult that = (InterpolationResult) o;
        return Objects.equals(warnings, that.warnings) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warnings, text);
    }
}
