package fyp.example.farmersbuyerslink;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fyp.example.farmersbuyerslink.Model.Buyers;
import fyp.example.farmersbuyerslink.Model.Users;
import fyp.example.farmersbuyerslink.Prevalent.Prevalent;

public class BuyerLoginActivity extends AppCompatActivity {

    private EditText Password;
    private EditText PhoneNumber;
    private Button LoginButton;
    private Button Logn;
    //private Button log;
    private ProgressDialog loading;
    private String parentDatabaseName="Buyers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_login);

        Password=(EditText) findViewById(R.id.login_password);
        LoginButton=(Button) findViewById(R.id.login_btn);
        PhoneNumber=(EditText) findViewById(R.id.login_phonenumber);
        //log=(Button) findViewById(R.id.login_bt) ;
        loading=new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBuyer();
            }
        });
    }

    private void LoginBuyer() {

        String phone=PhoneNumber.getText().toString();
        String password=Password.getText().toString();


        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Phone Number is required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"This Field Can't be Empty",Toast.LENGTH_SHORT).show();
        }
        else {
            loading.setTitle("Login Into Account");
            loading.setMessage("Checking The Inputs, Please Wait");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            PermitAccess(phone,password);
        }
    }


    private void PermitAccess(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(parentDatabaseName).child(phone).exists()){
                    Buyers userData=dataSnapshot.child(parentDatabaseName).child(phone).getValue(Buyers.class);

                    if(userData.getPhone().equals(phone)){
                        if(userData.getPassword().equals(password)){
                            Toast.makeText(BuyerLoginActivity.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                            loading.dismiss();

                            Intent intent=new Intent(BuyerLoginActivity.this,BuyerSeeUploads.class);

                            Prevalent.currentOnlineBuyer=userData;

                            startActivity(intent);

                        }
                        else {
                            loading.dismiss();
                            Toast.makeText(BuyerLoginActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(BuyerLoginActivity.this,"Account Not Exists",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    Toast.makeText(BuyerLoginActivity.this,"Create Account Instead",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
