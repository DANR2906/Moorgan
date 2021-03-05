package com.moorgan.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moorgan.R;
import com.moorgan.Model.Job;
import com.moorgan.Model.Status;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private final Context context;

    private List<Job> jobs;

    /**
     * Class constructor
     * @param context
     * @param jobs
     */
    public JobAdapter(Context context, List<Job> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.job_item_1, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Job job = jobs.get(position);

            holder.title.setText(job.getName());


            holder.arrow.setOnClickListener(v -> {

                changeView(holder);



                /*
                holder.creationDate.setText(job.getCreationDate().toString());
                holder.endDate.setText(job.getEndDate().toString());
                holder.payment.setText(String.valueOf(job.getPayment()));

                holder.entryDate.setText(job.getBalanceHistory()
                        .get(job.getBalanceHistory().size()-1)
                        .getEntryDate()
                        .toString());

                holder.entryDateAmount.setText(String.valueOf(
                        job.getBalanceHistory()
                                .get(job.getBalanceHistory().size()-1)
                                .getAmount()));

                int clients = job.getClients().size();

                holder.numberOfClients.setText("---" + (clients-1) + "+---");

                holder.status.setText(getCurrentStatus(job).getName());

                 */

            });


    }



    @Override
    public int getItemCount() {
        return jobs.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    /**
     *
     * @param job
     * @return
     */
    private Status getCurrentStatus(Job job){
        Status rStatus = new Status();

        int auxId = 999999999;

        for (Status status : job.getStatus()){

            if(! (status.getApproved() == 1) )
                if(status.getId() <= auxId) {
                    auxId = status.getId();
                    rStatus = status;
                }

        }

        return rStatus;

    }

    /**
     *
     * @param holder
     */
    private void changeView(ViewHolder holder){

        if(!holder.isExpanded()){
            holder.setExpanded(true);
            holder.arrow.setImageResource(R.drawable.ic_arrow_up);
            holder.relativeLayoutTop.setBackgroundColor(context.getResources().getColor(R.color.moorgan_red));
            holder.expandableLinearLayout.setVisibility(View.VISIBLE);
        }else{
            holder.setExpanded(false);
            holder.arrow.setImageResource(R.drawable.ic_arrow_down);
            holder.relativeLayoutTop.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.expandableLinearLayout.setVisibility(View.GONE);
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout relativeLayoutTop;
        public TextView title;
        public ImageButton arrow;

        public LinearLayout expandableLinearLayout;
        public TextView status;
        public TextView creationDate;
        public TextView endDate;
        public TextView clientName;
        public TextView numberOfClients;
        public TextView entryDateAmount;
        public TextView entryDate;
        public TextView payment;



        public boolean isExpanded = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayoutTop = itemView.findViewById(R.id.relativelayout_top);

            title = itemView.findViewById(R.id.job_title);
            arrow = itemView.findViewById(R.id.arrow);

            expandableLinearLayout = itemView.findViewById(R.id.expandable_linearlayout);
            expandableLinearLayout.setVisibility(View.GONE);

            status = itemView.findViewById(R.id.status_text);
            creationDate = itemView.findViewById(R.id.creation_date_text);
            endDate = itemView.findViewById(R.id.end_date_text);
            clientName = itemView.findViewById(R.id.name_client);
            numberOfClients = itemView.findViewById(R.id.number_clients);
            entryDateAmount = itemView.findViewById(R.id.entry_date_amount);
            entryDate = itemView.findViewById(R.id.entry_date_text);
            payment = itemView.findViewById(R.id.payment_text);



        }




        public boolean isExpanded() {
            return isExpanded;
        }

        public void setExpanded(boolean expanded) {
            isExpanded = expanded;
        }

    }



}
