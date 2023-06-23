import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Sudoku {
    public String[] puzzle;
    public String[] solution;
    public int index;
    public boolean quit;
    public ArrayList<Integer> startingNumbers = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";






    private static String[] puzzleEasy = {
            " "," "," ","8"," "," "," ","3"," ",
            " ","1","4"," "," ","5","7"," "," ",
            "3"," ","9"," "," "," ","5","6"," ",
            " ","3","6","7","8"," "," "," "," ",
            "1"," "," "," "," "," ","4"," "," ",
            " "," "," "," ","1","3"," ","7"," ",
            " "," "," ","6"," "," "," ","4","9",
            "6","4","2"," ","3","9"," ","5","7",
            "7"," ","3","4","5","8"," ","2","1",};


    private static String[] solutionEasy = {
            "2","6","5","8","9","7","1","3","4",
            "8","1","4","3","6","5","7","9","2",
            "3","7","9","2","4","1","5","6","8",
            "9","3","6","7","8","4","2","1","5",
            "1","5","7","9","2","6","4","8","3",
            "4","2","8","5","1","3","9","7","6",
            "5","8","1","6","7","2","3","4","9",
            "6","4","2","1","3","9","8","5","7",
            "7","9","3","4","5","8","6","2","1",};

    private static String[] puzzleMedium = {
            "9"," ","3","4"," "," "," "," ","8",
            " "," ","7","9","1"," ","3"," ","2",
            " ","5"," "," "," ","3"," "," ","7",
            " "," ","5"," "," "," "," "," "," ",
            " "," ","6","3","4"," "," ","5","1",
            " "," "," "," "," "," ","4"," "," ",
            " "," ","8","7","6"," ","2"," "," ",
            " "," ","9","1"," ","2","6"," "," ",
            " "," "," "," ","9"," "," ","3","4",};

    private static String[] solutionMedium = {
            "9"," ","3","4"," "," "," "," ","8",
            " "," ","7","9","1"," ","3"," ","2",
            " ","5"," "," "," ","3"," "," ","7",
            " "," ","5"," "," "," "," "," "," ",
            " "," ","6","3","4"," "," ","5","1",
            " "," "," "," "," "," ","4"," "," ",
            " "," ","8","7","6"," ","2"," "," ",
            " "," ","9","1"," ","2","6"," "," ",
            " "," "," "," ","9"," "," ","3","4",};

    private static String[] puzzleHard = {
            " ","3"," "," ","6","2"," ","1"," ",
            "2"," ","9","1"," "," ","7","6"," ",
            "6","5","1"," "," "," "," "," "," ",
            "3"," "," "," ","9"," ","1"," "," ",
            " "," "," "," ","5","3"," "," ","4",
            " ","7"," ","2"," "," "," ","9","6",
            " ","2"," "," "," ","9","8","3"," ",
            "1"," "," "," ","2"," "," "," "," ",
            " "," ","4"," "," "," "," ","7"," ",};

    private static String[] solutionHard = {
            "7","3","8","9","6","2","4","1","5",
            "2","4","9","1","8","5","7","6","3",
            "6","5","1","3","7","4","9","2","8",
            "3","8","2","4","9","6","1","5","7",
            "9","1","6","7","5","3","2","8","4",
            "4","7","5","2","1","8","3","9","6",
            "5","2","7","6","4","9","8","3","1",
            "1","6","3","8","2","7","5","4","9",
            "8","9","4","5","3","1","6","7","2",};

    private static String[] puzzleTest = {
            "2","6","5","8","9","7","1"," ","4",
            "8"," ","4","3","6","5","7","9","2",
            "3","7","9"," ","4","1","5","6","8",
            "9","3","6","7","8","4","2","1","5",
            "1","5","7","9","2","6","4","8","3",
            " ","2","8"," ","1","3","9","7"," ",
            "5","8","1","6","7","2","3","4"," ",
            "6","4","2","1","3","9","8","5","7",
            " ","9","3","4","5"," ","6","2","1",};

    private static String[] solutionTest = {
            "2","6","5","8","9","7","1","3","4",
            "8","1","4","3","6","5","7","9","2",
            "3","7","9","2","4","1","5","6","8",
            "9","3","6","7","8","4","2","1","5",
            "1","5","7","9","2","6","4","8","3",
            "4","2","8","5","1","3","9","7","6",
            "5","8","1","6","7","2","3","4","9",
            "6","4","2","1","3","9","8","5","7",
            "7","9","3","4","5","8","6","2","1",};


    private static HashMap <String, Sudoku> puzzles = new HashMap<String, Sudoku>();










    Sudoku(String[] puzzle, String[] solution){
        this.puzzle = puzzle;
        this.solution = solution;
        this.index = 100;
        this.quit = false;
        for (int i = 0; i<81; i++){
            if (puzzle[i] != " "){
                startingNumbers.add(i);
            }
        }
    }

    public void drawPuzzle(){
        int rows = 1;
        System.out.println("   1  2  3   4  5  6   7  8  9  ");
        System.out.println(" ┏━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━┓");
        for (int i=0; i<81; i++){
            if (i%9 == 0 || i%9 == 3 || i%9 == 6){
                if (i%9 == 0){
                    System.out.print(rows);
                    rows++;
                }
                System.out.print("┃");
            }

            if (i == this.index){System.out.print(formatDigit(i));}
            else if (startingNumbers.contains(i)){System.out.print(formatDigit(i));}
            else{System.out.print(formatDigit(i));}

            if(i%9 == 8){
                System.out.println("┃");

                if(i < 80 && i%27 == 26){
                    System.out.println(" ┣━━━━━━━━━╋━━━━━━━━━╋━━━━━━━━━┫");
                }
            }
        }
        System.out.println(" ┗━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━┛");

    }

    public void updateNumber(){
        int row = 0;
        int column = 0;
        Scanner scanner = new Scanner(System.in);

        if (!this.quit){
            System.out.print("Row: ");
            row = validateRow() - 1;}

        if (!this.quit){
            System.out.print("Column: ");
            column = validateRow() - 1;}

        if (!this.quit){
            this.index = (row * 9) + (column);
            drawPuzzle();

            System.out.print("Number: ");
            String newNumber = validateDigit(scanner.nextLine());
            if (newNumber != "" && !startingNumbers.contains(this.index)){
                this.puzzle[this.index] = newNumber;
                if (Arrays.equals(this.puzzle, this.solution)){
                    drawPuzzle();
                    System.out.println("You solved it!");
                    this.quit = true;
                }
            }
        }
     }

     public String formatDigit(int index){

         if (index == this.index){
             if (startingNumbers.contains(index)) {
                 return ("*" + ANSI_GREEN + this.puzzle[index] + ANSI_RESET + "*");
             }
             else if(!this.puzzle[index].equals(this.solution[index])){
                 return ("*" + ANSI_RED + this.puzzle[index] + ANSI_RESET + "*");
             }
             else{
                 return ("*" + this.puzzle[index] + "*");
             }

         }
         else{
             if (startingNumbers.contains(index)) {
                 return (" " + ANSI_GREEN + this.puzzle[index] + ANSI_RESET + " ");
             }
             else if(!this.puzzle[index].equals(this.solution[index])){
                 return (" " + ANSI_RED + this.puzzle[index] + ANSI_RESET + " ");
             }
             else{
                 return (" " + this.puzzle[index] + " ");
             }

         }
     }

     public String validateDigit(String digit){
        if (digit.equals("q")){this.quit = true;}
        try{
            if (Integer.parseInt(digit) <1 || Integer.parseInt(digit) >9){
                return ("");
            }
            else{
                return digit;
            }
        }
        catch(Exception e){
            //e.printStackTrace();
            return ("");
         }

     }

     public int validateRow(){
        Scanner scanner = new Scanner(System.in);
        String digit = scanner.nextLine();

        boolean check = false;
        while (!check) {
            if (digit.equals("q")){this.quit = true; check = true; break;}
            try {
                if (Integer.parseInt(digit) < 1 || Integer.parseInt(digit) > 9) {
                    System.out.println("Invalid input, please try again");
                    digit = scanner.nextLine();
                }
                else {
                    check = true;
                    return Integer.parseInt(digit);
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input, please try again");
                digit = scanner.nextLine();
            }
        }
        //should never reach this return
        return 0;
     }

     public static Sudoku startMenu(){
         puzzles.put("easy", new Sudoku(puzzleEasy, solutionEasy));
         puzzles.put("medium", new Sudoku(puzzleMedium, solutionMedium));
         puzzles.put("hard", new Sudoku(puzzleHard, solutionHard));
         puzzles.put("test", new Sudoku(puzzleTest, solutionTest));
         Scanner scanner = new Scanner(System.in);

         System.out.println("Enter \"q\" at any time to quit");
         System.out.println("Please select a sudoku puzzle from these options: ");

         for (String puzzle : puzzles.keySet()){
             System.out.println(puzzle);
         }
         System.out.print("> ");

         String input = scanner.nextLine();

         while (!puzzles.keySet().contains(input)){
             if (input.equals("q")){
                 String placeholder[] = new String[81];
                 Sudoku sudoku = new Sudoku(placeholder, placeholder);
                 sudoku.quit = true;
                 return sudoku;}
             System.out.println("invalid input, please try again");
             input = scanner.nextLine();
         }

         return puzzles.get(input);

     }
}
