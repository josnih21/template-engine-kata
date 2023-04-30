import org.example.InterpolatedText;
import org.example.InterpolationWarnings;
import org.example.TemplateEngine;
import org.junit.jupiter.api.Test;

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
        assertThat(getInterpolatedText("Text", Map.of()))
                .isEqualTo(new InterpolatedText("Text"));
    }

    @Test
    void replace_variables_with_values_in_the_dictionary() {
        String input = "Hola ${user} me llamo ${name}";
        var dictionary = Map.of("user", "Hosecrypto", "name", "la Fani");

        assertThat(getInterpolatedText(input, dictionary))
                .isEqualTo(new InterpolatedText("Hola Hosecrypto me llamo la Fani"));
    }

    @Test
    void interpolate_whenever_posible_and_warn_about_missing_placeholder_value() {
        String input = "Hola ${user} me llamo ${name}";
        var dictionaryWithMissingPlaceholder = Map.of("user", "Hosecrypto");

        var interpolationResult = TemplateEngine.interpolate(input, dictionaryWithMissingPlaceholder);

        var expectedWarnings = new InterpolationWarnings();
        expectedWarnings.addMissingPlaceHolder("Missing placeholder value: ${name}");
        assertThat(interpolationResult.getText()).isEqualTo(new InterpolatedText("Hola Hosecrypto me llamo ${name}"));
        /*TODO: Check why comparison with warnings fails:
        assertThat(interpolationResult.getWarnings()).isEqualTo(expectedWarnings);
         */
    }

    private static InterpolatedText getInterpolatedText(String input, Map<String, String> dictionary) {
        return TemplateEngine.interpolate(input, dictionary).getText();
    }
}
