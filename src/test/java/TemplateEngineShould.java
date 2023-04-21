import org.example.InterpolatedText;
import org.example.InterpolationResult;
import org.example.TemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/*
 - No variables to interpolate - "text" = "text"
 - OK A variable to interpolate - "Hola amigo me llamo ${name}, {{name: "La fani"}}
 - OK Several variables to interpolate - "Hola ${user} me llamo ${name} espero que estes bien"
 - Corner cases:
    - "Hola ${expression} me llamo ${name}"  , {name: "Paco"} -> "Hola ${expression} me llamo Paco" + WARN UNMATCHED PLACEHOLDER
    - "Hola me llamo ${name}"  , {expression: "lola", name: "Paco"} -> "Hola me llamo Paco" + WARN KEY NOT USED
    - (is same case as above) "Hola ${{expression} me llamo ${name}"  , {expression: "lola", name: "Paco"} -> "Hola ${{expression} me llamo Paco" + WARN KEY NOT USED
 */
public class TemplateEngineShould {
    @Test
    void not_modify_input_if_does_not_contain_variables() { //TODO: Check name
        String input = "Text";
        var textWithoutInterpolation = TemplateEngine.interpolate(input, Map.of()).getText();
        assertThat(textWithoutInterpolation).isEqualTo(new InterpolatedText(input));
    }

    @Test
    void replace_variables_with_values_in_the_dictionary() {
        String input = "Hola ${user} me llamo ${name}";
        var dictionary = Map.of("user", "Hosecrypto", "name", "la Fani");
        var interpolatedText = TemplateEngine.interpolate(input, dictionary).getText();
        assertThat(interpolatedText).isEqualTo(new InterpolatedText("Hola Hosecrypto me llamo la Fani"));
    }

    @Test
    void interpolate_whenever_posible_and_warn_about_missing_placeholder_value() {
        String input = "Hola ${user} me llamo ${name}";
        var dictionaryWithMissingPlaceholder = Map.of("user", "Hosecrypto");
        var interpolationResult = TemplateEngine.interpolate(input, dictionaryWithMissingPlaceholder);
        assertThat(interpolationResult).isEqualTo(InterpolationResult.from(List.of("Missing placeholder value: ${name}"), "Hola Hosecrypto me llamo ${name}"));
    }
}
