package ru.capchik.personalproject.mappers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.stream.Stream;

import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;
import ru.capchik.personalproject.models.CompactBuildInfo;

public class CompactBuildInfoMapper {

    public static ArrayList<CompactBuildInfo> map(ApiListResponse<BuildResponse> response){
        ArrayList<CompactBuildInfo> result = new ArrayList<>();
        for (BuildResponse br : response.getValue()) {
            result.add(map(br));
        }
        return  result;
    }
    public static CompactBuildInfo map(BuildResponse buildResponse) {
        return  new CompactBuildInfo(
                buildResponse.getId(),
                buildResponse.getDefinition().getId(),
                buildResponse.getResult().equals("succeeded"),
                buildResponse.getDefinition().getName(),
                buildResponse.getBuildNumber(),
                buildResponse.getTriggerInfo().getCiMessage(),
                buildResponse.getFinishTime()
        );
    }
}
