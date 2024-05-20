package tech.ada.banco.enums;

public enum StatusEnum {
    ATIVO(1), INATIVO(2);

    private final Integer id;

    StatusEnum(int i) {
        id = i;
    }
}
