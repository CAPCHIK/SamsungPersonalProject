package ru.capchik.personalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;

public class BuildItemFragment extends Fragment {

    private ImageView buildSuccessImageView;
    private TextView titleTextView;
    private TextView buildId;
    private TextView commitMessage;
    private TextView finishedDate;

    public BuildItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pipeline_item, container, false);
    }

    public void fillInfo(BuildResponse build) {
        buildSuccessImageView.setImageResource(
                build.getResult().equals("succeeded") ?
                        R.drawable.build_success :
                        R.drawable.build_failed);
        titleTextView.setText(build.getDefinition().getName());
        buildId.setText(build.getBuildNumber());
        commitMessage.setText(build.getTriggerInfo().getCiMessage());
        finishedDate.setText(build.getPrettyFinishTime());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildSuccessImageView = view.findViewById(R.id.success_image);
        titleTextView = view.findViewById(R.id.definition_name);
        buildId = view.findViewById(R.id.build_id);
        commitMessage = view.findViewById(R.id.commit_message);
        finishedDate = view.findViewById(R.id.build_finish_date);

    }

    public void hide() {

    }
}