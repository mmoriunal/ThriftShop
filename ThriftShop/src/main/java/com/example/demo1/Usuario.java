package com.example.demo1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Usuario {
    private int id_usuario;
    private String nombre;
    private String hashedPassword;
    private String profileImagePath;

    public Usuario(int id_usuario, String nombre, String password, String profileImagePath) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.hashedPassword = hashPassword(password);
        this.profileImagePath = profileImagePath;
    }

    public Usuario(int id_usuario, String nombre, String hashedPassword, String profileImagePath, boolean isHashed) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.hashedPassword = hashedPassword;
        this.profileImagePath = profileImagePath;
    }

    public Usuario(int id_usuario, String nombre, String hashedPassword, boolean isHashed) {
            this.id_usuario = id_usuario;
            this.nombre = nombre;
            this.hashedPassword = hashedPassword;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean verifyPassword(String password) {
        return this.hashedPassword.equals(hashPassword(password));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String toFileString() {
        return id_usuario + "," + nombre + "," + hashedPassword + "," + (profileImagePath != null ? profileImagePath : "");
    }
}
