package com.example.asm1_and103_ph41980.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asm1_and103_ph41980.R;
import com.example.asm1_and103_ph41980.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignupFragment extends Fragment {



    public SignupFragment() {
        // Required empty public constructor
    }

    FragmentSignupBinding binding;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance();
        binding.edEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    valiEmail();
                }
            }
        });
        binding.edPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    valiPass();
                }
            }
        });

        binding.edRePass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    valiRePass();
                }
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTaiKhoan = binding.edEmail.getText().toString();
                String matKhau = binding.edPass.getText().toString();
                String nhapLaiMatKhau = binding.edRePass.getText().toString();
                if (validateForm()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đúng thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(tenTaiKhoan,matKhau)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        binding.edEmail.setText("");
                                        binding.edPass.setText("");
                                        binding.edRePass.setText("");
                                        Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.w("hehehe", task.getException());
                                        Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });

        return binding.getRoot();
    }

    private boolean validateForm() {

        if (binding.edEmail.getText().toString() == null &&
                binding.edPass.getText().toString() == null &&
                binding.edRePass.getText().toString() == null){
            return true;
        }else {
            return false;
        }

    }

    private void valiEmail() {
        if (binding.edEmail.getText().toString().isEmpty()) {
            binding.email.setError("Vui lòng không để trống email");
        } else if (!binding.edEmail.getText().toString().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            binding.email.setError("Vui lòng nhập đúng định dạng email");
        } else {
            binding.email.setError(null);
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

    private void valiRePass() {
        if (binding.edRePass.getText().toString().isEmpty()) {
            binding.rePass.setError("Vui lòng không để trống mật khẩu");
        } else if (binding.edRePass.getText().toString().length() <6) {
            binding.rePass.setError("Mật khẩu phải lớn hơn 5 ký tự");
        } else if (!binding.edRePass.getText().toString().equals(binding.edPass.getText().toString())) {
            binding.rePass.setError("Mật khẩu không trùng nhau");
        }   else {
            binding.rePass.setError(null);
        }
    }
}