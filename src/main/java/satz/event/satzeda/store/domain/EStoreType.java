package satz.event.satzeda.store.domain;

public enum EStoreType {
    KOREAN("KOREAN"),
    ITALIAN("ITALIAN"),
    CHINESE("CHINESE"),
    JAPANESE("JAPANESE"),
    BBQ("BBQ")

    ;

    private final String type;

    EStoreType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EStoreType convert(String type) {
        for(EStoreType Type : EStoreType.values()) {
            if (Type.getType().equals(type)) return Type;
        }
        throw new IllegalArgumentException("가게 타입 지정 에러 발생");
    }
}
