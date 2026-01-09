import VideoDetailsInterface from "@/common/interfaces/videoDetails";
import VideoCard from "@/components/VideoCard/VideoCard";
import axios from "axios";

const RecomendationList = async (): Promise<React.ReactElement> => {
  const response = await axios.get(`${process.env.HOST_URL}/api/v1/video`);
  const RecomendationList: VideoDetailsInterface[] = response.data;
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
            videoData={videoData}
          />
        );
      })}
    </div>
  );
};

export default RecomendationList;
