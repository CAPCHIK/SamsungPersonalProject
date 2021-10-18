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
            CompactBuildInfo compactBuildInfo = new CompactBuildInfo(
                    br.getDefinition().getId(),
                    br.getResult().equals("succeeded"),
                    br.getDefinition().getName(),
                    br.getBuildNumber(),
                    br.getTriggerInfo().getCiMessage(),
                    br.getFinishTime()
            );
            result.add(compactBuildInfo);
        }
        return  result;
    }
}
