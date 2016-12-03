package net.therap.converter;

import net.therap.command.Status;

import javax.net.ssl.SSLEngineResult;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author safat
 * @since 12/2/16.
 */
@Converter // autoApply = false by default
public class StatusConverter implements AttributeConverter<Integer, Status> {

    @Override
    public Status convertToDatabaseColumn(Integer id) {
        return Status.getStatusById(id);
    }

    @Override
    public Integer convertToEntityAttribute(Status status) {
        return Status.getIdByStatus(status);
    }
}
