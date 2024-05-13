package com.example.projetolucas.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.projetolucas.common.ConversorData;
import com.example.projetolucas.domain.dto.usuario.LoginRequestDTO;
import com.example.projetolucas.domain.dto.usuario.LoginResponseDTO;
import com.example.projetolucas.domain.dto.usuario.UsuarioResponseDTO;
import com.example.projetolucas.domain.model.ErroResposta;
import com.example.projetolucas.domain.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth");
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        try{
            LoginRequestDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        }catch(BadCredentialsException ex){
            throw new BadCredentialsException("Usuário ou senha inválidos!");
        }catch(Exception ex){
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
        Usuario usuario = (Usuario) authResult.getPrincipal();
        String token = jwtUtil.gerarToken(authResult);
        UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setFoto(usuario.getFoto());
        usuarioResponse.setDataCadastro(usuario.getDataCadastro());
        usuarioResponse.setDataInativacao(usuario.getDataInativacao());

        LoginResponseDTO loginresponseDTO = new LoginResponseDTO();
        loginresponseDTO.setToken("Bearer "+token);
        loginresponseDTO.setUsuarioResponseDTO(usuarioResponse);
        response.setCharacterEncoding("UTF - 8");
        response.setContentType("aplication/json");
        response.getWriter().write(new Gson().toJson(loginresponseDTO));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException{

        String dataHora = ConversorData.converterDateParaDataHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora, 401, "Unauthorized", failed.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(erro));
    }

}
