package gridgame;

import java.util.*;

/*class PlayerMoves{
    int[] L = {-1,0};
    int[] R = {1,0};
    int[] F = {0,1};
    int[] B = {0,-1};
}*/

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

    static void UpdateBoard(int[] pos, String key, String move, int turn) {
        chessBoard[pos[0]][pos[1]] = "-";
        switch (move) {
            case "L":
                pos[0] = pos[0] - 1;
                pos[1] = pos[1] + 0;
                break;
            case "R":
                pos[0] = pos[0] + 1;
                pos[1] = pos[1] + 0;
                break;
            case "F":
                pos[0] = pos[0] + 0;
                pos[1] = pos[1] + 1;
                break;
            case "B":
                pos[0] = pos[0] + 0;
                pos[1] = pos[1] - 1;
                break;
            default: System.out.println("Invalid Move");
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

    static void InputSetter() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            System.out.println("Player " + (i + 1) + " Input:");
            String[] data = new String[5];
            for (int j = 0; j < 5; j++) {
                data[j] = sc.next();
            }

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

    static int[] findPos(String key) {
        int[] pos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (chessBoard[i][j].equals(key)) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FillBoard("-");
        InputSetter();

        int turn = 1;

        while (health[0] > 0 && health[1] > 0) {
            if (turn == 1) {
                System.out.print("Player A's Move:");
                String move = sc.next();
                String[] breakInput = move.split(":");
                String key = "A-" + breakInput[0];
                int[] pos = findPos(key);
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
    }
}
