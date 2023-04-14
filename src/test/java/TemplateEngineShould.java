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
    void not_modify_input_if_does_not_contain_variables(){
        String input = "Text";
        var textWithoutInterpolation = TemplateEngine.interpolate(input, Map.of());
        assertThat(textWithoutInterpolation).isEqualTo(input);
    }
}
