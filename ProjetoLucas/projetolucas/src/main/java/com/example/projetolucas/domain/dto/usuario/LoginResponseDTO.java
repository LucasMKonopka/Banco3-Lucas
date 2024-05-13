package com.example.projetolucas.domain.dto.usuario;

public class LoginResponseDTO {
    private String token;
    private UsuarioResponseDTO usuarioResponseDTO;

    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UsuarioResponseDTO getUsuarioResponseDTO() {
        return usuarioResponseDTO;
    }
    public void setUsuarioResponseDTO(UsuarioResponseDTO usuarioResponseDTO) {
        this.usuarioResponseDTO = usuarioResponseDTO;
    }

}
