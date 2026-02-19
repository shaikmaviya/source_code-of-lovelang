import java.nio.file.*;
import java.util.*;

public class Main {

    static Map<String, Object> variables = new HashMap<>();
    static Map<String, UserFunction> functions = new HashMap<>();
    static List<String> allLines = new ArrayList<>();
    static int currentLine = 0;

    static class UserFunction {
        String name;
        List<String> parameters;
        List<String> bodyLines;
        
        UserFunction(String name, List<String> parameters, List<String> bodyLines) {
            this.name = name;
            this.parameters = parameters;
            this.bodyLines = bodyLines;
        }
    }

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("Usage: java Main filename.love");
            return;
        }

        String fileName = args[0];

        if (!fileName.endsWith(".love")) {
            System.out.println("Error: File must have .love extension");
            return;
        }

        String code = Files.readString(Path.of(fileName));
        allLines = Arrays.asList(code.split("\n"));

        while (currentLine < allLines.size()) {
            execute(allLines.get(currentLine).trim());
            currentLine++;
        }
    }

    static void execute(String line) {
        if (line == null || line.isBlank()) {
            return;
        }

        String command = line.split("[\\s(]", 2)[0];

        switch (command) {
            case "heart" -> handleVariable(line);
            case "whisper" -> handlePrint(line);
            case "promise" -> defineFunction(line);
            case "iflove" -> ifCondition(line);
            case "elseheart" -> elseHeartCondition(line);
            case "elselove" -> elseLoveCondition(line);
            case "forever" -> foreverLoop(line);
            case "secret" -> Secret(line);
            case "goodbye" -> goodbye(line);
            case "hug" -> handlePrint(line);
            default -> callUserFunction(line);
        }
    }

    @SuppressWarnings("ConvertToStringSwitch")
    static void handleVariable(String line) {

        line = line.replace("heart", "").trim();
        String[] parts = line.split("=");

        String varName = parts[0].trim();
        String value = parts[1].trim();

        // boolean values
        if (value.equals("forever")) {
            variables.put(varName, true);
        } else if (value.equals("breakup")) {
            variables.put(varName, false);
        } else {
            value = value.replace("\"", "");
            variables.put(varName, value);
        }
    }

    static void handlePrint(String line) {
        if (line.startsWith("whisper(") && line.endsWith(")")) {
            line = line.substring(8, line.length() - 1).trim();
        } else {
            System.out.println("Error: Invalid syntax for whisper");
        }


            // Check if it's a function call like hug(...)
    if (line.startsWith("hug(") && line.endsWith(")")) {
        line = line.substring(4, line.length() - 1).trim();
        String[] params = line.split(",");
        
        if (params.length == 2) {
            String result = hug(params[0].trim(), params[1].trim());
            System.out.println(result);
        } else {
            System.out.println("Error: hug requires 2 parameters");
        }
        return;
    }
        String[] parts = line.split("\\+");

        String result = "";

        for (String part : parts) {
            part = part.trim();

            if (part.startsWith("\"")) {
                result += part.replace("\"", "");
            } else {
                result += String.valueOf(variables.get(part));
            }
        }

        System.out.println(result);
    }

    static void ifCondition(String line) {
        // Extract condition from iflove(condition) {
        if (!line.startsWith("iflove(")) {
            System.out.println("Error: Invalid syntax for if statement");
            return;
        }

        int condEnd = line.indexOf(")");
        if (condEnd == -1) {
            System.out.println("Error: Missing closing parenthesis in iflove");
            return;
        }

        String condition = line.substring(7, condEnd).trim();
        
        if (!line.substring(condEnd).trim().startsWith("{")) {
            System.out.println("Error: Missing opening brace in iflove statement");
            return;
        }

        boolean conditionMet = evaluateCondition(condition);

        if (conditionMet) {
            // Execute the block inside if
            currentLine++;
            executeBlock();
        } else {
            // Skip to else or closing brace
            currentLine++;
            skipBlock();
            
            // Check for elseheart or elselove after if block
            if (currentLine < allLines.size()) {
                String nextLine = allLines.get(currentLine).trim();
                if (nextLine.startsWith("elseheart(")) {
                    execute(nextLine);
                } else if (nextLine.startsWith("elselove(")) {
                    execute(nextLine);
                }
            }
        }
    }

    static void elseHeartCondition(String line) {
        // elseheart is an else-if statement: elseheart(condition) {
        if (!line.startsWith("elseheart(")) {
            System.out.println("Error: Invalid syntax for else-heart statement");
            return;
        }

        int condEnd = line.indexOf(")");
        if (condEnd == -1) {
            System.out.println("Error: Missing closing parenthesis in elseheart");
            return;
        }

        String condition = line.substring(10, condEnd).trim();
        
        if (!line.substring(condEnd).trim().startsWith("{")) {
            System.out.println("Error: Missing opening brace in elseheart statement");
            return;
        }

        boolean conditionMet = evaluateCondition(condition);

        if (conditionMet) {
            // Execute the block inside else-heart
            currentLine++;
            executeBlock();
        } else {
            // Skip to next else or closing brace
            currentLine++;
            skipBlock();
            
            // Check for another elseheart or elselove
            if (currentLine < allLines.size()) {
                String nextLine = allLines.get(currentLine).trim();
                if (nextLine.startsWith("elseheart(")) {
                    execute(nextLine);
                } else if (nextLine.startsWith("elselove(")) {
                    execute(nextLine);
                }
            }
        }
    }

    static void elseLoveCondition(String line) {
        // elselove is an else statement (or else-if): elselove(condition) { or just else {
        if (!line.startsWith("elselove(") && !line.startsWith("elselove{")) {
            System.out.println("Error: Invalid syntax for else-love statement");
            return;
        }


        

        boolean hasCondition = line.startsWith("elselove(");
        boolean conditionMet = true;

        if (hasCondition) {
            int condEnd = line.indexOf(")");
            if (condEnd == -1) {
                System.out.println("Error: Missing closing parenthesis in elselove");
                return;
            }

            String condition = line.substring(9, condEnd).trim();
            
            if (!line.substring(condEnd).trim().startsWith("{")) {
                System.out.println("Error: Missing opening brace in elselove statement");
                return;
            }

            conditionMet = evaluateCondition(condition);
        } else if (!line.startsWith("elselove{")) {
            System.out.println("Error: Missing opening brace in elselove statement");
            return;
        }

        if (conditionMet) {
            // Execute the block inside else-love
            currentLine++;
            executeBlock();
        } else {
            // Skip to next else (if any)
            currentLine++;
            skipBlock();
        }
    }

    static void foreverLoop(String line) {
        // Extract loop variable and range: forever(i in 0..5) { or forever(count) {
        if (!line.startsWith("forever(")) {
            System.out.println("Error: Invalid syntax for forever loop");
            return;
        }

        int condEnd = line.indexOf(")");
        if (condEnd == -1) {
            System.out.println("Error: Missing closing parenthesis in forever");
            return;
        }

        String condition = line.substring(8, condEnd).trim();

        if (!line.substring(condEnd).trim().startsWith("{")) {
            System.out.println("Error: Missing opening brace in forever loop");
            return;
        }

        // Check if it's a range loop: i in 0..10
        if (condition.contains(" in ")) {
            String[] parts = condition.split(" in ");
            String varName = parts[0].trim();
            String[] range = parts[1].split("\\.\\.");
            
            if (range.length == 2) {
                int start = Integer.parseInt(range[0].trim());
                int end = Integer.parseInt(range[1].trim());
                
                for (int i = start; i <= end; i++) {
                    variables.put(varName, i);
                    currentLine++;
                    executeBlock();
                    if (currentLine >= allLines.size()) break;
                }
                currentLine--; // Compensate for the outer loop increment
            }
        } else {
            // Simple infinite loop (execute block forever until break)
            while (true) {
                currentLine++;
                if (currentLine >= allLines.size()) break;
                
                String blockLine = allLines.get(currentLine).trim();
                if (blockLine.equals("}")) {
                    break;
                }
                execute(blockLine);
            }
        }
    }

    static void executeBlock() {
        // Execute lines until closing brace
        while (currentLine < allLines.size()) {
            String blockLine = allLines.get(currentLine).trim();
            
            if (blockLine.equals("}")) {
                return;
            }
            
            if (!blockLine.isEmpty()) {
                execute(blockLine);
            }
            currentLine++;
        }
    }

    static void skipBlock() {
        // Skip lines until closing brace
        int braceCount = 1;
        currentLine++;
        while (currentLine < allLines.size() && braceCount > 0) {
            String line = allLines.get(currentLine).trim();
            if (line.endsWith("{")) braceCount++;
            if (line.equals("}")) braceCount--;
            if (braceCount > 0) currentLine++;
            else break;
        }
    }

    static boolean evaluateCondition(String condition) {
        // Handle comparisons: "name == value", "count > 5", "age < 18", etc.
        // Operators: ==, !=, >, <, >=, <=

        if (condition.contains("==")) {
            String[] parts = condition.split("==");
            String left = parts[0].trim();
            String right = parts[1].trim();
            Object leftVal = getValue(left);
            Object rightVal = getValue(right);
            return leftVal != null && leftVal.toString().equals(rightVal.toString());
        }
        
        if (condition.contains("!=")) {
            String[] parts = condition.split("!=");
            String left = parts[0].trim();
            String right = parts[1].trim();
            Object leftVal = getValue(left);
            Object rightVal = getValue(right);
            return leftVal != null && !leftVal.toString().equals(rightVal.toString());
        }

        if (condition.contains(">=")) {
            String[] parts = condition.split(">=");
            String left = parts[0].trim();
            String right = parts[1].trim();
            try {
                double leftVal = Double.parseDouble(getValueAsString(left));
                double rightVal = Double.parseDouble(getValueAsString(right));
                return leftVal >= rightVal;
            } catch (Exception e) {
                return false;
            }
        }

        if (condition.contains("<=")) {
            String[] parts = condition.split("<=");
            String left = parts[0].trim();
            String right = parts[1].trim();
            try {
                double leftVal = Double.parseDouble(getValueAsString(left));
                double rightVal = Double.parseDouble(getValueAsString(right));
                return leftVal <= rightVal;
            } catch (Exception e) {
                return false;
            }
        }

        if (condition.contains(">")) {
            String[] parts = condition.split(">");
            String left = parts[0].trim();
            String right = parts[1].trim();
            try {
                double leftVal = Double.parseDouble(getValueAsString(left));
                double rightVal = Double.parseDouble(getValueAsString(right));
                return leftVal > rightVal;
            } catch (Exception e) {
                return false;
            }
        }

        if (condition.contains("<")) {
            String[] parts = condition.split("<");
            String left = parts[0].trim();
            String right = parts[1].trim();
            try {
                double leftVal = Double.parseDouble(getValueAsString(left));
                double rightVal = Double.parseDouble(getValueAsString(right));
                return leftVal < rightVal;
            } catch (Exception e) {
                return false;
            }
        }

        // Simple boolean variable check
        Object val = variables.get(condition);
        return val != null && val.equals(true);
    }

    static Object getValue(String token) {
        token = token.trim();
        if (token.startsWith("\"") && token.endsWith("\"")) {
            return token.substring(1, token.length() - 1);
        }
        if (variables.containsKey(token)) {
            return variables.get(token);
        }
        try {
            return Double.parseDouble(token);
        } catch (Exception e) {
            return token;
        }
    }

    static String getValueAsString(String token) {
        Object val = getValue(token);
        return val != null ? val.toString() : "0";
    }

    static void defineFunction(String line) {
        // Format: promise functionName(param1, param2) {
        if (!line.startsWith("promise ") || !line.contains("(")) {
            System.out.println("Error: Invalid promise (function) syntax");
            return;
        }

        // Extract function name
        int openParen = line.indexOf("(");
        String funcName = line.substring(8, openParen).trim();
        
        int closeParen = line.indexOf(")");
        if (closeParen == -1) {
            System.out.println("Error: Missing closing parenthesis in function definition");
            return;
        }

        // Extract parameters
        String paramsStr = line.substring(openParen + 1, closeParen).trim();
        List<String> params = new ArrayList<>();
        if (!paramsStr.isEmpty()) {
            String[] paramArray = paramsStr.split(",");
            for (String param : paramArray) {
                params.add(param.trim());
            }
        }

        if (!line.substring(closeParen).trim().startsWith("{")) {
            System.out.println("Error: Missing opening brace in function definition");
            return;
        }

        // Extract function body
        List<String> bodyLines = new ArrayList<>();
        currentLine++;
        int braceCount = 1;
        
        while (currentLine < allLines.size() && braceCount > 0) {
            String bodyLine = allLines.get(currentLine).trim();
            
            if (bodyLine.contains("{")) braceCount += bodyLine.split("\\{", -1).length - 1;
            if (bodyLine.contains("}")) braceCount -= bodyLine.split("\\}", -1).length - 1;
            
            if (braceCount > 0) {
                bodyLines.add(bodyLine);
            }
            currentLine++;
        }
        currentLine--; // Compensate for outer loop increment

        // Store function
        functions.put(funcName, new UserFunction(funcName, params, bodyLines));
        System.out.println("Function " + funcName + " defined with " + params.size() + " parameters");
    }

    static void callUserFunction(String line) {
        // Check if this is a function call: functionName(arg1, arg2)
        if (!line.contains("(") || !line.contains(")")) {
            return;
        }

        int openParen = line.indexOf("(");
        String funcName = line.substring(0, openParen).trim();

        if (!functions.containsKey(funcName)) {
            return;
        }

        UserFunction func = functions.get(funcName);
        int closeParen = line.lastIndexOf(")");
        String argsStr = line.substring(openParen + 1, closeParen).trim();
        
        List<String> args = new ArrayList<>();
        if (!argsStr.isEmpty()) {
            String[] argArray = argsStr.split(",");
            for (String arg : argArray) {
                args.add(arg.trim());
            }
        }

        if (args.size() != func.parameters.size()) {
            System.out.println("Error: Function " + funcName + " expects " + func.parameters.size() + 
                               " arguments but got " + args.size());
            return;
        }

        // Create local scope for function by saving current variables
        Map<String, Object> savedVariables = new HashMap<>(variables);

        // Bind parameters to arguments
        for (int i = 0; i < func.parameters.size(); i++) {
            Object argValue = getValue(args.get(i));
            variables.put(func.parameters.get(i), argValue);
        }

        // Execute function body
        for (String bodyLine : func.bodyLines) {
            if (!bodyLine.isEmpty()) {
                execute(bodyLine);
            }
        }

        // Restore variables to previous scope
        variables = savedVariables;
    }

    static void Secret(String line){
        if(line.startsWith("secret")){
            return ;
        }
    }

    static void goodbye(String line){
    if (line.equals("goodbye")) {
    System.out.println("Love program ended 💔");
    System.exit(0);
    }

}

static String hug(String first, String second) {
    // Get values from variables if they exist, otherwise treat as strings
    String firstVal = variables.containsKey(first) ? 
        String.valueOf(variables.get(first)) : first.replace("\"", "");
    
    String secondVal = variables.containsKey(second) ? 
        String.valueOf(variables.get(second)) : second.replace("\"", "");
    
    return firstVal + secondVal;
}

}
