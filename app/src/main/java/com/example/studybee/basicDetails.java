package com.example.studybee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONStringer;

public class basicDetails extends AppCompatActivity implements OnTaskCompleted{

    TextView tname;
    TextView tfaculty;
    TextView temail;
    String msgType;
    String username, sfacu, sname,smail;
    String status;
    String myuser = "lsy";// hard coded need to retrieve information from user profile
    Button btnfriend;
    int id1;

    // Set host address of the WAMP Server
    public static final String HOST = "10.27.240.3"; //use your IP address

    // Set virtual directory of the host website
    public static final String DIR = "myproject";

    // Set request ID for all HTTP requests
    private static final String REQ_DOWNLOAD = "1005";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);

        Bundle bn = getIntent().getExtras();
       // id2 = bn.getInt("id");
        username = bn.getString("user");
        sname = bn.getString("name");
        sfacu = bn.getString("facu");
        smail = bn.getString("email");
        tname = findViewById(R.id.textView13);
        tfaculty = findViewById(R.id.textView14);
        temail = findViewById(R.id.textView15);

        tname.setText(sname);
        temail.setText(smail);
        tfaculty.setText(sfacu);


        }
    public void SButtonOnClickHandler(View v){
        String jsonString = convertToJSON();
        //Toast.makeText(getApplicationContext(), "Successfully Added.", Toast.LENGTH_LONG).show();
        //access database network
        HttpAsyncTaskForLogin task = new HttpAsyncTaskForLogin(this);
        task.execute("http://" + HOST + "/" + DIR + "/addFriend.php", jsonString);
    }

    private String convertToJSON() {
        JSONStringer jsonText = new JSONStringer();
        try {

            jsonText.object();
            jsonText.key("type");
            jsonText.value(msgType);
            jsonText.key("user1");
            jsonText.value(myuser);
            jsonText.key("user2");
            jsonText.value(username);
            jsonText.endObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText.toString();
    }
   public void retrieveFromJSON(String message) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            msgType = jsonObject.getString("type");
            if (msgType.equals(REQ_DOWNLOAD)) {
                status = jsonObject.getString("status");
                if (status.equals("OK")) {
                    id1 = jsonObject.getInt("id1");
                    myuser= jsonObject.getString("user1");
                    username = jsonObject.getString("user2");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(String response) {
        retrieveFromJSON(response);
        btnfriend = findViewById(R.id.button13);
        //if ((msgType.equals(REQ_DOWNLOAD))  status.equals("OK")) {
            btnfriend.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Successfully Added.", Toast.LENGTH_LONG).show();
       // }
        //else {
           // Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            return;
    //}
}}