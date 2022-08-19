package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class LifeGame {
    private static int[][] field;
    private static final int width = 10;
    private static final int height = 10;

    private static final ArrayList<int[][]> buf = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        initField(width, height);


        field[0][0] = 1;
        field[1][0] = 1;
        field[1][1] = 1;
        field[1][2] = 1;
        field[0][1] = 1;
        field[2][2] = 1;
        field[2][1] = 1;
        printField();

        boolean correct = true;

        while (correct) {
            Thread.sleep(5000);
            int[][] life = life();

            if (Arrays.stream(field).flatMapToInt(Arrays::stream).noneMatch(value -> value == 1)) {
                correct = false;
            }

            if(Arrays.deepEquals(field, life) && buf.contains(life)){
                correct = false;
            }

            buf.add(life);
            printField();

        }
    }

    private static int[][] life() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                List<Coordinate> neighboursForCell = getNeighboursForCell(j, i);
                long counter = neighboursForCell.stream().filter(coordinate -> field[coordinate.x][coordinate.y] == 1).count();

                if ((counter > 3 || counter < 2) && field[i][j] == 1) {
                    field[i][j] = 0;
                }

                if (counter == 3 && field[i][j] == 0) {
                    field[i][j] = 1;
                }
            }
        }
        return field;
    }


    private static void printField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static List<Coordinate> getNeighboursForCell(int x, int y) {
        List<Coordinate> coordinates = new ArrayList<>(List.of(
                new Coordinate(x, y + 1),
                new Coordinate(x, y - 1),
                new Coordinate(x + 1, y),
                new Coordinate(x - 1, y),
                new Coordinate(x - 1, y - 1),
                new Coordinate(x + 1, y + 1),
                new Coordinate(x - 1, y + 1),
                new Coordinate(x + 1, y - 1)
        ));
        if (x > 1 && x < width - 1 && y < height - 1 && y > 1) {
            return coordinates;
        }

        if (y < 1) {
            coordinates.remove(new Coordinate(x, y - 1));
            coordinates.remove(new Coordinate(x + 1, y - 1));
            coordinates.remove(new Coordinate(x - 1, y - 1));
        }

        if (y >= height - 1) {
            coordinates.remove(new Coordinate(x, y + 1));
            coordinates.remove(new Coordinate(x + 1, y + 1));
            coordinates.remove(new Coordinate(x - 1, y + 1));
        }

        if(x < 1) {
            coordinates.remove(new Coordinate(x - 1, y - 1));
            coordinates.remove(new Coordinate(x - 1, y + 1));
            coordinates.remove(new Coordinate(x - 1, y));
        }

        if (x >= width - 1) {
            coordinates.remove(new Coordinate(x + 1, y - 1));
            coordinates.remove(new Coordinate(x + 1, y + 1));
            coordinates.remove(new Coordinate(x + 1, y));
        }

        return coordinates;
    }

    private static void initField(int width, int height) {

        field = new int[height][width];

        for (int i = 0; i < height; i++) {
            field[i] = new int[width];
            Arrays.fill(field[i], 0);
        }

    }

    @AllArgsConstructor
    @Data
    static class Coordinate {
        private int x;
        private int y;


    }
}
