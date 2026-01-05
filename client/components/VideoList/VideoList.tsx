import VideoDetailsInterface from "@/common/interfaces/VedioDetails";
import VideoCard from "@/components/VideoCard/VideoCard";
import axios from "axios";
const VideoList = async (): Promise<React.ReactElement> => {
  const response = await axios.get(`${process.env.HOST_URL}/api/v1/vedio`);
  const videoData: VideoDetailsInterface[] = response.data;
  return (
    <div className="overflow-y-scroll">
      <div className="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 2xl:grid-cols-4 gap-4">
        {videoData.map((videoData: VideoDetailsInterface, idx: number) => {
          return (
            <VideoCard
              key={idx}
              vedioData={videoData}
              userIconStyle={{
                width: "3em",
                aspectRatio: "1/1",
              }}
            />
          );
        })}
      </div>
    </div>
  );
};

export default VideoList;
