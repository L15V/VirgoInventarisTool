package virgo.larsverhulst.nl.virgoinventaristool.Adapters;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import virgo.larsverhulst.nl.virgoinventaristool.Dialogs.EditRyclerPopup;
import virgo.larsverhulst.nl.virgoinventaristool.MainActivity;
import virgo.larsverhulst.nl.virgoinventaristool.R;
import virgo.larsverhulst.nl.virgoinventaristool.Util.InvItem;

public class InvItemRViewAdapter extends RecyclerView.Adapter<InvItemRViewAdapter.InvViewHolder> {
    private List<InvItem> invItems;

    private MainActivity mainActivity;
    private Context context;

    private EditRyclerPopup editItemDialog;

    public static class InvViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView amountCrates;
        public TextView amountBottles;

        public View view;

        public InvViewHolder(@NonNull View itemView){
            super(itemView);

            this.view = itemView.findViewById(R.id.CardItem);

            this.title = itemView.findViewById(R.id.cardView_title);
            this.amountCrates = itemView.findViewById(R.id.cardView_amountCrates);
            this.amountBottles = itemView.findViewById(R.id.cardView_amountBottles);
        }
    }

    public InvItemRViewAdapter(Application application, MainActivity ma, Context co){
        this.invItems = new ArrayList<>();
        this.mainActivity = ma;
        this.context = co;

        editItemDialog = new EditRyclerPopup(context, mainActivity);
    }

    @NonNull
    @Override
    public InvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);

        return new InvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InvViewHolder holder, final int position){
        InvItem item = invItems.get(position);

        holder.title.setText(item.getNameOfDrink());
        holder.amountCrates.setText(Integer.toString(item.getCrates()));
        holder.amountBottles.setText(Integer.toString(item.getBottles()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItemDialog.ShowPopup(position);
            }
        });

    }

    @Override
    public int getItemCount(){
        return invItems.size();
    }

    public void update(List<InvItem> items){
        this.invItems.clear();
        this.invItems.addAll(items);
    }


}
