package com.example.asm1_and103_ph41980.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asm1_and103_ph41980.Activity.ListActivity;
import com.example.asm1_and103_ph41980.R;
import com.example.asm1_and103_ph41980.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {



    public LoginFragment() {
        // Required empty public constructor
    }
    FragmentLoginBinding binding;

    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance();
        binding.edPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    valiPass();
                }

            }
        });
        binding.edUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    valiEmail();
                }
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ListActivity.class));
//                String email = binding.edUsername.getText().toString();
//                String pass = binding.edPass.getText().toString();
//                if (!validateForm()) {
//                    Toast.makeText(getContext(), "Vui lòng nhập đúng thông tin", Toast.LENGTH_SHORT).show();
//                } else {
//                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getActivity(), ListActivity.class));
//                            } else {
//                                Log.w("hehehe", task.getException());
//                                Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
            }
        });


        return binding.getRoot();
    }

    private boolean validateForm() {

        if (binding.username.getError() == null && binding.pass.getError() == null){
            return true;
        }else {
            return false;
        }
    }

    private void valiEmail() {
        if (binding.edUsername.getText().toString().isEmpty()) {
            binding.username.setError("Vui lòng không để trống email");
        } else if (!binding.edUsername.getText().toString().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            binding.username.setError("Vui lòng nhập đúng định dạng email");
        }  else {
            binding.username.setError(null);
        }
    }
    private void valiPass() {
        if (binding.edPass.getText().toString().isEmpty()) {
            binding.pass.setError("Vui lòng không để trống mật khẩu");
        } else if (binding.edPass.getText().toString().length() <6) {
            binding.pass.setError("Mật khẩu phải lớn hơn 5 ký tự");
        }  else {
            binding.pass.setError(null);
        }
    }
}