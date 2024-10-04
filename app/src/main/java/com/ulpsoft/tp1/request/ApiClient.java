package com.ulpsoft.tp1.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ulpsoft.tp1.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar (Context contexto){
        if( sp == null){
            sp = contexto.getSharedPreferences("configuracion.xml", 0);
        }
        return sp;
    }

    public static boolean guardarUsuario(Context context, Usuario usuario){

        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("mail",usuario.getMail());
        editor.putString("password", usuario.getPassword());
        editor.apply();
        Log.d("ApiClient", "Usuario guardado: " + usuario.getMail());
        return true;
    }

    public static Usuario registrado(Context context){

        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String mail2 = sp.getString("mail","-1");
        String pass = sp.getString("password", "-1");
        Usuario usuario = new Usuario(dni, nombre, apellido, mail2, pass);

        return usuario;
    }


    public static Usuario login(Context context, String mail, String password){

        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String mail2 = sp.getString("mail","-1");
        String pass = sp.getString("password", "-1");

        if (mail.equals(mail2) && password.equals(pass)){
            usuario = new Usuario(dni, nombre, apellido, mail2, pass);
            return usuario;
        }

        Log.d("ApiClient", "Login fallido. No coincide el mail o password");
        return null;

    }


}
