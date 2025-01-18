package satz.event.satzeda.user.domain;

public enum ERole {
    USER("USER"),
    OWNER("OWNER"),
    RIDER("RIDER")
    ;
    private final String Role;

    ERole(String role) {
        Role = role;
    }

    public String getRole() {
        return Role;
    }

    public static ERole converter(String role) {
        for(ERole Role : ERole.values()) {
            if (Role.getRole().equals(role)) return Role;
        }
        throw new IllegalArgumentException("역할 이상함");
    }


}
