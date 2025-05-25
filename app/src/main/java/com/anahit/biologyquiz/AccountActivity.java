package com.anahit.biologyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    private TextInputEditText currentPassword, newPassword, confirmPassword;
    private Button changePasswordButton, logoutButton, deleteAccountButton;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Add exit button click listener
        ImageButton exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> finish());

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Check if user is logged in
        if (currentUser == null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Login.class));
            finish();
            return;
        }

        // Initialize views
        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        logoutButton = findViewById(R.id.logoutButton);
        deleteAccountButton = findViewById(R.id.deleteAccountButton);

        // Set click listeners
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String currentPass = currentPassword.getText() != null ? currentPassword.getText().toString().trim() : "";
                    String newPass = newPassword.getText() != null ? newPassword.getText().toString().trim() : "";
                    String confirmPass = confirmPassword.getText() != null ? confirmPassword.getText().toString().trim() : "";
                    
                    if (currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                        Toast.makeText(AccountActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!newPass.equals(confirmPass)) {
                        Toast.makeText(AccountActivity.this, "New passwords do not match", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (newPass.length() < 6) {
                        Toast.makeText(AccountActivity.this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Show loading state
                    changePasswordButton.setEnabled(false);
                    changePasswordButton.setText("Updating...");

                    // Reauthenticate user before changing password
                    AuthCredential credential = EmailAuthProvider.getCredential(currentUser.getEmail(), currentPass);
                    currentUser.reauthenticate(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Change password
                                currentUser.updatePassword(newPass)
                                    .addOnCompleteListener(passwordTask -> {
                                        // Reset button state
                                        changePasswordButton.setEnabled(true);
                                        changePasswordButton.setText("Update Password");
                                        
                                        if (passwordTask.isSuccessful()) {
                                            Toast.makeText(AccountActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                                            // Clear the input fields
                                            currentPassword.setText("");
                                            newPassword.setText("");
                                            confirmPassword.setText("");
                                        } else {
                                            String errorMessage = passwordTask.getException() != null ? 
                                                passwordTask.getException().getMessage() : "Failed to update password";
                                            Toast.makeText(AccountActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            } else {
                                // Reset button state
                                changePasswordButton.setEnabled(true);
                                changePasswordButton.setText("Update Password");
                                
                                String errorMessage = task.getException() != null ? 
                                    task.getException().getMessage() : "Current password is incorrect";
                                Toast.makeText(AccountActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        });
                } catch (Exception e) {
                    // Reset button state
                    changePasswordButton.setEnabled(true);
                    changePasswordButton.setText("Update Password");
                    Toast.makeText(AccountActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mAuth.signOut();
                    Toast.makeText(AccountActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                    
                    // Navigate to login screen
                    Intent intent = new Intent(AccountActivity.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(AccountActivity.this, "Error during logout: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AlertDialog.Builder(AccountActivity.this)
                        .setTitle("Delete Account")
                        .setMessage("Are you sure you want to delete your account? This action cannot be undone.")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            if (currentUser != null) {
                                currentUser.delete()
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(AccountActivity.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(AccountActivity.this, Login.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            String errorMessage = task.getException() != null ? 
                                                task.getException().getMessage() : "Failed to delete account";
                                            Toast.makeText(AccountActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                } catch (Exception e) {
                    Toast.makeText(AccountActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
} 