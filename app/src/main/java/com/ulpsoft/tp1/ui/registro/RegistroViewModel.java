package com.ulpsoft.tp1.ui.registro;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ulpsoft.tp1.model.Usuario;
import com.ulpsoft.tp1.request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {
    public MutableLiveData<String> mUsuario = new MutableLiveData<>();
    public MutableLiveData<String> mPass = new MutableLiveData<>();
    public MutableLiveData<Long> mDni = new MutableLiveData<>();
    public MutableLiveData<String> mApellido = new MutableLiveData<>();
    public MutableLiveData<String> mNombre = new MutableLiveData<>();
    public ApiClient ac;
    public Context contexto;

    public RegistroViewModel(@NonNull Application application) {

        super(application);
        contexto = application.getApplicationContext();
        ac = new ApiClient();

    }

    public MutableLiveData<String> getMUsuario() {
        if (mUsuario == null) {
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public MutableLiveData<Long> getmDni() {
        if (mDni == null) {
            mDni = new MutableLiveData<>();
        }
        return mDni;
    }

    public MutableLiveData<String> getmApellido() {
        if (mApellido == null) {
            mApellido = new MutableLiveData<>();
        }
        return mApellido;
    }

    public MutableLiveData<String> getmNombre() {
        if (mNombre == null) {
            mNombre = new MutableLiveData<>();
        }
        return mNombre;
    }

    public MutableLiveData<String> getmPass() {
        if (mPass == null) {
            mPass = new MutableLiveData<>();
        }
        return mPass;
    }

    public void accion(String usuario){
        if (usuario != null) {
            inicioUsuario();
        }
    }

    public void inicioUsuario(){
            Usuario usu =  ApiClient.registrado(contexto);
            mUsuario.setValue(usu.getMail());
            mPass.setValue(usu.getPassword());
            mDni.setValue(usu.getDni());
            mApellido.setValue(usu.getApellido());
            mNombre.setValue(usu.getNombre());

    }

    public void registroUsuario(String usu,String password,String apellido,String nombre,Long dni){
        Usuario usuario = new Usuario(dni,  nombre,  apellido, usu,  password);
        if(ApiClient.guardarUsuario(contexto, usuario)){
            Toast.makeText(contexto, "Usuario registrado", Toast.LENGTH_SHORT).show();
            mUsuario.setValue("");
            mPass.setValue("");
            mDni.setValue(null);
            mApellido.setValue("");
            mNombre.setValue("");
        }
    }
}
