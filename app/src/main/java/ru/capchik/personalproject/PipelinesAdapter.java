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

    public interface OnClickListener {
        void Click(BuildResponse build);
    }

    private final ApiListResponse<BuildResponse> data;
    private final OnClickListener onClickListener;

    public PipelinesAdapter(ApiListResponse<BuildResponse> data, OnClickListener onClickListener) {
        this.data = data;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pipeline_item, parent, false);

        return new ViewHolder(onClickListener, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BuildResponse build = data.getValue().get(position);

        holder.setBuildResponse(build);
        holder.getBuildSuccessImageView().setImageResource(
                build.getResult().equals("succeeded") ?
                    R.drawable.build_success :
                    R.drawable.build_failed);
        holder.getTitleTextView().setText(build.getDefinition().getName());
        holder.getBuildId().setText(build.getBuildNumber());
        holder.getCommitMessage().setText(build.getTriggerInfo().getCiMessage());
        holder.getFinishedDate().setText(build.getPrettyFinishTime());
    }

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final OnClickListener onClickListener;
        private BuildResponse buildResponse;

        private final ImageView buildSuccessImageView;
        private final TextView titleTextView;
        private final TextView buildId;
        private final TextView commitMessage;
        private final TextView finishedDate;

        public ViewHolder(OnClickListener onClickListener, View view) {
            super(view);
            this.onClickListener = onClickListener;

            view.setOnClickListener(view1 -> this.onClickListener.Click(buildResponse));

            buildSuccessImageView = view.findViewById(R.id.success_image);
            titleTextView = view.findViewById(R.id.definition_name);
            buildId = view.findViewById(R.id.build_id);
            commitMessage = view.findViewById(R.id.commit_message);
            finishedDate = view.findViewById(R.id.build_finish_date);
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

        public void setBuildResponse(BuildResponse buildResponse) {
            this.buildResponse = buildResponse;
        }

        public TextView getFinishedDate() {
            return finishedDate;
        }
    }
}
