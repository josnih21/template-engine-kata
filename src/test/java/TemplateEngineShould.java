import org.example.TemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/*
 - No variables to interpolate - "text" = "text"
 - A variable to interpolate - "Hola amigo me llamo ${name}, {{name: "La fani"}}
 */
public class TemplateEngineShould {
    @Test
    void not_modify_input_if_does_not_contain_variables(){ //TODO: Check name
        String input = "Text";
        var textWithoutInterpolation = TemplateEngine.interpolate(input, Map.of());
        assertThat(textWithoutInterpolation).isEqualTo(input);
    }

    @Test
    void replace_variables_with_values_in_the_dictionary(){
        String input = "Hola amigo me llamo ${name}";
        var dictionary = Map.of("name", "la Fani");
        var interpolatedText = TemplateEngine.interpolate(input, dictionary);
        assertThat(interpolatedText).isEqualTo("Hola amigo me llamo la Fani");
    }
}
