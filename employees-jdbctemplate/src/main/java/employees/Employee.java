package employees;

public record Employee(Long id, String name) {

    public Employee(String name) {
        this(null, name);
    }
}
