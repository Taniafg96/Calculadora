package calculadora;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
/**
 *
 * @author Usuario
 */
public class LogicaCalculadora {

    private String text;
    List<String> arrNum = new ArrayList<>();

    public LogicaCalculadora() {
    }

    public void setText(String text) {
        this.text = text;
    }

    //Esta función coge el array y al ultimo numero lo pone entre parentesis y le cambia el signo
    public String masMenos(String str) {
        String[] arrMasMenos = this.text.split("");
        String numbers = "";

        for (String i : arrMasMenos) {
            if (i.equals("+") || i.equals("-") || i.equals("/") || i.equals("*") || i.equals("(") || i.equals(")")) {
                if (!numbers.equals("")) {
                    this.arrNum.add(numbers);
                    numbers = "";
                }
                this.arrNum.add(i);
            } else {
                numbers += i;
            }
        }
        this.arrNum.add(numbers);
        String lastNumber = this.arrNum.get(this.arrNum.size() - 1);
        this.arrNum.remove(this.arrNum.size() - 1);
        this.arrNum.add("(-" + lastNumber + ")");

        String result = "";
        result = this.arrNum.stream().map((i) -> i).reduce(result, String::concat);

        return result;
    }

    //ScriptEngine es una clase que me procesa el string y lo analiza como 
    //una ecuación en las últimas versiones del jdk y netbeans ya esta deprecado 
    //y hay que usar una librearia equivalente
    //Si la ecuación no da ningun error me devuelve el resultado sino devuelve Error
    public String resultado(String text) {
        ScriptEngineManager script = new ScriptEngineManager();
        ScriptEngine eng = script.getEngineByName("JavaScript");
        try {
            return String.valueOf(eng.eval(text));
        } catch (ScriptException ex) {
            return "Error";
        }
    } 

}
