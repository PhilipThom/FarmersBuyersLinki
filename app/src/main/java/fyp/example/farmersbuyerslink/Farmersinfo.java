package fyp.example.farmersbuyerslink;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


import fyp.example.farmersbuyerslink.Model.FarmersUploads;
import fyp.example.farmersbuyerslink.Model.Users;

public class Farmersinfo extends ArrayAdapter<Users> {
    private Activity context;

    private List<Users> usersList;

    public Farmersinfo(Activity context,List<Users>usersList){
        super(context,R.layout.list_view,usersList);
        this.context=context;
        this.usersList=usersList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listView=inflater.inflate(R.layout.list_view,null,true);

        TextView name=(TextView)listView.findViewById(R.id.farmer_textview_name);
        TextView phone=(TextView)listView.findViewById(R.id.farmer_textview_phone);
        TextView cty=(TextView)listView.findViewById(R.id.farmer_textview_city);
        TextView quantity=(TextView)listView.findViewById(R.id.farmer_textview_quantity);
        TextView type=(TextView)listView.findViewById(R.id.farmer_textview_type);

        Users users=usersList.get(position);

        name.setText(users.getName());
        phone.setText(users.getPhone());
        cty.setText(users.getCty());
        quantity.setText(users.getQuantity());
        type.setText(users.getType());


        return listView;
    }
}
