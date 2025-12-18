import VedioCard from "@/components/VedioCard/VedioCard";
const VideoList = (): React.ReactElement => {
  return (
    <div className="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3  xl:grid-cols-3 2xl:grid-cols-6 gap-4">
      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />

      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />
      <VedioCard />
    </div>
  );
};

export default VideoList;
