package com.company;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;

    private enum Move {
        ROCK, PAPER, SCISSORS;


        public int compareMoves(Move otherMove) {

            if (this == otherMove)
                return 0;

            switch (this) {
                case ROCK:
                    return (otherMove == SCISSORS ? 1 : -1);
                case PAPER:
                    return (otherMove == ROCK ? 1 : -1);
                case SCISSORS:
                    return (otherMove == PAPER ? 1 : -1);
            }



            return 0;
        }
    }

    private class User {
        private Scanner inputScanner;

        public User() {
            inputScanner = new Scanner(System.in);
        }

        public Move getMove() {

            System.out.println("Камень, ножницы или бумага? ");


            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            if (firstLetter == 'К' || firstLetter == 'Н' || firstLetter == 'Б') {

                switch (firstLetter) {
                    case 'К':
                        return Move.ROCK;
                    case 'Б':
                        return Move.PAPER;
                    case 'Н':
                        return Move.SCISSORS;
                }
            }


            return getMove();
        }

        public boolean playAgain() {
            System.out.println("Хотите сыграть   еще раз? ");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Д';
        }
    }

    private class Computer {
        public Move getMove() {
            Move[] moves = Move.values();
            Random random = new Random();
            int index = random.nextInt(moves.length);
            return moves[index];
        }
    }

    public RockPaperScissors() {
        user = new User();
        computer = new Computer();
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
    }

    public void startGame() {
        System.out.println("КАМЕНЬ, НОЖНИЦЫ, БУМАГА!!!");


        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nВаш ход " + userMove + ".");
        System.out.println("Ход компьютера " + computerMove + ".\n");


        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
            case 0:
                System.out.println("Ничья!");
                break;
            case 1:
                System.out.println(userMove + " бьёт " + computerMove + ". Вы победили!");
                userScore++;
                break;
            case -1:
                System.out.println(computerMove + " бьёт " + userMove + ". Вы проиграли.");
                computerScore++;
                break;
        }
        numberOfGames++;


        if (user.playAgain()) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }


    private void printGameStats() {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;


        System.out.print("+");
        printDashes(68);
        System.out.println("+");


        System.out.printf("| %6s   | %6s| %6s   | %12s   | %14s   |\n",
                "ПОБЕДА", "ПОРАЖЕНИЕ", "НИЧЬЯ", "ВСЕГО ИГР", "ПРОЦЕНТ ПОБЕД");


        System.out.print("|");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(16);
        System.out.print("+");
        printDashes(18);
        System.out.println("|");


        System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
                wins, losses, ties, numberOfGames, percentageWon * 100);


        System.out.print("+");
        printDashes(68);
        System.out.println("+");
    }

    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        game.startGame();
    }
}
