package network2;

public class Cell {
    private static Long idCounter = 1L;
    private Long id;

    public Cell(){
        id = idCounter++;
    }

    public Long getId() {
        return id;
    }
}
