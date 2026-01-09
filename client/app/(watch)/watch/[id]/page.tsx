import DescriptionCard from "@/components/DescriptionCard/DescriptionCard";
import RecomendationList from "@/components/RecomendationList/RecomendationList";
import RoundedImage from "@/components/RoundedImage/RoundedImage";
import VideoDetailsInterface from "@/common/interfaces/VideoDetails";
import axios from "axios";
import MediaPlayerWrapper from "@/components/MediaPlayer/MediaPlayer";
const WatchPage = async ({
  params,
}: {
  params: Promise<{ id: string }>;
}): Promise<React.ReactElement> => {
  const { id } = await params;
  const response = await axios.get(
    `${process.env.HOST_URL}/api/v1/video/${id}`
  );
  const currentlyWatching: VideoDetailsInterface = response.data;
  console.log(currentlyWatching);

  return (
    <div className="flex flex-col lg:flex-row p-6 gap-4">
      <div id="video-comments" className="w-full lg:w-8/12 2xl:w-9/12">
        <MediaPlayerWrapper
          src={currentlyWatching.videoUrl}
          poster={currentlyWatching.thumbnailUrl}
        />
        <h2 className="my-2 text-2xl">{currentlyWatching.title}</h2>
        <div className="flex flex-row gap-4 items-center">
          <RoundedImage
            style={{
              width: "3rem",
            }}
            imageUrl="/hq720.jpg"
          />
          <div className="flex flex-col mr-4">
            <p className="text-sm">
              <strong>{currentlyWatching.createdBy.username}</strong>
            </p>
            <p className="text-sm">Subs Count</p>
          </div>
          <button className="p-1 px-4 border border-gray-300 rounded-full font-semibold">
            Subscribe
          </button>
        </div>
        <DescriptionCard {...currentlyWatching} />
      </div>
      <div className="w-full lg:w-4/12 2xl:w-3/12">
        <RecomendationList />
      </div>
    </div>
  );
};

export default WatchPage;
