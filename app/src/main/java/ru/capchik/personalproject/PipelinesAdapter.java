package ru.capchik.personalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;

public class PipelinesAdapter extends RecyclerView.Adapter<PipelinesAdapter.ViewHolder> {

    private ApiListResponse<BuildResponse> data;

    public PipelinesAdapter(ApiListResponse<BuildResponse> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pipeline_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.getTitleTextView().setText(data.getValue().get(position).getDefinition().getName());
        holder.getResultTextView().setText(data.getValue().get(position).getResult());
    }

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView resultTextView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            titleTextView = view.findViewById(R.id.textView);
            resultTextView = view.findViewById(R.id.result_text_view);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getResultTextView() {
            return resultTextView;
        }
    }
}
