package dankbox.roasthub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button buttonRoast;
    Button buttonWatch;
    Button buttonSubmit;
    EditText username;

    String sendName;
    String fileName = "usernames.txt";
    String testStr = "LOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //create username storage file if possible
        try {
            readStorage();
        }
        catch(IOException e){
            Toast.makeText(this, "Could not create file", Toast.LENGTH_SHORT).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            // Load chat room contents
            displayChatMessages();
        }*/

        username = (EditText)findViewById(R.id.username);
        buttonSubmit = (Button)findViewById(R.id.buttonSubmit);

        //once button is pressed the username is written in usernames.txt
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                try {
                    writeStorage(username.getText().toString());
                    readStorage();
                    //Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void displayChatMessages() {

    }

    /*add user method
    public void addUser(){


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }*/

    public boolean fileExistance(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public void createStorage() throws FileNotFoundException, IOException{

        //create usernames.txt
        File unameText = new File("username.txt");
        FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
        fos.write(testStr.getBytes());
        fos.close();

    }

    public void readStorage() throws IOException{

        //Secure way of opening file and reading
        FileInputStream fin = openFileInput(fileName);
        InputStreamReader isr = new InputStreamReader(fin);

        //char array that will store in read values
        ArrayList<Character> inputBuffer = new ArrayList<>();

        //read characters
        char c;
        int i;
        while((i = isr.read()) != -1){
            //int to char
            c = (char) i;

            //store char
            inputBuffer.add(c);
        }

        //create char array
        char[] readChars = new char[inputBuffer.size()];

        //ArrayList to char
        for(int j = 0; j < readChars.length; j++) {
            readChars[j] = inputBuffer.get(j);
        }

        //cast array into string
        String readString = new String(readChars);
        boolean isTheSame = testStr.equals(readString);

        Log.i("File Reading Stuff!!!", "equals = " + readString);
    }

    public void writeStorage(String str) throws IOException{

        //create usernames.txt
        File unameText = new File("username.txt");
        FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
        fos.write(str.getBytes());
        fos.close();

    }

    public void pressedRoast(View view){
        Intent roastIntent = new Intent (this, Roast.class);
        startActivity(roastIntent);
    }
    public void pressedWatch(View view){
        Intent messageIntent = new Intent (this, Messaging.class);
        startActivity(messageIntent);
    }
}
