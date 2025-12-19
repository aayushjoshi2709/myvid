import DescriptionCard from "@/components/DescriptionCard/DescriptionCard";
import RecomendationList from "@/components/RecomendationList/RecomendationList";
import RoundedImage from "@/components/RoundedImage/RoundedImage";
import Image from "next/image";
import DummyData from "@/constants/DummyData";
import VedioDetailsInterface from "@/common/interfaces/VedioDetails";

const WatchPage = async ({
  params,
}: {
  params: Promise<{ id: string }>;
}): Promise<React.ReactElement> => {
  const { id } = await params;
  const currentlyWatchingIndex = DummyData.findIndex(
    (vedioData) => vedioData.id === id
  );
  const currentlyWatching: VedioDetailsInterface =
    DummyData[currentlyWatchingIndex];

  return (
    <div className="flex flex-col lg:flex-row p-6 gap-4">
      <div id="video-comments" className="w-full lg:w-8/12 ">
        <Image
          id="video-logo"
          src={"/hq720.jpg"}
          width={0}
          height={0}
          sizes={"100vw"}
          style={{
            width: "100%",
            height: "auto",
            borderRadius: "20px",
            aspectRatio: "16/9",
          }}
          alt="Image for"
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
              <strong>{currentlyWatching.channel.name}</strong>
            </p>
            <p className="text-sm">Subs Count</p>
          </div>
          <button className="p-1 px-4 border border-gray-300 rounded-full font-semibold">
            Subscribe
          </button>
        </div>
        <DescriptionCard {...currentlyWatching} />
      </div>
      <div className="w-full lg:w-4/12">
        <RecomendationList />
      </div>
    </div>
  );
};

export default WatchPage;
