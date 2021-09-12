package clientModule.util;

import common.data.Coordinates;
import common.data.LocationFrom;
import common.data.LocationTo;
import common.exceptions.IncorrectInputInScriptException;
import common.exceptions.MustBeNotEmptyException;
import common.exceptions.NotInBoundsException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Asks a user a Route value.
 */
public class Interactions {
    public static final String INPUT_INFO = "> ";
    private final long MIN_DISTANCE = 1;
    private final double MIM_COORDINATES_Y = -30.0;
    private Pattern patternNumber = Pattern.compile("-?\\d+(\\.\\d+)?");

    private Scanner userScanner;
    private boolean fileMode;

    public Interactions(Scanner scanner) {
        this.userScanner = scanner;
        fileMode = false;
    }

    /**
     * Returns scanner, which uses for user input.
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets a scanner to scan user input.
     * @param scanner Scanner to set.
     */
    public void setUserScanner(Scanner scanner) {
        this.userScanner = scanner;
    }

    /**
     * Sets route's asker mode to 'User Mode'.
     */
    public void setUserMode() {
        this.fileMode = false;
    }

    /**
     * Sets route's asker mode to 'File Mode'.
     */
    public void setFileMode() {
        this.fileMode = true;
    }

    /**
     * Asks a user the route's name.
     * @param inputTitle title of input.
     * @return Name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public String askName(String inputTitle) throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println(inputTitle);
                Console.print(INPUT_INFO);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.println("Name not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            } catch (MustBeNotEmptyException exception) {
                Console.println("The name cannot be empty!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.println("An unexpected error!");
                System.exit(0);
            } catch (NullPointerException exception){
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user coordinate in a long value.
     * @param coordinateName title of input.
     * @param minValue  min value of coordinate.
     * @param maxValue  max value of coordinate.
     * @return Coordinate's Long value.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Long askLong(String coordinateName, Long minValue, Long maxValue) throws IncorrectInputInScriptException{
        String str = "";
        long number;
        while (true) {
            try {
                Console.println("Input the coordinate " + coordinateName + ": ");
                Console.print(INPUT_INFO);
                str = userScanner.nextLine().trim();
                if (fileMode) Console.println(str);
                number = Long.parseLong(str);
                if ((number < minValue) || (number > maxValue)) throw new NumberFormatException();
                break;
            } catch (NoSuchElementException exception) {
                Console.println("Coordinate " + coordinateName + " not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(str).matches())
                    Console.println("Coordinate " + coordinateName + " should be in the range (" + minValue
                            + ";" + maxValue + ")!");
                else
                    Console.println("Coordinate " + coordinateName + " must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.println("Unexpected error!");
                System.exit(0);
            }
        }
        return number;
    }

    /**
     * Asks a user coordinate in a double value.
     * @param coordinateName title of input.
     * @param minValue  min value of coordinate.
     * @param maxValue  max value of coordinate.
     * @return Coordinate's Double value.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Double askDouble(String coordinateName, Double minValue, Double maxValue) throws IncorrectInputInScriptException{
        String str = "";
        double number;
        while (true) {
            try {
                Console.println("Input the coordinate " + coordinateName + ": ");
                Console.print(INPUT_INFO);
                str = userScanner.nextLine().trim();
                if (fileMode) Console.println(str);
                number = Double.parseDouble(str);
                if ((number < minValue) || (number > maxValue)) throw new NumberFormatException();
                break;
            } catch (NoSuchElementException exception) {
                Console.println("Coordinate " + coordinateName + " not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(str).matches())
                    Console.println("Coordinate " + coordinateName + " should be in the range (" + minValue
                            + ";" + maxValue + ")!");
                else
                    Console.println("Coordinate " + coordinateName + " must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.println("Unexpected error!");
                System.exit(0);
            }
        }
        return number;
    }

    /**
     * Asks a user coordinate in a Float value.
     * @param coordinateName title of input.
     * @param minValue  min value of coordinate.
     * @param maxValue  max value of coordinate.
     * @return Coordinate's Float value.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Float askFloat (String coordinateName, Float minValue, Float maxValue) throws IncorrectInputInScriptException{
        String str = "";
        float number;
        while (true) {
            try {
                Console.println("Input the coordinate " + coordinateName + ": ");
                Console.print(INPUT_INFO);
                str = userScanner.nextLine().trim();
                if (fileMode) Console.println(str);
                number = Float.parseFloat(str);
                if ((number < minValue) || (number > maxValue)) throw new NumberFormatException();
                break;
            } catch (NoSuchElementException exception) {
                Console.println("Coordinate " + coordinateName + " not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(str).matches())
                    Console.println("Coordinate " + coordinateName + " should be in the range (" + minValue
                            + ";" + maxValue + ")!");
                else
                    Console.println("Coordinate " + coordinateName + " must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.println("Unexpected error!");
                System.exit(0);
            }
        }
        return number;
    }

    /**
     * Asks a user Coordinates.
     * @return Coordinates.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        long x = askLong("X", -Long.MAX_VALUE, Long.MAX_VALUE);
        Double y = askDouble("Y", MIM_COORDINATES_Y, Double.MAX_VALUE);
        return new Coordinates(x, y);
    }

    /**
     * Asks a user LocationFrom.
     * @return LocationFrom.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public LocationFrom askLocationFrom() throws IncorrectInputInScriptException{
        long x = askLong("X", -Long.MAX_VALUE, Long.MAX_VALUE);
        long y = askLong("Y", -Long.MAX_VALUE, Long.MAX_VALUE);
        Float z = askFloat("Z", -Float.MAX_VALUE, Float.MAX_VALUE);
        return new LocationFrom(x, y, z);
    }

    /**
     * Asks a user LocationTo.
     * @return LocationTo.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public LocationTo askLocationTo() throws IncorrectInputInScriptException{
        Double x = askDouble("X",-Double.MAX_VALUE, Double.MAX_VALUE);
        float y = askFloat("Y", -Float.MAX_VALUE, Float.MAX_VALUE);
        Float z = askFloat("Z", -Float.MAX_VALUE, Float.MAX_VALUE);
        String name = askName("Input the name of the location");
        return new LocationTo(x, y, z, name);
    }

    /**
     * Asks a user distance.
     * @return Distance.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Long askDistance() throws IncorrectInputInScriptException{
        String str = "";
        long distance;
        while (true) {
            try {
                Console.println("Input distance: ");
                Console.print(INPUT_INFO);
                str = userScanner.nextLine().trim();
                if (fileMode) Console.println(str);
                distance = Long.parseLong(str);
                if (distance < MIN_DISTANCE) throw new NumberFormatException();
                break;
            } catch (NoSuchElementException exception) {
                Console.println("Distance not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(str).matches())
                    Console.println("Distance must be in range (" + MIN_DISTANCE
                            + ";" + Long.MAX_VALUE + ")!");
                else
                    Console.println("Distance must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.println("Unexpected error!");
                System.exit(0);
            }
        }
        return distance;
    }

    /**
     * Asks a user a question.
     * @param question A question.
     * @return Answer (true/false).
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(INPUT_INFO);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInBoundsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.println("The answer is not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            } catch (NotInBoundsException exception) {
                Console.println("The answer must be represented by signs '+' or '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.println("Unexpected error!");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }

}