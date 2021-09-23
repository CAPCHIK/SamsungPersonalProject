package ru.capchik.personalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        BuildResponse build = data.getValue().get(position);

        holder.getBuildSuccessImageView().setImageResource(
                build.getResult().equals("succeeded") ?
                    R.drawable.build_success :
                    R.drawable.build_failed);
        holder.getTitleTextView().setText(build.getDefinition().getName());
        holder.getBuildId().setText(build.getBuildNumber());
        holder.getCommitMessage().setText(build.getTriggerInfo().getCiMessage());
    }

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView buildSuccessImageView;
        private final TextView titleTextView;
        private final TextView buildId;
        private final TextView commitMessage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            buildSuccessImageView = view.findViewById(R.id.success_image);
            titleTextView = view.findViewById(R.id.definition_name);
            buildId = view.findViewById(R.id.build_id);
            commitMessage = view.findViewById(R.id.commit_message);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getBuildId() {
            return buildId;
        }

        public ImageView getBuildSuccessImageView() {
            return buildSuccessImageView;
        }

        public TextView getCommitMessage() {
            return commitMessage;
        }
    }
}
