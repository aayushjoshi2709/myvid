import VideoCard from "@/components/VideoCard/VideoCard";
import DummyData from "@/constants/DummyData";

const RecomendationList = (): React.ReactElement => {
  return (
    <div className="flex flex-col">
      {DummyData.map((videoData, idx) => {
        return (
          <VideoCard
            key={idx}
            thumbnailStyle={{
              size: "80%",
              width: "100px",
              minWidth: "150px",
            }}
            videoInfoAlignment="horizontal"
            hideChannelLogo={true}
            {...videoData}
          />
        );
      })}
    </div>
  );
};

export default RecomendationList;
