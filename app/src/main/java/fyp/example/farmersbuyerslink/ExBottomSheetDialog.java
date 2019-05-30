package fyp.example.farmersbuyerslink;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ExBottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet_layout,container,false);

        Button button1=view.findViewById(R.id.sheet_btn1);
        Button button2=view.findViewById(R.id.sheet_btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),BuyerRegister.class));
                dismiss();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(getActivity(),Main2Activity.class);
                startActivity(new Intent(getActivity(),RegisterActivity.class));
                dismiss();

            }
        });


        return view;
    }

    public interface BottomSheetListener{
        void onButtonClicked(String text);
    }
}
