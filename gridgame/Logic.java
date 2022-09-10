package gridgame;

import java.util.*;

public class Logic {
    static String[][] chessBoard = new String[5][5];
    static int[] health = new int[] { 5, 5 };

    static void FillBoard(String value) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                chessBoard[i][j] = "-\t";
            }
        }
    }

    static void PrintMatrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println("");
        }
    }

    //change board position according to position entered
    static void UpdateBoard(int[] pos, String key, String move, int turn) {
        chessBoard[pos[0]][pos[1]] = "-";
        if(turn==1){
            switch(move){
                case "L":
                    pos[0] = pos[0];
                    pos[1] = pos[1]- 1;
                    break;
                case "R":
                    pos[0] = pos[0];
                    pos[1] = pos[1] + 1;
                    break;
                case "F":
                    pos[0] = pos[0] - 1;
                    pos[1] = pos[1] ;
                    break;
                case "B":
                    pos[0] = pos[0] + 1;
                    pos[1] = pos[1];
                    break;
                default: System.out.println("Invalid Move");
            }
        }else if (turn==2){
            switch(move){
                case "L":
                    pos[0] = pos[0];
                    pos[1] = pos[1]+ 1;
                    break;
                case "R":
                    pos[0] = pos[0];
                    pos[1] = pos[1] - 1;
                    break;
                case "F":
                    pos[0] = pos[0] + 1;
                    pos[1] = pos[1] ;
                    break;
                case "B":
                    pos[0] = pos[0] - 1;
                    pos[1] = pos[1];
                    break;
                default: System.out.println("Invalid Move");
            }
        }
        

        if (chessBoard[pos[0]][pos[1]] != "-") {
            if (turn == 1)
                health[1]--;
            else
                health[0]--;
        }

        chessBoard[pos[0]][pos[1]] = key;
        PrintMatrix();
    }

    //Intial input setter accoeding to payer input data
    static void InputSetter() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            System.out.println("Player " + (i + 1) + " Input:");
            //sc.nextLine();
            String inputString = sc.nextLine();
            String[] data = inputString.split(",");
            String temp = "";
            int indx = 0;
            if (i == 0) {
                temp = "A";
                indx = 4;
            } else {
                temp = "B";
                indx = 0;
            }

            for (int a = 0; a < 5; a++) {
                chessBoard[indx][a] = temp + "-" + data[a]+"\t";
            }

            PrintMatrix();
        }
    }

    //check the position of entered player position and return that
    static int[] findPos(String key) {
        int[] tpos = new int[2];
        key = key.trim();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String match = chessBoard[i][j].trim();
                if (key.equals(match)) {
                    tpos[0] = i;
                    tpos[1] = j;
                    return tpos;
                }
            }
        }
        return tpos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FillBoard("-");
        InputSetter();

        int turn = 1;
        //Game play loop
        while (health[0] > 0 && health[1] > 0) {
            //check which player's turn it is
            if (turn == 1) {
                System.out.print("Player A's Move:");
                String move = sc.next();
                String[] breakInput = move.split(":");
                String key = "A-" + breakInput[0];
                int[] pos = findPos(key);
                System.out.println(Arrays.toString(pos));
                UpdateBoard(pos, key, breakInput[1], 1);
                if (health[1] == 0)
                    System.out.println("A is winner");
                turn = 2;
            } else {
                System.out.print("Player B's Move:");
                String move = sc.next();
                String[] breakInput = move.split(":");
                String key = "B-" + breakInput[0];
                int[] pos = findPos(key);
                UpdateBoard(pos, key, breakInput[1], 1);
                if (health[0] == 0)
                    System.out.println("B is winner");
                turn = 1;
            }
        }
        sc.close();
    }
}
