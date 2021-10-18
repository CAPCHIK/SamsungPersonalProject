package ru.capchik.personalproject.mappers;

import ru.capchik.personalproject.azureDevOpsModels.FullDefinitionResponse;
import ru.capchik.personalproject.models.FullDefinitionInfo;

public class FullDefinitionInfoMapper {
    public static FullDefinitionInfo map(FullDefinitionResponse response) {
        return new FullDefinitionInfo(
                response.getAuthoredBy().get_links().getAvatar().getHref(),
                response.getAuthoredBy().getDisplayName(),
                response.getRepository().getProperties().getOwnerAvatarUrl(),
                response.getRepository().getProperties().getSafeRepository(),
                response.getRepository().getProperties().getDefaultBranch(),
                response.getPath(),
                response.getCreatedDate(),
                response.get_links().getWeb().getHref(),
                CompactBuildInfoMapper.map(response.getLatestBuild()),
                CompactBuildInfoMapper.map(response.getLatestCompletedBuild())
        );
    }
}
