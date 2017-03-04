package es.juandavidvega.dto.output;

public enum OperationStatus {
    START(1), SUCCESS(2), ERROR(3);

    private final Integer operationId;

    OperationStatus(Integer operationId) {

        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return operationId;
    }
}
