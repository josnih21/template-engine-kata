package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InterpolationWarnings {
    private List<String> warnings = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterpolationWarnings that = (InterpolationWarnings) o;

        if (warnings.size() != that.warnings.size()) {
            return false;
        } else {
            // Iterate through each element of the lists and compare them
            int i = 0;
            for (String element1 : warnings) {
                String element2 = that.warnings.get(i++);
                if (!element1.equals(element2)) {
                    // If any two elements are not equal, the lists are not equal
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(warnings);
    }

    public void addMissingPlaceHolder(String placeholder) {
        warnings.add("Missing placeholder value: " + placeholder);
    }
}
