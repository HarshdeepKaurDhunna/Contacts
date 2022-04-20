/**
 * @author A00246003
 */
package com.example.contacts;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList userId, userName, userContact;

    CustomAdapter(Activity activity, Context context, ArrayList userId, ArrayList userName, ArrayList userContact){
        this.activity = activity;
        this.context = context;
        this.userId = userId;
        this.userName = userName;
        this.userContact = userContact;

    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    /**
     * Class to show the list of conatcts
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView contactIdVal, contactName, contactTextVal;
        LinearLayout mainLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactIdVal = itemView.findViewById(R.id.contactIdVal);
            contactName = itemView.findViewById(R.id.contactName);
            contactTextVal = itemView.findViewById(R.id.contactTextVal);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }

    }
}
