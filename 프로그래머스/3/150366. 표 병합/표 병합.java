import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;
import java.util.Set;

class Solution {
    private static final String[][] TABLE = new String[51][51];
    private static final int[][] GROUP = new int[51][51];
    private static final String EMPTY = "EMPTY";
    private static final List<String> PRINT = new ArrayList<>();

    private static int lastGroupNumber = 1;

    public static class Position {
        int row;
        int column;

        @Override
        public boolean equals(Object object) {
            Position position = (Position) object;
            return row == position.row && column == position.column;
        }

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public String[] solution(String[] commands) {
        for (String[] row : TABLE) {
            Arrays.fill(row, "");
        }
        
        for (String command : commands) {
            executeCommand(command);
        }

        return PRINT.toArray(new String[PRINT.size()]);
    }

        private void executeCommand(String command) {
        String prefix = command.split(" ")[0];
        if (prefix.equals("UPDATE")) {
            executeUpdate(command);
        }

        if (prefix.equals("MERGE")) {
            executeMerge(command);
        }

        if (prefix.equals("UNMERGE")) {
            executeUnmerge(command);
        }

        if (prefix.equals("PRINT")) {
            executePrint(command);
        }
    }

    private void executeUpdate(String command) {
        String[] splitCommand = command.split(" ", 4);
        if(splitCommand.length == 4) {
            Position position = new Position(Integer.parseInt(splitCommand[1]), Integer.parseInt(splitCommand[2]));
            updateValue(position, splitCommand[3]);
        }
        else if(splitCommand.length == 3) {
            updateValue(splitCommand[1], splitCommand[2]);
        }
    }

    private void updateValue(Position position, String value) {
        Set<Position> groupPositions = getGroupPositions(position);
        for (Position groupPosition : groupPositions) {
            write(groupPosition, value);
        }
    }

        private Set<Position> getGroupPositions(Position position) {
        int groupNumber = getGroupNumber(position);
        if (groupNumber == 0) {
            return Collections.singleton(position);
        }

        Set<Position> groupPositions = new HashSet<>();

        for (int row = 1; row < 51; row++) {
            for (int column = 1; column < 51; column++) {
                if (GROUP[row][column] == groupNumber) {
                    groupPositions.add(new Position(row, column));
                }
            }
        }

        return groupPositions;
    }

    private int getGroupNumber(Position position) {
        return GROUP[position.row][position.column];
    }


    private void write(Position position, String value) {
        TABLE[position.row][position.column] = value;
    }


    private void updateValue(String originalValue, String newValue) {
        Set<Position> positions = getPositionsByValue(originalValue);
        for (Position position : positions) {
            write(position, newValue);
        }
    }

    private Set<Position> getPositionsByValue(String value) {
        Set<Position> positions = new HashSet<>();
        for (int row = 1; row < 51; row++) {
            for (int column = 1; column < 51; column++) {
                if(TABLE[row][column].equals(value)) {
                    positions.add(new Position(row, column));
                }
            }
        }
        return positions;
    }

    private void executeMerge(String command) {
        String[] splitCommand = command.split(" ");
        Position position1 = new Position(Integer.parseInt(splitCommand[1]), Integer.parseInt(splitCommand[2]));
        Position position2 = new Position(Integer.parseInt(splitCommand[3]), Integer.parseInt(splitCommand[4]));
        // if(position1.equals(position2) || (getGroupNumber(position1) != 0 && getGroupNumber(position1) == getGroupNumber(position2))) {
        //     return;
        // }
        Set<Position> groupPositions1 = getGroupPositions(position1);
        Set<Position> groupPositions2 = getGroupPositions(position2);

        int groupNumber = getGroupNumber(position1);
        if(groupNumber == 0) {
            groupNumber = lastGroupNumber++;
            setGroupNumber(position1, groupNumber);
        }

        
        for (Position position : groupPositions1) {
            setGroupNumber(position, groupNumber);
        }
        
        for (Position position : groupPositions2) {
            setGroupNumber(position, groupNumber);
        }

        
        if(getValue(position1).isEmpty() && !getValue(position2).isEmpty()) {
            writeMergeValue(getValue(position2), groupPositions1);
        }
        else if (!getValue(position1).isEmpty() && getValue(position2).isEmpty()) {
            writeMergeValue(getValue(position1), groupPositions2);
        }
        else {
            writeMergeValue(getValue(position1), groupPositions2);
        }
    }

    private void setGroupNumber(Position position, int groupNumber) {
        GROUP[position.row][position.column] = groupNumber;
    }

    private void writeMergeValue(String value, Set<Position> positions) {
        for (Position position : positions) {
            write(position, value);
        }
    }

    private String getValue(Position position) {
        return TABLE[position.row][position.column];
    }

    private void executeUnmerge(String command) {
        String[] splitCommand = command.split(" ");
        Position position = new Position(Integer.parseInt(splitCommand[1]), Integer.parseInt(splitCommand[2]));
        unmerge(position);
    }

    private void unmerge(Position position) {
        int groupNumber = getGroupNumber(position);
        String value = getValue(position);
        if(groupNumber == 0) {
            return;
        }
        for (int row = 1; row < 51; row++) {
            for (int column = 1; column < 51; column++) {
                if(GROUP[row][column] == groupNumber) {
                    GROUP[row][column] = 0;
                    TABLE[row][column] = "";
                }
            }
        }
        write(position, value);
    }

    private void executePrint(String command) {
        String[] splitCommand = command.split(" ");
        Position position = new Position(Integer.parseInt(splitCommand[1]), Integer.parseInt(splitCommand[2]));
        String value = getValue(position);
        if(value.isEmpty()) {
            PRINT.add(EMPTY);
        }
        else {
            PRINT.add(value);
        }
    }
}