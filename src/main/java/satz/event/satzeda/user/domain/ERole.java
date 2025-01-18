package satz.event.satzeda.user.domain;

public enum ERole {
    USER("USER"),
    OWNER("OWNER"),
    RIDER("RIDER")
    ;
    private final String role;

    ERole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static ERole converter(String role) {
        for(ERole Role : ERole.values()) {
            if (Role.getRole().equals(role)) return Role;
        }
        throw new IllegalArgumentException("역할 이상함");
    }


}
