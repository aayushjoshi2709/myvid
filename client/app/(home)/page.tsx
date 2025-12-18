import VideoList from "@/components/VedioList/VideoList";

export default function Home(): React.ReactElement {
  return (
    <div className="p-4 h-full overflow-scroll">
      <VideoList />
    </div>
  );
}
