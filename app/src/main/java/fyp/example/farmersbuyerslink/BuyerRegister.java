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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BuyerRegister extends AppCompatActivity {
    private Button JoinNow;
    private EditText UserName;
    private EditText PhoneNumber;
    private EditText Password;
    private EditText City;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);

        JoinNow=(Button) findViewById(R.id.register_btn);
        PhoneNumber=(EditText) findViewById(R.id.register_phone_number);
        UserName=(EditText) findViewById(R.id.register_username);
        Password=(EditText) findViewById(R.id.register_password);
        City=(EditText) findViewById(R.id.register_city);
        loading=new ProgressDialog(this);

        JoinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinNow();
            }
        });
    }



    private void JoinNow() {

        String name=UserName.getText().toString();
        String password=Password.getText().toString();
        String cty=City.getText().toString();
        String phone=PhoneNumber.getText().toString();

        if(TextUtils.isEmpty(name)){

            Toast.makeText(this,"Please Write Your Name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Phone Number is required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"This Filled Can't be Empty",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cty)){
            Toast.makeText(this,"Please Provide City Name",Toast.LENGTH_SHORT).show();
        }
        else{
            loading.setTitle("Signing Up");
            loading.setMessage("Checking The Inputs, Please Wait");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            PhoneNumberValidation(name,phone,cty,password);
        }


    }

    private void PhoneNumberValidation(final String name, final String phone,final String cty,final String password) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if ((!dataSnapshot.child("Buyers").child(phone).exists())){

                    HashMap<String,Object> userdatamap=new HashMap<>();
                    userdatamap.put("phone",phone);
                    userdatamap.put("password",password);
                    userdatamap.put("name",name);
                    userdatamap.put("cty",cty);

                    RootRef.child("Buyers").child(phone).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(BuyerRegister.this,"You Have Joined Succefully",Toast.LENGTH_SHORT).show();
                                loading.dismiss();


                                Intent intent=new Intent(BuyerRegister.this,BuyerLoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                loading.dismiss();
                                Toast.makeText(BuyerRegister.this,"Something Went Wrong, Try Again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                else{

                    Toast.makeText(BuyerRegister.this,"Sorry The Phone Number "+phone+" Already in Use",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    Toast.makeText(BuyerRegister.this,"Try Another Phone Number",Toast.LENGTH_SHORT).show();


                    Intent intent=new Intent(BuyerRegister.this,MainActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
