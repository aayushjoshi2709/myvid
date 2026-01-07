import VideoDetailsInterface from "@/common/interfaces/VedioDetails";
import VideoCard from "@/components/VideoCard/VideoCard";

const RecomendationList = (): React.ReactElement => {
  const RecomendationList: VideoDetailsInterface[] = [];
  return (
    <div className="flex flex-col">
      {RecomendationList.map((videoData, idx) => {
        return (
          <VideoCard
            key={idx}
            thumbnailStyle={{
              size: "80%",
              width: "100px",
              minWidth: "150px",
            }}
            videoInfoAlignment="horizontal"
            hideUserIcon={true}
            {...videoData}
          />
        );
      })}
    </div>
  );
};

export default RecomendationList;
