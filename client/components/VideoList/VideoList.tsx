import VideoCard from "@/components/VideoCard/VideoCard";
import DummyData from "@/constants/DummyData";
const VideoList = (): React.ReactElement => {
  return (
    <div className="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 2xl:grid-cols-4 gap-4">
      {DummyData.map((videoData, idx) => {
        return (
          <VideoCard
            key={idx}
            {...videoData}
            channelIconStyle={{
              width: "3em",
              aspectRatio: "1/1",
            }}
          />
        );
      })}
    </div>
  );
};

export default VideoList;
