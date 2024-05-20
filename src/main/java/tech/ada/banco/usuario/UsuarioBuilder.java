package tech.ada.banco.usuario;




import tech.ada.banco.util.Pessoa;

public class UsuarioBuilder {
    private String email;
    private String cpf;
    private String username;
    private String password;
    private String telefone;
    private String roles;
    private boolean active;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;

    public UsuarioBuilder() {
        // Set default values if necessary
        this.active = true;
        this.accountExpired = false;
        this.accountLocked = false;
        this.credentialsExpired = false;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public UsuarioBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UsuarioBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UsuarioBuilder telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public UsuarioBuilder roles(String roles) {
        this.roles = roles;
        return this;
    }

    public UsuarioBuilder active(boolean active) {
        this.active = active;
        return this;
    }

    public UsuarioBuilder accountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
        return this;
    }

    public UsuarioBuilder accountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
        return this;
    }

    public UsuarioBuilder credentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
        return this;
    }

    public Usuario build() {
        return new Usuario(email, cpf, username, password, telefone, roles, active, accountExpired, accountLocked, credentialsExpired);
    }
}
