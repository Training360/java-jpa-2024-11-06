package employees;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, String> {

    @Override
    public String convertToDatabaseColumn(EmployeeStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(String dbData) {
        return EmployeeStatus.byCode(dbData);
    }
}
