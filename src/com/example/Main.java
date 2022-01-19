package com.example;//package com.example;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        Map<String, Integer> dictionary = new HashMap<>();

        dictionary.put("A", 0);
        dictionary.put("B", 1);
        dictionary.put("C", 2);
        dictionary.put("D", 3);
        dictionary.put("E", 4);
        dictionary.put("F", 5);
        dictionary.put("G", 6);
        dictionary.put("H", 7);
        dictionary.put("I", 8);
        dictionary.put("L", 9);

        Random ran = new Random();
        Scanner scanner = new Scanner(System.in);
        int rows = 10;
        int columns = 10;
        String[][] grid = new String[rows][columns];
        String[][] computergrid = new String[rows][columns];
        String[][] hits = new String[rows][columns];
        randomShipPosition(grid);
        randomShipPosition(computergrid);

        boolean game = true;
        String turn = "player";

        while (game) {
            int choice = 0;

            printMenu();
            while(true) {
                try {
                    System.out.println("Enter your choice");
                    choice = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    game = false;
                    break;
                case 2:

                    if (turn == "player") {
                        System.out.println("Computer board");
                        printBoard(hits);
                        System.out.println("Your board");
                        printBoard(grid);
                        int x = 0;
                        String y = "";
                        int column = 0;
                        while (true) {
                            try {

                                System.out.println("Enter coordinate row: ");

                                x = scanner.nextInt();
                                if (x <=9){
                                    break;
                                } else {
                                    throw new Exception();
                                }



                            } catch (Exception e) {
                                System.out.println("Please enter a valid number");
                                scanner.nextLine();
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("Enter coordinate column: ");
                                y = scanner.next();
                                column = dictionary.get(y.toUpperCase());
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid letter");
                                scanner.nextLine();
                            }
                        }



                        int hitResult = hit(x, column, computergrid);
                        if (hitResult == 1) {
                            hits[x][column] = " H  ";
                            System.out.println("Well done! You hit the boat");
                            turn = "computer";
                        } else if (hitResult == 0) {
                            hits[x][column] = " W  ";
                            System.out.println("Water");
                            turn = "computer";
                        } else if (!checkWin(grid)) {
                            System.out.println("Player One Won !!");
                            game = false;
                        } else {
                            System.out.println("Place already hit, check the hit grid");
                            turn = "player";
                        }
                    }


                    System.out.println("-------------hits_Greed------------");
                    printBoard(hits);
                    System.out.println("-------------Player grid------------");
                    printBoard(grid);

                    if (turn == "computer") {
                        System.out.println("I am here");
                        int ranRow = ran.nextInt(10);
                        int ranColumn = ran.nextInt(10);

                        while (hit(ranRow, ranColumn, grid)==1 || hit(ranRow, ranColumn, grid)==0);


                        turn = "player";


                            if (!checkWin(computergrid)) {
                                System.out.println("The computer Won");
                                game = false;
                            }
                    }


            }
        }

    }
















    public static void printBoard(String [][] grid){
        int count = 0;
        int j = 0;
        System.out.println("    A    B    C    D    E    F    G    H    I    L");

        while (j < grid.length){
            System.out.print(j + ") ");
            for(int i = 0; i < grid.length; i++){
                count++;
                System.out.print(grid[j][i] + "|");
            }

            System.out.println("");

            j++;

        }

    }

    public static boolean shipPosition(int row, int column, String [][] grid, boolean isVertical, String boat ){
      if(boat.toLowerCase() == "d") {
          if (!isVertical) {
              try {
                  if ((grid[column][row] == null) && (grid[row][column + 1] == null) && (grid[row][column + 2] == null) && (grid[row][column + 3] == null)) {
                      grid[row][column] = " D  ";
                      grid[row][column + 1] = " D  ";
                      grid[row][column + 2] = " D  ";
                      grid[row][column + 3] = " D  ";

                      return true;
                  }
              } catch (Exception e) {
                  //System.out.println("The boat cannot be palced here because exceed the length of the board");

              }

          }
          if (isVertical) {
              try {
                  if ((grid[column][row] == null) && (grid[column + 1][row] == null) && (grid[column + 2][row] == null) && (grid[column + 3][row] == null)) {
                      grid[column][row] = " D  ";
                      grid[column + 1][row] = " D  ";
                      grid[column + 2][row] = " D  ";
                      grid[column + 3][row] = " D  ";
                      return true;
                  }
              } catch (Exception e) {
                  //System.out.println("The boat cannot be placed here because exceed the length of the board or the place is already taken");
              }

          }
      }


        if(boat == "b") {
            if (!isVertical) {
                try {
                    if ((grid[column][row] == null) && (grid[row][column + 1] == null) && (grid[row][column + 2] == null) && (grid[row][column + 3] == null) &&(grid[row][column + 4] == null)) {
                        grid[row][column] = " B  ";
                        grid[row][column + 1] = " B  ";
                        grid[row][column + 2] = " B  ";
                        grid[row][column + 3] = " B  ";
                        grid[row][column + 4] = " B  ";

                        return true;
                    }
                } catch (Exception e) {
                    System.out.println("The boat cannot be palced here because exceed the length of the board");

                }

            }
            if (isVertical) {
                try {
                    if ((grid[column][row] == null) && (grid[column + 1][row] == null) && (grid[column + 2][row] == null) && (grid[column + 3][row] == null) && (grid[column + 3][row] == null)) {
                        grid[column][row] = " B  ";
                        grid[column + 1][row] = " B  ";
                        grid[column + 2][row] = " B  ";
                        grid[column + 3][row] = " B  ";
                        grid[column + 4][row] = " B  ";

                        return true;
                    }
                } catch (Exception e) {
                    System.out.println("The boat cannot be placed here because exceed the length of the board or the place is already taken");
                }

            }
        }

        return false;
    }


    public static void randomShipPosition(String [][] grid){
        Random ran = new Random();
        int countBattleShip = 3;
        while (countBattleShip > 0) {
            if (countBattleShip >= 2) {
                if (shipPosition(ran.nextInt(10), ran.nextInt(10), grid, ran.nextBoolean(), "d")) {
                    countBattleShip--;
                }

            }


            if (countBattleShip < 2) {
                if (shipPosition(ran.nextInt(10), ran.nextInt(10), grid, ran.nextBoolean(), "b")) {
                    countBattleShip--;
                }
            }
        }

    }


    public static int  hit(int x,int y, String [][] grid){
        if (grid[x][y] == " D  " || grid[x][y]== " B  " && grid[x][y] != " H  " && grid[x][y] != " W  "){
            grid[x][y] = " H  ";
            return 1;
        }
        else if(x < 10 && y < 10 && grid[x][y] == null){
            grid[x][y] = " W  ";
            return 0;

        }
        else if (grid[x][y] == " W  "){
            //System.out.println("Place already try again");
            return 2;
        }
        else if(grid[x][y] == " H  "){
            //System.out.println("Place already hit, try again");
            return 2;
        }
        else{
            System.out.println("Out of the grid");

        }
        return 2;
    }

    public static void printMenu(){
        System.out.println("(1 - Quit) - (2 - hit)");
    }

    public static boolean checkWin(String[][] grid){
        List<String[]> list = Arrays.asList(grid);
        for(String[] arr: list){
            boolean checkD =  Arrays.asList(arr).contains(" D  ");
            boolean checkB =  Arrays.asList(arr).contains(" B  ");
            if (checkD || checkB){
                return true;
            }

        }

        return false;
    }







    }














