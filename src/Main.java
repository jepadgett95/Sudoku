public class Main {
    public static void main(String[] args) {

        Sudoku sudoku = Sudoku.startMenu();

        while (!sudoku.quit){
            sudoku.drawPuzzle();
            sudoku.updateNumber();
        }


    }
}