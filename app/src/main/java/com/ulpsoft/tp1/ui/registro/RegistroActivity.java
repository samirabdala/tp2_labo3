package com.ulpsoft.tp1.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulpsoft.tp1.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegistroViewModel vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");

        if (usuario != null) {
            vm.accion(usuario);
        }

        vm.getMUsuario().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etMail.setText(string);
            }
        });

        vm.getmPass().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etPassword.setText(string);
            }
        });

        vm.getmApellido().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etApellido.setText(string);
            }
        });

        vm.getmNombre().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etNombre.setText(string);
            }
        });

        vm.getmDni().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long dni) {
                if (dni != null) {
                    binding.etDni.setText(dni.toString());
                } else {
                    binding.etDni.setText("");
                }
            }
        });



        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = binding.etMail.getText().toString();
                String password = binding.etPassword.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                Long dni = null;
                try {
                    dni = Long.parseLong(binding.etDni.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(RegistroActivity.this, "El DNI debe ser numérico", Toast.LENGTH_SHORT).show();

                }

                if (!usuario.isEmpty() && !password.isEmpty() && !apellido.isEmpty() && !nombre.isEmpty() && dni != null){
                    vm.registroUsuario(usuario, password, apellido, nombre, dni);
                } else {
                    Toast.makeText(RegistroActivity.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }




}