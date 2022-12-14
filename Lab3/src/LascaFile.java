import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LascaFile<BoardPosition> 
{
    private PrintWriter writer;
    private Scanner scanner;
    private File gameFile;

    public void createNewSaveFile() 
    {
        gameFile = new File("game.dat");

        try {
            writer = new PrintWriter(gameFile);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to find game.dat");
        }
    }

    public File getGameFile() 
    {
        return gameFile;
    }

    public void saveMove(String moveAsCardinal) 
    {
        writer.println(moveAsCardinal);
    }

    public void closeWriter() 
    {
        writer.close();
    }

    public void openSaveFile() 
    {
        gameFile = new File("game.dat");

        try 
        {
            scanner = new Scanner(gameFile);
        } catch (FileNotFoundException e) 
        {
            System.out.println("Failed to find game.dat");
        }
    }

    public String loadNextMove() 
    {
        return scanner.nextLine();
    }

    public void closeScanner() 
    {
        scanner.close();
    }

    public <BoardSpace> BoardPosition convertToPosition(BoardPosition startingPosition, String cardinalDirection, LascaBoard board, BoardSpace currentPlayer)
    {
        
        int x = 0;
        int y = 0;
        switch (cardinalDirection) 
        {
            case "N" -> y = -1;
            case "NE" -> {
                y = -1;
                x = 1;
            }
            case "E" -> x = 1;
            case "SE" -> {
                y = 1;
                x = 1;
            }
            case "S" -> y = 1;
            case "SW" -> {
                y = 1;
                x = -1;
            }
            case "W" -> x = -1;
            case "NW" -> {
                y = -1;
                x = -1;
            }
        }
        
        return new BoardPosition(startingPosition.getRow()+y, startingPosition.getColumn()+x);
    }
}