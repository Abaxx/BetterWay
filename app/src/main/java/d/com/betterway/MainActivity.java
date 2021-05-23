package d.com.betterway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        String[] categories = {"FAMILY","FRIEND","COLLEAGUE","TUTORS","STUDENTS"};
        mAdapter = new myAdapter(this,categories);
        recyclerView.setAdapter(mAdapter);
    }

    public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder>{

        String[] strings;
        Context context;

        public myAdapter(Context context, String[] strings){
            this.context = context;
            this.strings = strings;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView title;
            public View mylayout;
            public ViewHolder(View itemView){
                super(itemView);
                title = (TextView)itemView.findViewById(R.id.mytitle);
                mylayout = (ViewGroup)itemView.findViewById(R.id.mylayout);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View customView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder,parent,false);
            ViewHolder vh = new ViewHolder(customView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText(strings[position]);
            holder.mylayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(MainActivity.this,strings[position],Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,Contact.class);
                    intent.putExtra("title",strings[position]);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return strings.length;
        }
    }
}