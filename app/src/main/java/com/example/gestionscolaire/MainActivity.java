package com.example.gestionscolaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* Define the UI elements */
    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private int counter = 5;

    String userName = "";
    String userPassword = "";

    /* Class to hold credentials */
    class Credentials
    {
        String name = "admin";
        String password = "abcdef";
    }

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.pssft);
        eLogin = findViewById(R.id.btnLog);


        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();


                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(MainActivity.this, "Veullez remplir tous les champs SVP !", Toast.LENGTH_LONG).show();

                }else {


                    isValid = validate(userName, userPassword);

                    /* Validate the user inputs */

                    /* If not valid */
                    if (!isValid) {

                        /* Decrement the counter */
                        counter--;

                        /* Show the attempts remaining */
                        eAttemptsInfo.setText("Attempts Remaining: " + String.valueOf(counter));

                        /* Disable the login button if there are no attempts left */
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(MainActivity.this, "Vous avez echoué à plusieurs tentative, veuillez réessayer !", Toast.LENGTH_LONG).show();
                        }
                        /* Display error message */
                        else {
                            Toast.makeText(MainActivity.this, "Incorrecte, réessayer plus tard!", Toast.LENGTH_LONG).show();
                        }
                    }
                    /* If valid */
                    else {

                        /* Allow the user in to your app by going into the next activity */
                        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                    }

                }
            }
        });
    }

    /* Validate the credentials */
    private boolean validate(String userName, String userPassword)
    {
        /* Get the object of Credentials class */
        Credentials credentials = new Credentials();

        /* Check the credentials */
        if(userName.equals(credentials.name) && userPassword.equals(credentials.password))
        {
            return true;
        }

        return false;
    }
}