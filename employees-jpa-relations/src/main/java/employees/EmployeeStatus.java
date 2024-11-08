package employees;

import java.util.Arrays;

public enum EmployeeStatus {

    NEW("N"), ACCEPTED("A");

    private final String code;

    EmployeeStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EmployeeStatus byCode(String code) {
        return Arrays.stream(EmployeeStatus.values())
                        .filter(status -> status.getCode().equals(code))
                        .findAny().orElseThrow();
    }
}
